package com.ant.linker.module.cart.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ant.linker.data.dao.IBrand;
import com.ant.linker.data.dao.ICartDao;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCartDao;
import com.ant.linker.data.dao.ICommercialCatalogDao;
import com.ant.linker.data.dao.ICustomerDao;
import com.ant.linker.data.dao.IGuestDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.UserRole;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.shared.dto.cart.AddFromGuestCartToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToMultipleCartDto;
import com.ant.linker.module.shared.dto.cart.CartDto;
import com.ant.linker.module.shared.dto.cart.EmailsForMergeDto;
import com.ant.linker.module.shared.factory.cart.ICartFactory;
import com.ant.linker.module.shared.mapper.ICartGuestMapper;
import com.ant.linker.module.shared.mapper.ICartMapper;
import com.googlecode.objectify.Ref;

@Component
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ICommercial commercialDao;
	@Autowired
	private IGuestDao guestDao;
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private ICommercialCartDao commercialCartDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IBrand brandDao;
	@Autowired
	private ICommercialCatalogDao commercialCatalogDao;
	@Autowired
	private IUserService userService;

	@Autowired
	private ICartMapper cartMapper;
	@Autowired
	private ICartGuestMapper cartGuestMapper;
	
	@Autowired
	private ICartFactory cartFactory;

	@Override
	public Integer addProductToCart(AddToMultipleCartDto addToCartDto) {

		addToCartDto.getCommercialEmail().forEach(commercialEmail -> {
			Cart cart = null;
			User user = userDao.load(addToCartDto.getGuestOrCustomerEmail());
			if (user.getRoles().contains(UserRole.CUSTOMER)) {
				Customer customer = customerDao.loadCustomerByUser(user);
				cart = cartDao.loadCartByCustomer(customer);
			} else if (user.getRoles().contains(UserRole.GUEST)) {
				Guest guest = guestDao.loadGuestByUser(user);
				cart = cartDao.loadCartByGuest(guest);
			}

			Product product = productDao.loadProductByBrandModel(brandDao.loadBrandByLabel(addToCartDto.getBrand()),
					addToCartDto.getModel());

			User userCommercial = userDao.load(commercialEmail);
			Commercial commercial = commercialDao.loadCommercialByUser(userCommercial);

			if (cart != null && cart.getListCommercialCart().size() > 0) {

				CommercialCart comCartByEmail = findComCartByEmail(cart, commercialEmail);
				if (comCartByEmail != null) {
					int x = 0;
					for (Product element : comCartByEmail.getListProduct()) {
						if (element == product) {
							x = 1;
						}
					}
					if (x == 0) {
						comCartByEmail.addProduct(product);
						commercialCartDao.save(comCartByEmail);
					}

				} else {
					createCommercialCart(product, commercial, cart);
				}

			} else {
				createCommercialCart(product, commercial, cart);
			}

		});

		return nbArticle(addToCartDto.getGuestOrCustomerEmail());

	}

	@Override
	public Integer nbArticle(String customerOrGuestEmail) {

		Cart cart = null;

		User user = userDao.load(customerOrGuestEmail);
		if (user.getRoles().contains(UserRole.CUSTOMER)) {
			Customer customer = customerDao.loadCustomerByUser(user);
			cart = cartDao.loadCartByCustomer(customer);
		} else if (user.getRoles().contains(UserRole.GUEST)) {
			Guest guest = guestDao.loadGuestByUser(user);
			cart = cartDao.loadCartByGuest(guest);
		}

		Integer nbProduct = 0;

		if (cart != null) {

			for (CommercialCart item1 : cart.getListCommercialCart()) {
				if (cart.getListCommercialCart() == null) {
					return 0;
				}
				for (Product item2 : item1.getListProduct()) {
					nbProduct = new Integer(nbProduct.intValue() + 1);
				}
			}
		}

		return nbProduct;
	}

	@Override
	public CommercialCart findComCartByEmail(Cart cart, String commercialEmail) {
		if (cart == null)
			return null;

		CommercialCart filtredCommercial = cart.getListCommercialCart().stream().filter(commercialCart -> {
			if (commercialCart != null) {
				String email = commercialCart.getCommercial().getUserAccount().getEmail();
				return StringUtils.compare(email, commercialEmail) == 0;
			} else {
				return false;
			}
		}).findFirst().orElse(null);

		return filtredCommercial;
	}

	private void createCommercialCart(Product product, Commercial commercial, Cart cart) {
		CommercialCart commercialCart = new CommercialCart();
		commercialCart.setCart(cart);
		commercialCart.setCommercial(commercial);
		commercialCart.addProduct(product);
		commercialCartDao.save(commercialCart);
		cart.addCommercialCart(commercialCart);
		cartDao.save(cart);
		commercial.addCommercialCart(commercialCart);
		commercialDao.saveCommercial(commercial);
	}

	@Override
	public CartDto sendCartByUser(String email) {
		Cart cart = new Cart();

		User user = userDao.load(email);
		if (user.getRoles().contains(UserRole.CUSTOMER)) {
			Customer customer = customerDao.loadCustomerByUser(user);
			cart = cartDao.loadCartByCustomer(customer);
			return cartMapper.entityToDto(cart);
		} else if (user.getRoles().contains(UserRole.GUEST)) {
			Guest guest = guestDao.loadGuestByUser(user);
			cart = cartDao.loadCartByGuest(guest);
			return cartGuestMapper.entityToDto(cart);
		}

		return new CartDto();
	}

	@Override
	public CartDto removeProductFromCart(AddToCartDto productToRemove) {

		Product product = productDao.loadProductByBrandModel(brandDao.loadBrandByLabel(productToRemove.getBrand()),
				productToRemove.getModel());

		Cart cart = new Cart();

		int flag = 0;

		User user = userDao.load(productToRemove.getGuestOrCustomerEmail());

		if (user.getRoles().contains(UserRole.CUSTOMER)) {
			Customer customer = customerDao.loadCustomerByUser(user);
			cart = cartDao.loadCartByCustomer(customer);
			flag = 1;
		} else if (user.getRoles().contains(UserRole.GUEST)) {
			Guest guest = guestDao.loadGuestByUser(user);
			cart = cartDao.loadCartByGuest(guest);
			flag = 0;
		}

		Commercial commercial = userService.getCommercialByAccountEmail(productToRemove.getCommercialEmail());

		List<CommercialCart> listCommercialCart = commercialCartDao.loadCommercialCartByCommercialInCart(cart, commercial);

		for (CommercialCart comCart : listCommercialCart) {
			for (Product item : comCart.getListProduct()) {
				if (item.getBrand().getLabel().equals(productToRemove.getBrand())
						&& item.getModel().equals(productToRemove.getModel())) {
					comCart.removeProduct(product);
					commercialCartDao.save(comCart);
					if (comCart.getListProduct().size() == 0) {
						commercial.removeCommercialCart(comCart);
						commercialDao.saveCommercial(commercial);
						cart.removeCommercialCart(comCart);
						cartDao.save(cart);
						commercialCartDao.deleteCommercialCart(comCart);
					}
					if (flag == 1) {
						return cartMapper.entityToDto(cart);
					} else if (flag == 0) {
						return cartGuestMapper.entityToDto(cart);
					}

				}
			}
		}

		return new CartDto();

	}

	@Override
	public CartDto addProductFromCartGuest(AddFromGuestCartToCartDto productToAdd) {

		AddToMultipleCartDto createGuestMergeMultipleCart = cartFactory.createGuestMergeMultipleCart(productToAdd);
		addProductToCart(createGuestMergeMultipleCart);

		AddToCartDto createGuestRemoveCart = cartFactory.createGuestRemoveCart(productToAdd);
		CartDto cartDto = removeProductFromCart(createGuestRemoveCart);

		return cartDto;
	}

	@Override
	public Integer mergeCarts(EmailsForMergeDto infos) throws Exception {
		Integer number = 0;

		Guest guest = userService.getGuestByAccountEmail(infos.getGuestEmail());
		Cart cartGuest = cartDao.loadCartByGuest(guest);

		for (CommercialCart comCart : cartGuest.getListCommercialCart()) {
			for (Product product : comCart.getListProduct()) {
				AddFromGuestCartToCartDto item = new AddFromGuestCartToCartDto(product.getBrand().getLabel(),
						product.getModel(), infos.getGuestEmail(), infos.getCustomerEmail(),
						comCart.getCommercial().getUserAccount().getEmail());
				addProductFromCartGuest(item);
				number = 1;
			}
		}

		return number;
	}
}
