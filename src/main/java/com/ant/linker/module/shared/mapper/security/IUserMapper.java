package com.ant.linker.module.shared.mapper.security;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.UserRole;
import com.ant.linker.module.shared.dto.account.AccountDetailDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

@Mapper(componentModel = "spring")
@Component
public abstract class IUserMapper {

	@Mappings({ @Mapping(source = "email", target = "email"), @Mapping(source = "password", target = "password"),
			@Mapping(source = "roles", target = "roles") })
	public abstract User dtoToEntity(UserSignUpDto userDto);

	@Mappings({ @Mapping(source = "email", target = "email"), @Mapping(source = "password", target = "password") })
	public abstract UserSignUpDto entityToDto(User user);

	public List<UserRole> rolesArrayToRolesList(String[] roles) {
		List<UserRole> userRoles = new ArrayList<>();
		if (roles != null) {
			for (String role : roles) {
				userRoles.add(UserRole.valueOf(role));
			}
		}
		return userRoles;
	}
	
	@Mappings({ @Mapping(source = "credentials.email", target = "email"),
		@Mapping(source = "credentials.password", target = "password"),
		@Mapping(source = "credentials.roles", target = "roles"),
		@Mapping(source = "firstName", target = "firstName"),
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "phone", target = "phone"),
		@Mapping(source = "company", target = "company")})
	public abstract User dtoToFullEntity(AccountDetailDto userAccount);
	
	@Mappings({ @Mapping(source = "email", target = "credentials.email"),
		@Mapping(source = "roles", target = "credentials.roles"),
		@Mapping(source = "firstName", target = "firstName"),
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "phone", target = "phone"),
		@Mapping(source = "company", target = "company")})
	public abstract AccountDetailDto entityToFullDto(User userAccount);

}
