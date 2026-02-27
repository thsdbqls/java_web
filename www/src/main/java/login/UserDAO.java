package login;

import java.util.List;

public interface UserDAO {
	public int save(UserVO user);
	public List<UserVO> findAll();
	public UserVO findById(int id);
	public UserVO findByUsername(String username);
	public int update(UserVO user);
	public int delete(String username);

}
