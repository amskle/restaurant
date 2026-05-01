package com.restaurant.dianca.service.impl;

import com.restaurant.dianca.entity.ResCategory;
import com.restaurant.dianca.entity.ResDish;
import com.restaurant.dianca.mapper.ResCategoryMapper;
import com.restaurant.dianca.mapper.ResDishMapper;
import com.restaurant.dianca.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private ResCategoryMapper categoryMapper;

    @Autowired
    private ResDishMapper dishMapper;

    @Override
    public Map<String, Object> getMenu() {
        Map<String, Object> result = new HashMap<>();
        result.put("categories", categoryMapper.selectList(null));
        result.put("dishes", dishMapper.selectList(null));
        return result;
    }

    @Override
    public String initMenuData() {
        if (categoryMapper.selectCount(null) > 0) {
            return "数据库中已有菜单数据，请勿重复初始化！可以直接访问 /api/menu/list 查看。";
        }

        ResCategory category1 = new ResCategory();
        category1.setName("经典热炒");
        category1.setIsActive(1);
        categoryMapper.insert(category1);

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
        dish2.setStockQuantity(-1);
        dishMapper.insert(dish2);

        return "菜单初始化成功！成功插入 1 个分类和 2 道菜品。请访问 http://localhost:8080/api/menu/list 查看结果。";
    }
}
