package com.restaurant.dianca;

import com.restaurant.dianca.entity.SysUser;
import com.restaurant.dianca.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// 注意下面这一行的修改 👇
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// 🚨 强制指定启动类，完美解决 Unable to find a @SpringBootConfiguration 问题
@SpringBootTest(classes = DiancaApplication.class)
class DiancaApplicationTests {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Test
	void testDbConnection() {
		SysUser admin = new SysUser();
		admin.setUsername("admin");
		admin.setPassword("admin123");
		admin.setRole(0);
		admin.setIsActive(1);

		sysUserMapper.insert(admin);
		System.out.println("成功插入管理员数据，ID为：" + admin.getId());

		List<SysUser> users = sysUserMapper.selectList(null);
		System.out.println("当前数据库中的用户：");
		users.forEach(System.out::println);
	}
}