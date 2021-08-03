package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.User;


public interface IUserDao {
	public User save(User user);
	public User load(String userEmail);
	public List<User> loadAll();
}