package com.restaurant.dianca.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Integer role; // 0:管理员, 1:经理, 2:服务员, 3:收银员
    private Integer isActive;
    private LocalDateTime lastLoginTime;
}
