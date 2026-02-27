package login;

public class LoginService {
	
	UserDAO dao;
	
	public LoginService() {
		dao = new UserDAOH2();
	}

	public Boolean isLogin(String username, String password) {
		UserVO user = dao.findByUsername(username);
		System.out.println("service:"+ user);
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public boolean register(RegisterVO registerVO) {
		UserVO user = new UserVO();
		user.setUsername(registerVO.getUsername());
		user.setPassword(registerVO.getPassword());
		user.setHp(registerVO.getHp());
		user.setEmail(registerVO.getEmail());
		user.setNickname(registerVO.getNickname());
		boolean result = dao.save(user)>0 ? true : false;
		return result;
	}

}
