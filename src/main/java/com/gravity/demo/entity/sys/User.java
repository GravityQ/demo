package com.gravity.demo.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.gravity.demo.common.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@Data
@TableName("sys_user")
@ApiModel(value="User对象", description="系统用户表")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty(value = "用户名")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "状态 0：禁用 1：正常")
    private StatusEnum status;

    @ApiModelProperty(value = "创建者ID")
    private Integer createUid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登陆名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "IP地址")
    private String ip;


}
