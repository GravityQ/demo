package com.gravity.demo.service.impl;

import com.gravity.demo.entity.Menu;
import com.gravity.demo.mapper.MenuMapper;
import com.gravity.demo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author gravity
 * @since 2019-08-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
