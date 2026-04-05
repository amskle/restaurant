package com.restaurant.dianca.controller;

import com.restaurant.dianca.entity.ResCategory;
import com.restaurant.dianca.entity.ResDish;
import com.restaurant.dianca.mapper.ResCategoryMapper;
import com.restaurant.dianca.mapper.ResDishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin; // 加上这个引入

@CrossOrigin // 🌟 加上这个注解，允许前端访问
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private ResCategoryMapper categoryMapper;

    @Autowired
    private ResDishMapper dishMapper;

    // 1. 获取完整菜单 (查)
    @GetMapping("/list")
    public Map<String, Object> getMenu() {
        Map<String, Object> result = new HashMap<>();
        // 同时查出分类和对应的菜品返回给前端
        result.put("categories", categoryMapper.selectList(null));
        result.put("dishes", dishMapper.selectList(null));
        return result;
    }

    // 2. 浏览器测试专用：一键初始化测试数据 (增)
    @GetMapping("/init")
    public String initMenuData() {
        // 先检查是否已经有数据，防止重复插入
        if (categoryMapper.selectCount(null) > 0) {
            return "数据库中已有菜单数据，请勿重复初始化！可以直接访问 /api/menu/list 查看。";
        }

        // 添加一个分类：热炒
        ResCategory category1 = new ResCategory();
        category1.setName("经典热炒");
        category1.setIsActive(1);
        categoryMapper.insert(category1);

        // 添加属于该分类的两道菜
        ResDish dish1 = new ResDish();
        dish1.setCategoryId(category1.getId());
        dish1.setName("宫保鸡丁");
        dish1.setDescription("酸甜微辣，非常下饭");
        dish1.setPrice(new BigDecimal("28.00"));
        dish1.setStatus(0);
        dish1.setStockQuantity(50);
        dishMapper.insert(dish1);

        ResDish dish2 = new ResDish();
        dish2.setCategoryId(category1.getId());
        dish2.setName("麻婆豆腐");
        dish2.setDescription("麻辣鲜香，经典川菜");
        dish2.setPrice(new BigDecimal("18.00"));
        dish2.setStatus(0);
        dish2.setStockQuantity(-1); // -1表示不限库存
        dishMapper.insert(dish2);

        return "菜单初始化成功！成功插入 1 个分类和 2 道菜品。请访问 http://localhost:8080/api/menu/list 查看结果。";
    }
}