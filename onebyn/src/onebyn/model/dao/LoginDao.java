package onebyn.model.dao;

import static onebyn.common.JDBCtem.close;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import onebyn.model.vo.Member;

public class LoginDao {

	private Properties p = new Properties();

	public LoginDao() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginDao(ServletContext s) {
		// TODO Auto-generated constructor stub
		try {
			p.load(s.getResourceAsStream("/WEB-INF/resource/mquery.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member loginUser(Connection conn, String id, String pw) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement pst = null;
		String sql = p.getProperty("loginuser");
		Member m = new Member();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pw);
			rs = pst.executeQuery();
			boolean r = rs.next();
			System.out.println("rs.next() : "+r);
			//Member 객체에 넣어야될듯?
			
			if(r) {
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pwd"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pst);
		}
		System.out.println("반환하기 전에 출력 ! : "+m);
		return m;
	}

}
