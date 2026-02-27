package news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOH2 implements NewsDAO {

    private Connection conn;
    public NewsDAOH2() {
    	// 접속하는 주소가 고정일 경우 기본생성자에서 처리		
    			try{
    				Class.forName("org.h2.Driver");
    				conn=DriverManager
    						.getConnection("jdbc:h2:tcp://localhost/~/test","sa", "");
    				System.out.println(conn);
    			}catch (Exception e) {
    				System.out.println("Connection 객체 생성 오류!!");
    				e.printStackTrace();
    			}
	}
    // 생성자에서 Connection 주입
    public NewsDAOH2(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int save(News news){

        String sql = "INSERT INTO NEWS " +
                "(MAIN_IMAGE, REPORTER_NAME, PRESS_NAME, TITLE, CONTENT, CREATED_AT, UPDATED_AT) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, news.getMainImage());
            pstmt.setString(2, news.getReporterName());
            pstmt.setString(3, news.getPressName());
            pstmt.setString(4, news.getTitle());
            pstmt.setString(5, news.getContent());

            return pstmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
            return 0;
		}
    }

    @Override
    public List<News> findAll() {

        String sql = "SELECT * FROM NEWS ORDER BY CREATED_AT DESC";
        List<News> list = new ArrayList<>();

        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(resultSetToNews(rs));
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}

        return list;
    }

    @Override
    public News findById(Long id) {

        String sql = "SELECT * FROM NEWS WHERE ID = ?";
        News news = null;

        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
        	ResultSet rs = pstmt.executeQuery();
          
            if (rs.next()) {
                   news = resultSetToNews(rs);
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}

        return news;
    }

    @Override
    public int update(News news) {

        String sql = "UPDATE NEWS SET " +
                "MAIN_IMAGE = ?, " +
                "REPORTER_NAME = ?, " +
                "PRESS_NAME = ?, " +
                "TITLE = ?, " +
                "CONTENT = ?, " +
                "UPDATED_AT = CURRENT_TIMESTAMP " +
                "WHERE ID = ?";

        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, news.getMainImage());
            pstmt.setString(2, news.getReporterName());
            pstmt.setString(3, news.getPressName());
            pstmt.setString(4, news.getTitle());
            pstmt.setString(5, news.getContent());
            pstmt.setLong(6, news.getId());
            return pstmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
        	return 0;
		}
    }

    // 5️⃣ 삭제
    @Override
    public int delete(Long id) {

        String sql = "DELETE FROM NEWS WHERE ID = ?";

        try {       
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            return pstmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return 0;
    }

  
    private News resultSetToNews(ResultSet rs){

    	try {
        News news = new News();

        news.setId(rs.getLong("ID"));
        news.setMainImage(rs.getString("MAIN_IMAGE"));
        news.setReporterName(rs.getString("REPORTER_NAME"));
        news.setPressName(rs.getString("PRESS_NAME"));
        news.setTitle(rs.getString("TITLE"));
        news.setContent(rs.getString("CONTENT"));

        Timestamp created = rs.getTimestamp("CREATED_AT");
        Timestamp updated = rs.getTimestamp("UPDATED_AT");

        if (created != null) {
            news.setCreatedAt(created.toLocalDateTime());
        }

        if (updated != null) {
            news.setUpdatedAt(updated.toLocalDateTime());
        }

        return news;
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return null;
    }

	
}