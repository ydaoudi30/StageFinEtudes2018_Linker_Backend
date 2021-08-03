package com.ant.linker.module.commercial.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.ICartDao;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCartDao;
import com.ant.linker.data.dao.ICustomerDao;
import com.ant.linker.data.dao.IGuestDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.UserRole;
import com.ant.linker.jwt.security.SecurityConstants;
import com.ant.linker.module.shared.dto.account.AccountDetailDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.mapper.security.IUserMapper;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private ICommercial commercialDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IGuestDao guestDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICartDao cartDao;

	@Autowired
	private ICommercialCartDao commercialCartDao;

	@Autowired
	private IUserMapper userMapper;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Commercial getCommercialByAccountEmail(String email) {
		// TODO Auto-generated method stub
		User userAccount = userDao.load(email);
		Commercial commercial = null;
		if (userAccount != null) {
			commercial = commercialDao.loadCommercialByUser(userAccount);
		}
		return commercial;
	}

	@Override
	public Customer getCustomerByAccountEmail(String email) {
		// TODO Auto-generated method stub
		User userAccount = userDao.load(email);
		Customer customer = null;
		if (userAccount != null) {
			customer = customerDao.loadCustomerByUser(userAccount);
		}
		return customer;
	}

	@Override
	public Guest getGuestByAccountEmail(String email) {
		// TODO Auto-generated method stub
		User userAccount = userDao.load(email);
		Guest guest = null;
		if (userAccount != null) {
			guest = guestDao.loadGuestByUser(userAccount);
		}
		return guest;
	}

	@Override
	public UserSignUpDto createGuestAccount() {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String date = String.valueOf(timestamp.getTime());
		Integer number = (int) Math.random();
		String guestEmail = "guest" + date + number.toString() + "@gmail.com";

		UserSignUpDto guestDto = new UserSignUpDto(guestEmail, SecurityConstants.SECRET);
		guestDto.setPassword(bCryptPasswordEncoder.encode(guestDto.getPassword()));
		User guestUser = userMapper.dtoToEntity(guestDto);
		guestUser.setFirstName("Guest");
		guestUser.setLastName("Guest");
		guestUser.addUserRole(UserRole.GUEST);
		userDao.save(guestUser);

		Guest guest = new Guest();
		guest.setUserAccount(guestUser);
		ofy().save().entity(guest).now();

		Cart guestCart = new Cart();
		guestCart.setGuest(guest);
		ofy().save().entity(guestCart).now();

		guest.setCart(guestCart);
		ofy().save().entity(guest).now();

		guestUser.setGuestProfil(guest);
		userDao.save(guestUser);

		return guestDto;

	}

	@Override
	public Integer deleteGuestAccount(String email) {

		Guest guest = userService.getGuestByAccountEmail(email);
		Cart cart = cartDao.loadCartByGuest(guest);
		for (CommercialCart comCart : cart.getListCommercialCart()) {

			comCart.getCommercial().removeCommercialCart(comCart);
			ofy().save().entity(comCart.getCommercial()).now();

			cart.removeCommercialCart(comCart);
			commercialCartDao.deleteCommercialCart(comCart);

		}

		cartDao.deleteCart(cart);
		guestDao.deleteGuest(guest);

		return 1;
	}

	public void clearGuests() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date dateBefore10Days = cal.getTime();

		List<Guest> listGuest = guestDao.loadOutDatedGuests(dateBefore10Days);
		for (Guest guest : listGuest) {
			deleteGuestAccount(guest.getUserAccount().getEmail());
		}
	}

	public void createCustomerUserAccount(AccountDetailDto customerAccount) throws Exception{
		User loadedUser = userDao.load(customerAccount.getCredentials().getEmail());
		if (loadedUser == null) {

			UserSignUpDto user = customerAccount.getCredentials();
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User userEntity = userMapper.dtoToFullEntity(customerAccount);
			// Create Customer Profile for new Account
			createCustomerProfile(userEntity);
			
			return;
		} else if(!loadedUser.getRoles().contains(UserRole.CUSTOMER)) {
			// Create Customer Profile for new Account
			createCustomerProfile(loadedUser);
			return;
		}

		throw new Exception("Try to create user duplicated");
	}

	public void createCommercialUserAccount(AccountDetailDto commercialAccount) throws Exception {
		User loadedUser = userDao.load(commercialAccount.getCredentials().getEmail());
		if (loadedUser == null) {
			UserSignUpDto user = commercialAccount.getCredentials();
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			User userEntity = userMapper.dtoToFullEntity(commercialAccount);
			// Create Commercial Profile for new user
			createCommercialProfile(userEntity);
			
			return;
		} else if(!loadedUser.getRoles().contains(UserRole.COMMERCIAL)) {
			// Create Commercial Profile for existing user
			createCommercialProfile(loadedUser);
			return;
		}

		throw new Exception("Try to create user duplicated");

	}
	
	private void createCustomerProfile(User userAccount) {
		List<UserRole> roles = userAccount.getRoles();
		if(roles == null) {
			roles = new ArrayList<UserRole>();
		}
		roles.add(UserRole.CUSTOMER);
		userAccount.setRoles(roles);
		userDao.save(userAccount);

		Customer customer = new Customer();
		customer.setUserAccount(userAccount);
		customerDao.save(customer);

		Cart customerCart = new Cart();
		customerCart.setCustomer(customer);
		cartDao.save(customerCart);

		customer.setCart(customerCart);
		customerDao.save(customer);

		userAccount.setCustomerProfil(customer);
		userDao.save(userAccount);
	}
	
	private void createCommercialProfile(User userAccount) {
		
		List<UserRole> roles = userAccount.getRoles();
		if(roles == null) {
			roles = new ArrayList<UserRole>();
		}
		roles.add(UserRole.COMMERCIAL);
		userAccount.setRoles(roles);
		userDao.save(userAccount);
		
		Commercial commercial = new Commercial();
		commercial.setUserAccount(userAccount);
		commercialDao.saveCommercial(commercial);

		userAccount.setCommercialProfil(commercial);
		userDao.save(userAccount);
		
	}

	public AccountDetailDto getAccountDetail(String email, UserRole userRole) {
		AccountDetailDto accountDetail = null;
		switch (userRole) {
		case COMMERCIAL: {
			Commercial commercial = getCommercialByAccountEmail(email);
			User userAccount = commercial.getUserAccount();
			accountDetail = userMapper.entityToFullDto(userAccount);
		}
			break;

		case CUSTOMER: {
			User userAccount = userDao.load(email);
			accountDetail = userMapper.entityToFullDto(userAccount);
		}
			break;
		}

		return accountDetail;
	}
	
	public AccountDetailDto getAccountDetail(String email) {
		User userAccount = userDao.load(email);
		AccountDetailDto accountDetail = userMapper.entityToFullDto(userAccount);
		return accountDetail;
	}
}