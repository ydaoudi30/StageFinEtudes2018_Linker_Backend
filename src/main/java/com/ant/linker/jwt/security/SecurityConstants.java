package com.ant.linker.jwt.security;

public class SecurityConstants {
	public static final String SECRET = "yAPFbu7j0WEUc9aVU70DDvOjICjeQrYp";
	public static final long EXPIRATION_TIME = 600_000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/cmr/users/default**";
	public static final String PRODUCT_URL = "/cmr/**/default/**";
	public static final String ADMIN = "/_ah/**";
	public static final String COMMERCIAL_API = "/cmr/**";
	public static final String ADMIN_API = "/adm/**";
	public static final String CUSTOMER_API = "/cust/**";
	public static final String GUEST_API = "/guest/**";
	public static final String CATALOG_IMPORT_API = "/cmr/import/catalog";
	public static final String GET_CATEGORIES = "/cmr/product/category-**";
	public static final String GET_CUST_CATEGORIES = "/cust/category/list";
	public static final String GET_NEW_PRODUCTS = "/cust/product/search/**";
	public static final String GET_FILTER_PRODUCTS = "/cust/product/search/filter/date";
	public static final String GET_PRODUCTS_BYREF = "/cust/product/search/filter/bycategoryref";
	public static final String GET_IMG_TEST = "/serve/image/**";
	public static final String CRON_API = "/cronJobs/**";
	public static final String GUEST_PWD = "y70DDvOjICjeQrYpAPFbu7j0WEUc9aVU";
	public static final String USER_SIGN_UP_API = "/account/**";
	public static final String ADMIN_CACHE = "/adm/cache/**";
}
