package com.gravity.demo.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gravity.demo.dto.sys.ResourcePermDTO;
import com.gravity.demo.entity.sys.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 获取用户权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserResourcePerms(@Param("uid") Integer uid);

    /**
     * 获取用户菜单资源权限
     *
     * @param uid
     * @return
     */
    List<ResourcePermDTO> getUserMenuResourcePerms(@Param("uid") Integer uid);
}
