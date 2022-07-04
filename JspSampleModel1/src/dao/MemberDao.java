package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = new MemberDao();
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		return dao;
	}
	
	public boolean addMember(MemberDto dto) {
		
		String sql = " insert into member(id, pwd, name, email, auth) "
							+ "	   values(?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}
	
	
	public boolean chkMember(String id) {
		
		boolean isMember = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		int total = 0;
		MemberDto mem = new MemberDto();
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			psmt.setString(1, id);

			//System.out.println(psmt);
			ResultSet rs = psmt.executeQuery();
			rs.last();
			total = rs.getRow();
			//rs.beforeFirst();
	
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		isMember = (total > 0) ? true : false;
		return isMember;	
		
	}
	
	
	// member dto return
	public MemberDto getMember(String id) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		
		MemberDto mem = new MemberDto();
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			ResultSet rs = psmt.executeQuery();
			rs.next();
			
			// user에 담기
			
			mem.setId(rs.getString("ID"));
			mem.setName(rs.getString("NAME"));
			mem.setPwd(rs.getString("PWD"));
			mem.setEmail(rs.getString("EMAIL"));
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return mem;	
		
		
	}
	
	
	/**
	 * Login 
	 * @param dto
	 * @return MemberDto
	 */
	public MemberDto login(MemberDto dto) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT ID, NAME, EMAIL, AUTH" +
					 "FROM MEMBER" +
				     "WHERE ID = ? AND PWD = ?";
		
		MemberDto mem = null;
		
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());

			rs = psmt.executeQuery();

			
			if(rs.next()) {
				String id	= rs.getString(1);
				String name = rs.getString(1);
				String email = rs.getString(3);
				int auth = rs.getInt(4);
				
				mem = new MemberDto(id, null, name, email, auth);
			}
			
			
		} catch (SQLException e) {			
			System.out.println("Login Fail!");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return mem;
	}
}





