package com.gravity.demo.service.sys;

import com.gravity.demo.common.enums.AuthTypeEnum;
import com.gravity.demo.dto.sys.ResourcePermDTO;
import com.gravity.demo.entity.sys.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 根据用户id获取所有权限
     * @param userId
     * @return
     */
    List<String> getUserPerms(Integer userId);

    /**
     * 获取开放权限资源列表
     *
     * @return
     */
    List<ResourcePermDTO> getOpenPerms();

    /**
     * 获取需要登录权限资源列表
     *
     * @return
     */
    List<ResourcePermDTO> getLoginPerms();

    /**
     * 获取指定类型权限资源列表
     *
     * @param authTypes 类型
     * @return
     */
    List<ResourcePermDTO> getPerms(AuthTypeEnum... authTypes);

    /**
     * 获取用户所有权限
     *
     * @param uid
     * @return
     */
    Set<ResourcePermDTO> getUserResourcePerms(Integer uid);

    /**
     * 获取所有权限
     *
     * @return
     */
    List<ResourcePermDTO> getPerms();

    /**
     * 获取某种请求方式资源权限
     *
     * @param method
     * @return
     */
    List<ResourcePermDTO> getResourcePerms(String method);

    /**
     * 获取资源权限标记
     *
     * @param method
     * @param mapping
     * @return
     */
    String getResourcePermTag(String method, String mapping);

    /**
     * 获取用户菜单资源权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserMenuResourcePerms(Integer uid);

    /**
     * <p>
     * 批量修改插入
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean saveOrUpdateBatch(Collection<Resource> entityList);
}
