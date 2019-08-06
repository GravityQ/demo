package com.gravity.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.gravity.demo.common.BaseEntity;
import com.gravity.demo.common.enums.MenuTypeEnum;
import com.gravity.demo.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author gravity
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@Accessors(chain = true)
@TableName("sys_menu")
@NoArgsConstructor
public class Menu extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 路由
     */
    private String router;

    /**
     * 类型:1:目录,2:菜单,3:按钮
     */
    private MenuTypeEnum menuType;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 别名
     */
    private String alias;
    /**
     * 状态 0：禁用 1：正常
     */
    private StatusEnum status;
    /**
     * 创建者ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createUid;

    /**
     * 修改者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateUid;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
