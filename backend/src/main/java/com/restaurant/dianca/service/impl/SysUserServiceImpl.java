package com.restaurant.dianca.service.impl;

import com.restaurant.dianca.entity.SysUser;
import com.restaurant.dianca.mapper.SysUserMapper;
import com.restaurant.dianca.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getUserList() {
        return sysUserMapper.selectList(null);
    }
}
