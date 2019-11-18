package com.gravity.demo.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.model.TreeNode;
import com.gravity.demo.common.utils.TreeUtils;
import com.gravity.demo.dto.sys.MenuTreeDTO;
import com.gravity.demo.entity.sys.Menu;
import com.gravity.demo.mapper.sys.MenuMapper;
import com.gravity.demo.service.sys.MenuService;
import com.gravity.demo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserService userService;

    @Override
    public ResultResponse getUserMenus(String username) {
        Integer uid = userService.query().eq("login_name", username).one().getUid();
        List<MenuTreeDTO> menus = menuMapper.getUserMenus(uid);
        List<TreeNode> list = menus.stream().filter(e -> e.getParentId() == 0).map(e -> TreeUtils.findChildren(e, menus)).collect(Collectors.toList());
        return ResultResponse.success(list);

    }
}
