package com.gravity.demo.common.utils;

import com.gravity.demo.common.model.TreeNode;

import java.util.List;

/**
 * 树结构工具类
 *
 * @author qijunlin
 * @date 2019/11/16 6:06 下午
 */

public class TreeUtils {
    public static <T extends TreeNode> TreeNode findChildren(T treeNode, List<T> treeNodes) {
        treeNodes.stream().filter(e -> treeNode.getId().equals(e.getParentId())).forEach(e -> e.getChildren().add(findChildren(e, treeNodes)));
        return treeNode;
    }
}
