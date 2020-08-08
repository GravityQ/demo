package com.gravity.demo.common.utils;

import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.val;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;

/**
 * 统一描述 property path，支持 bean、map、array/collection 多级嵌套
 *
 * 1. GET(String parameter)         => parameter
 * 2. POST(Bean bean)               => property
 * 3. POST(List<Bean> beans)        => [0].property
 * 4. POST(Map<String, Bean> beans) => [key].property
 *
 * 适配 {@link JsonMappingException}
 * 适配 {@link ConstraintViolation}
 *
 * @since 2019-11-24
 */
public class PropertyPathUtils {

    private static final Pattern TYPE_PARAMETER = Pattern.compile("<[^>]+>");

    private static final Pattern INDEX = Pattern.compile("\\[^]]+\\]");

    private static final String INDEX_PLACEHOLDER = "[]";

    private static final char INDEX_OPEN = '[';
    private static final char INDEX_CLOSE = ']';

    private static final char PROPERTY_PATH_SEPARATOR = '.';

    public static String removeIndexVariable(String path) {
        return INDEX.matcher(path).replaceAll(INDEX_PLACEHOLDER);
    }

    /**
     * @see JsonMappingException.Reference#getDescription
     */
    public static String getPathString(JsonMappingException e) {
        val builder = new StringBuilder();

        for (val reference : e.getPath()) {
            if (reference.getIndex() >= 0) {
                // array or collection
                builder.append(INDEX_OPEN).append(reference.getIndex()).append(INDEX_CLOSE);
                continue;
            }

            val from = reference.getFrom();
            val cls = (from instanceof Class) ? (Class) from : from.getClass();
            val field = reference.getFieldName();

            if (Map.class.isAssignableFrom(cls)) {
                builder.append(INDEX_OPEN).append(field).append(INDEX_CLOSE);
            } else {
                builder.append(PROPERTY_PATH_SEPARATOR).append(field);
            }
        }

        return trim(builder);
    }

    /**
     * 去除泛型信息，保留 index
     * map<K>[b].<map key> => map[b]
     *
     * @see PathImpl#toString
     * @see NodeImpl#toString
     * @see NodeImpl#MAP_KEY_NODE_NAME
     */
    public static String getPathString(Path path) {
        val builder = new StringBuilder();
        val iterator = path.iterator();

        String parameterName = "";
        ElementKind parent = null;

        while (iterator.hasNext()) {
            val node = iterator.next();
            val kind = node.getKind();

            if (parent != ElementKind.PROPERTY) {
                // 处理 PARAMETER & CONTAINER_ELEMENT 里的 index
                // 注意 index & key 返回的都是 parent 的数据
                Object index = node.getIndex();
                if (index == null) {
                    index = node.getKey();
                }
                if (index != null) {
                    builder.append(INDEX_OPEN).append(index).append(INDEX_CLOSE);
                }
            }
            parent = kind;

            if (kind != ElementKind.PROPERTY) {
                if (kind == ElementKind.PARAMETER) {
                    parameterName = node.getName();
                }
                continue;
            }

            // 已包含当前节点的 index
            String property = node.toString();

            if (node.isInIterable()) {
                // 去除泛型信息
                property = TYPE_PARAMETER.matcher(property).replaceAll("");
            }
            builder.append(PROPERTY_PATH_SEPARATOR).append(property);
        }

        if (builder.length() <= 0) {
            // controller 方法上的参数名
            return parameterName;
        }

        return trim(builder);
    }

    private static String trim(StringBuilder builder) {
        if (builder.length() <= 0) {
            return "";
        }

        if (builder.charAt(0) == PROPERTY_PATH_SEPARATOR) {
            return builder.substring(1);
        }
        return builder.toString();
    }
}
