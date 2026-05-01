package com.restaurant.dianca.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("res_order")
public class ResOrder {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;       // 订单流水号
    private Integer tableNumber;  // 桌号
    private Integer status;       // 状态
    private BigDecimal totalAmount; // 总金额
    private BigDecimal finalAmount; // 最终金额
    private LocalDateTime createTime; // 下单时间
}