package com.codeenginestudio.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeenginestudio.elearning.constant.RoleConstant;
import com.codeenginestudio.elearning.dao.UserDAO;
import com.codeenginestudio.elearning.dao.entity.UserEntity;
import com.codeenginestudio.elearning.dto.UserPrincipal;
import com.codeenginestudio.elearning.util.RoleUtil;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDAO.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Can not found user");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(RoleConstant.DEFAULT_ROLE));
		authorities.add(new SimpleGrantedAuthority(
				RoleConstant.PREFIX_ROLE.concat(user.getRole().getRolename().toUpperCase())));

		UserPrincipal userPrincipal = new UserPrincipal(username, user.getPassword(), authorities);

		addMoreProperties(userPrincipal, user);

		return userPrincipal;
	}

	private void addMoreProperties(UserPrincipal userPrincipal, UserEntity user) {

		if (user.getRole() != null) {
			userPrincipal.setRole(RoleUtil.parseToRoleDTO(user.getRole()));
		}
		if (user.getUserid() != null) {
			userPrincipal.setUserid(user.getUserid());
		}
	}
}
