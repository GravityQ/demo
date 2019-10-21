package com.gravity.demo.service.impl.sys;

import com.gravity.demo.common.enums.AuthTypeEnum;
import com.gravity.demo.dto.sys.ResourcePermDTO;
import com.gravity.demo.entity.sys.Resource;
import com.gravity.demo.mapper.sys.ResourceMapper;
import com.gravity.demo.service.sys.MenuResourceService;
import com.gravity.demo.service.sys.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    private MenuResourceService menuResourceService;

    @Override
    public List<String> getUserPerms(Integer userId) {
        return getUserResourcePerms(userId).stream().map(e->getResourcePermTag(e.getMapping(),e.getMethod())).collect(Collectors.toList());
    }

    @Override
    public List<ResourcePermDTO> getOpenPerms() {
        return null;
    }

    @Override
    public List<ResourcePermDTO> getLoginPerms() {
        return null;
    }

    @Override
    public List<ResourcePermDTO> getPerms(AuthTypeEnum... authTypes) {
        return null;
    }

    @Override
    public Set<ResourcePermDTO> getUserResourcePerms(Integer uid) {
        List<ResourcePermDTO> perms = getPerms(AuthTypeEnum.LOGIN,AuthTypeEnum.OPEN);
        List<ResourcePermDTO> userResourcePerms = baseMapper.getUserResourcePerms(uid);
        List<ResourcePermDTO> userMenuResourcePerms = getUserMenuResourcePerms(uid);
        perms.addAll(userMenuResourcePerms);
        perms.addAll(userResourcePerms);
        return new HashSet<>(perms);
    }

    @Override
    public List<ResourcePermDTO> getPerms() {
        return null;
    }

    @Override
    public List<ResourcePermDTO> getResourcePerms(String method) {
        return null;
    }

    @Override
    public String getResourcePermTag(String method, String mapping) {
        return method+":"+mapping;
    }

    @Override
    public List<ResourcePermDTO> getUserMenuResourcePerms(Integer uid) {
        return baseMapper.getUserMenuResourcePerms(uid);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Resource> entityList) {
        return false;
    }
}
