package vn.com.fpt.clt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.com.fpt.clt.dto.UserDto;
import vn.com.fpt.clt.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userDto = userService.findByUsername(username);
		if (null == userDto) {
			throw new UsernameNotFoundException("Username " + username + " does not exist in the database");
		}
		
		return userDto;
	}

}
