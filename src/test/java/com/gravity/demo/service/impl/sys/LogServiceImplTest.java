package com.gravity.demo.service.impl.sys;

import com.gravity.demo.service.sys.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author qijunlin
 * @date 2019/11/28 4:57 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceImplTest {
    @Autowired
    LogService logService;

    @Test
    public void update() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> logService.updateTime()).start();
        }
    }
}