package com.restaurant.dianca.controller;

import com.restaurant.dianca.entity.SysUser;
import com.restaurant.dianca.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController 表示这个类是一个对外提供 API 的接口类，返回 JSON 格式数据
@RestController
// @RequestMapping 定义了这个接口的统一前缀路径
@RequestMapping("/api/users")
public class SysUserController {

    // 注入我们之前写好的 Mapper
    @Autowired
    private SysUserMapper sysUserMapper;

    // @GetMapping 表示响应 HTTP 的 GET 请求
    @GetMapping("/list")
    public List<SysUser> getUserList() {
        // 直接调用 MyBatis-Plus 帮我们写好的 selectList 方法，查询所有用户
        return sysUserMapper.selectList(null);
    }
}