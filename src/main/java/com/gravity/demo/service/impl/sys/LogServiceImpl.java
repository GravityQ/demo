package com.gravity.demo.service.impl.sys;

import com.gravity.demo.entity.sys.Log;
import com.gravity.demo.mapper.sys.LogMapper;
import com.gravity.demo.service.sys.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author gravity
 * @since 2019-11-22
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
