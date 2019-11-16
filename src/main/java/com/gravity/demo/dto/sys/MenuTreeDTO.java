package com.gravity.demo.dto.sys;

import com.gravity.demo.common.model.TreeNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单
 *
 * @author qijunlin
 * @date 2019/11/16 5:19 下午
 */
@Data
public class MenuTreeDTO extends TreeNode {
    @ApiModelProperty(notes = "菜单名称")
    private String menuName;
    @ApiModelProperty(notes = "类型:1:目录,2:菜单,3:按钮")
    private Integer menuType;
    @ApiModelProperty(notes = "映射地址")
    private String router;
    @ApiModelProperty(notes = "路径")
    private String path;
    @ApiModelProperty(notes = "图标")
    private String icon;
    @ApiModelProperty(notes = "别名")
    private String perm;
}
