package com.ant.linker.data.dao;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

@Component
public class UserDao implements IUserDao {

	@Override
	public User save(User user) {
		ofy().save().entity(user).now();
		return user;
	}
	
	@Override
	public User load(String userEmail) {
		User user = ofy().load().type(User.class).filter("email", userEmail).first().now();
		return user;
	}
	
	@Override
	public List<User> loadAll() {
		return ofy().load().type(User.class).list();
	}

}
