package com.restaurant.dianca.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("res_category")
public class ResCategory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer isActive;
    private Integer sortOrder;
}