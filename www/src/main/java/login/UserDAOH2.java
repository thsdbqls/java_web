package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.User;

// 싱글톤 객체(하나만 생성하는 객체 정의)
public class UserDAOH2 implements UserDAO {
	
	//private static UserDAOH2 instance;  // 자기 자신을 생성한다. 
	
	private static Connection conn;
	
	// instance 가져오는 함수
	/*
	public static UserDAOH2 getInstance() {
		if(instance == null) {  // 생성이 안 되어 있으면
			instance = new UserDAOH2();
		}
		return instance;
	}
	*/
	
	public UserDAOH2() {
		if(conn == null) {
			try {
			Class.forName("org.h2.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int save(UserVO user) {
		try {
	    String sql = "INSERT INTO users (username, password, hp, email, nickname) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, user.getUsername());
	    ps.setString(2, user.getPassword());
	    ps.setString(3, user.getHp());
	    ps.setString(4, user.getEmail());
	    ps.setString(5, user.getNickname());

	    int result = ps.executeUpdate();
	    ps.close();
	    return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<UserVO> findAll() {
		try {
	    String sql = "select * from users";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    List<UserVO> list = new ArrayList<UserVO>();
	    while(rs.next()) {
	    	list.add(new UserVO(
	    			rs.getInt(1),
	    			rs.getString(2),
	    			rs.getString(3),
	    			rs.getString(4),
	    			rs.getString(5),
	    			rs.getString(6)));
	    }
	    rs.close(); ps.close();
	    return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserVO findById(int id) {
		try {
		String sql = "select * from users where id = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if(rs.next()) {
	    	UserVO user =new UserVO(
	    			rs.getInt(1),
	    			rs.getString(2),
	    			rs.getString(3),
	    			rs.getString(4),
	    			rs.getString(5),
	    			rs.getString(6));
	    	rs.close(); ps.close();
		    return user;
	    }
	    return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserVO findByUsername(String username) {
		try {
			String sql = "select * from users where username = ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, username);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	UserVO user =new UserVO(
		    			rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getString(4),
		    			rs.getString(5),
		    			rs.getString(6));
		    	rs.close(); ps.close();
			    return user;
		    }
		    return null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public int update(UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

}
