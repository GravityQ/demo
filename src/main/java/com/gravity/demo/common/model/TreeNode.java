package com.gravity.demo.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qijunlin
 * @date 2019/11/16 5:38 下午
 */
@Data
public class TreeNode {
    protected Integer id;
    protected Integer parentId;

    protected List<TreeNode> children = new ArrayList<>();
}
