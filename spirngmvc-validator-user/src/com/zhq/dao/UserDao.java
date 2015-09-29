package com.zhq.dao;

import java.util.List;
import com.zhq.entity.User;

public interface UserDao {
	//查询所有用户
	List<User> selectUsers();
	//添加用户
	void insertUser(User user);
	//删除用户
	void deleteUser(User user);
	//根据ID查询用户
	User getUserById(Long id);
	//修改用户
	void updateUser(User user);
}
