package com.restaurant.dianca.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("res_order_item")
public class ResOrderItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long orderId;   // 关联的订单主表ID
    private Long dishId;    // 菜品ID
    private String dishName; // 菜品名称
    private Integer quantity; // 数量
    private BigDecimal unitPrice; // 购买时的单价
}