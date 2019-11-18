package com.gravity.demo.service.impl.sys;

import com.gravity.demo.service.sys.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qijunlin
 * @date 2019/11/16 6:35 下午
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {
    @Autowired
    MenuService menuService;

    @Test
    public void getUserMenus() {
        System.out.println(menuService.getUserMenus("crown"));
    }
}
