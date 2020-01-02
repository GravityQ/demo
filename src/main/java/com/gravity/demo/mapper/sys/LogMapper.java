package com.gravity.demo.mapper.sys;

import com.gravity.demo.entity.sys.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @author gravity
 * @since 2019-11-22
 */
public interface LogMapper extends BaseMapper<Log> {
    public int updateTime();
}
