package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhonebookDAOOracle implements PhonebookDAO{
	Connection conn;
	
	public PhonebookDAOOracle() {
		// 접속하는 주소가 고정일 경우 기본생성자에서 처리		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager
					.getConnection("jdbc:oracle:thin:@172.16.15.66:1521:xe","system","1234");
			System.out.println(conn);
		}catch (Exception e) {
			System.out.println("Connection 객체 생성 오류!!");
			e.printStackTrace();
		}
		
	}
	
	public PhonebookDAOOracle(String driver, String url, String username, String password) {
		// 접속하는 주소가 고정이 아닐 때는 필드 생성자를 활용		
		try{
			Class.forName(driver);
			conn=DriverManager
					.getConnection(url,username,password);
			System.out.println(conn);
		}catch (Exception e) {
			System.out.println("Connection 객체 생성 오류!!");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int save(PhonebookVO pb) {
		String sql="insert into phonebook values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, pb.getId());
			ps.setString(2,pb.getName());
			ps.setString(3,pb.getHp());
			ps.setString(4,pb.getEmail());
			ps.setString(5,pb.getMemo());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public List<PhonebookVO> findAll() {
		String sql="select * from phonebook"; 
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<PhonebookVO> list = new ArrayList<PhonebookVO>();
			while(rs.next()) {
				PhonebookVO pb = new PhonebookVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("hp"),
						rs.getString("email"),
						rs.getString("memo")
						);
				list.add(pb);
			}
			rs.close(); ps.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public PhonebookVO findById(int id) {
		String sql="select * from phonebook where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) {
				PhonebookVO pb = new PhonebookVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("hp"),
						rs.getString("email"),
						rs.getString("memo")
						);
				rs.close(); ps.close();
				return pb;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int update(PhonebookVO pb) {
		String sql="update phonebook set name=?, hp=?, email=?, memo=? where id=?";
		// update는 변경하지 않는 값은 null이므로 기존의 값으로 대체한다
		PhonebookVO oldpb = findById(pb.getId());
		if(pb.getName()==null) pb.setName(oldpb.getName());
		if(pb.getHp()==null) pb.setHp(oldpb.getHp());
		if(pb.getEmail()==null) pb.setEmail(oldpb.getEmail());
		if(pb.getMemo()==null) pb.setMemo(oldpb.getMemo());
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setString(2, pb.getHp());
			ps.setString(3, pb.getEmail());
			ps.setString(4, pb.getMemo());
			ps.setInt(5, pb.getId());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		String sql="delete from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
