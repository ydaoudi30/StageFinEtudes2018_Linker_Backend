package com.ant.linker.jwt.security.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.IUserDao;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private UserAuthoritiesHelper userAuthoritiesService;

	public UserDetailsServiceImpl(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] credentials = new String[2];
		String[] authorities = new String[50];
		ObjectifyService.run(new VoidWork() {
			public void vrun() {
				com.ant.linker.data.entity.User user = userDao.load(username);
				credentials[0] = user.getEmail();
				credentials[1] = user.getPassword();
				userAuthoritiesService.buildUserAuthority(user.getRoles(), authorities);
			}
		});
		if (credentials[0] == null || credentials[1] == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(credentials[0], credentials[1], userAuthoritiesService.buildUserAuthority(authorities));
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public UserAuthoritiesHelper getUserAuthoritiesService() {
		return userAuthoritiesService;
	}

	public void setUserAuthoritiesService(UserAuthoritiesHelper userAuthoritiesService) {
		this.userAuthoritiesService = userAuthoritiesService;
	}
	
	
}