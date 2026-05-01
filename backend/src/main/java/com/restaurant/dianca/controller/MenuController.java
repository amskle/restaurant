package com.restaurant.dianca.controller;

import com.restaurant.dianca.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Map<String, Object> getMenu() {
        return menuService.getMenu();
    }

    @GetMapping("/init")
    public String initMenuData() {
        return menuService.initMenuData();
    }
}
