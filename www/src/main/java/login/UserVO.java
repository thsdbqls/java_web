package login;

public class UserVO {
	private int id;
	private String username;
	private String password;
	private String hp;
	private String email;
	private String nickname;

	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public UserVO(int id, String username, String password, String hp, String email, String nickname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.hp = hp;
		this.email = email;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", username=" + username + ", password=" + password + ", hp=" + hp + ", email="
				+ email + ", nickname=" + nickname + "]";
	}
	
}
