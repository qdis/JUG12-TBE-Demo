package ro.dialogdata.jug.web.auth;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ro.dialogdata.jug.common.enums.Role;

public class AuthenticationUtils {

	public static boolean isLoggedIn() {
		boolean result = false;
		Authentication authetication = SecurityContextHolder.getContext()
				.getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authetication
				.getAuthorities();
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().toUpperCase().contains(Role.USER.name())) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static String getCurrentUsername(){
		Authentication authetication = SecurityContextHolder.getContext()
				.getAuthentication();
		return authetication.getName();
	}
	
	public static void logout() {
		   SecurityContextHolder.clearContext();
		   ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().invalidate();
	}
}
