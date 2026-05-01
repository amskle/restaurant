package com.restaurant.dianca.controller;

import com.restaurant.dianca.entity.SysUser;
import com.restaurant.dianca.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getUserList() {
        return sysUserService.getUserList();
    }
}
