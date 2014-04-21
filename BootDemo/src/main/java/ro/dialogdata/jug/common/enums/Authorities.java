package ro.dialogdata.jug.common.enums;

import org.springframework.security.core.GrantedAuthority;

import ro.dialogdata.jug.common.model.User;

public class Authorities {

	public static GrantedAuthority USER_AUTHORITY = new UserAuthority();
	
	public static GrantedAuthority forUser(User user){
		GrantedAuthority authority =null;
		switch(user.getRole()){
		case USER:
			authority = USER_AUTHORITY;
			break;
		}
		return authority;
	}
	
	public static class UserAuthority implements GrantedAuthority{

		private static final long serialVersionUID = -7323618131041082688L;

		@Override
		public String getAuthority() {
			return Role.USER.name();
		}

	}
	

}
