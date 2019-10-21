package com.gravity.demo.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_resource")
@ApiModel(value="Resource对象", description="资源表")
public class Resource implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "路径映射")
    private String mapping;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "权限认证类型")
    private Integer authType;

    private LocalDateTime updateTime;

    @ApiModelProperty(value = "权限标识")
    private String perm;


}
