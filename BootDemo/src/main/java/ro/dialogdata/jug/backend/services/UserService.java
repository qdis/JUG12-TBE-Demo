package ro.dialogdata.jug.backend.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.dialogdata.jug.backend.repos.UserRepository;
import ro.dialogdata.jug.common.enums.Authorities;
import ro.dialogdata.jug.common.model.User;

/**
 * UserService implementing UserDetailsService ( required interface for Spring Security )
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Looks up User using userRepository and creates a UserDetails Object 
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsernameIgnoreCase(username);
		if (user != null) {
			ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(Authorities.forUser(user));
			UserDetails details = new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true,
					true, authorities);
			return details;
		} else {
			throw new UsernameNotFoundException(username);
		}
	}

}
