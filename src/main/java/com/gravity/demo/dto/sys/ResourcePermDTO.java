package com.gravity.demo.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qijunlin
 * @date 2019-08-13 19:27
 */
@Data
public class ResourcePermDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "请求方式")
    private String method;

    @ApiModelProperty(notes = "路径映射")
    private String mapping;

}

