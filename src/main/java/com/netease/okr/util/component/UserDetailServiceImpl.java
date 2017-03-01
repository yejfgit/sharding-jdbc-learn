package com.netease.okr.util.component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.netease.okr.model.entity.security.Role;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.UserService;


/**
 * 实现SpringSecurity的UserDetailsService接口,用于获取用户Detail信息.
 * 
 */
@Component("userDetailServiceImpl")
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

	@Resource(name = "userServiceImpl")
	protected UserService userService;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		User user = userService.getUserByUsername(userName, false);
		if (user == null)
			throw new UsernameNotFoundException("用户" + userName + " 不存在");

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		// 设置用户帐号状态属性
		boolean enabled = user.getIsValid() == 1 ? true : false;
		boolean accountNonExpired = !isAccountExpired(user);
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		org.springframework.security.core.userdetails.UserDetails userdetail = new org.springframework.security.core.userdetails.User(
				user.getName(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);

		return userdetail;
	}

	/**
	 * 获得用户所有角色的权限.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Role> userRoles = user.getUserRoles();
		if (userRoles != null) {
			for (Role role : userRoles) {
				authSet.add(new SimpleGrantedAuthority(role.getName()));
			}
		}
		return authSet;
	}

	/**
	 * 检查登录帐号是否过期
	 * 
	 * @param user
	 * @return
	 */
	private boolean isAccountExpired(User user) {
		boolean isExpired = true;
		if (user.getValidTimeBegin() != null && user.getValidTimeEnd() != null) {
			Date now = new Date();
			long begin = user.getValidTimeBegin().getTime();
			long end = user.getValidTimeEnd().getTime();
			long currentTime = now.getTime();
			if (currentTime >= begin && currentTime <= end) {
				isExpired = false;
			}
		}

		return isExpired;
	}
}
