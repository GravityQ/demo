package com.gravity.demo.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gravity.demo.dto.sys.MenuTreeDTO;
import com.gravity.demo.entity.sys.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuTreeDTO> getUserMenus(Integer uid);
}
