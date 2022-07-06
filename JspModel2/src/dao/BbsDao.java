package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;
import dto.CategoryDto;
import dto.DetailCodeDto;

public class BbsDao {
	
	private static BbsDao dao = new BbsDao();
	
	public BbsDao() {
		
	}
	
	public static BbsDao getInstance() {
		return dao;
	}
	
	/**
	 * 전체 글 목록 받아오기
	 * @return
	 */
	public List<BbsDto> getBbslist() {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH, "
				   + "		TITLE, CONTENT, WDATE, DEL, READCOUNT"
				   + " ,CATEGORY_CD, C.CODE_TITLE\n"
				   + " FROM BBS B, CATEGORYCD C\n"
				   + " WHERE B.CATEGORY_CD = C.P_CODE\n"
				   + "ORDER BY REF DESC, STEP ASC";
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("get BBS : conn success");
			psmt = conn.prepareStatement(sql);
			System.out.println("get BBS : psmt success");
			rs = psmt.executeQuery();
			System.out.println("get BBS : rs success");

			BbsDto dto = null;
			while(rs.next()) {
				dto = new BbsDto(rs.getInt(1),	//seq
									rs.getString(2),//id
									rs.getInt(3),	//reg
									rs.getInt(4),	//step
									rs.getInt(5),	//depth
									rs.getString(6),//title
									rs.getString(7),//content
									rs.getString(8),
									rs.getInt(9),
									rs.getInt(10),
									rs.getString(11),
									rs.getString(12)
									);
				list.add(dto);
				
				System.out.println("get bbs success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get bbs fail");
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		

	
		return list;
	}
	
	
	/**
	 * 글 작성
	 * @param dto
	 * @return
	 */
	public boolean writeBbs(BbsDto dto) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		// increment를 두개쓸수는 없으니
		String sql = "INSERT INTO BBS(ID, REF, STEP, DEPTH,"
				 	+ "				TITLE, CONTENT, WDATE, "
				 	+ "				DEL, READCOUNT)"
				 	+ "VALUES(?,"
				 	+ "(SELECT IFNULL(MAX(REF), 0) + 1 FROM BBS B)"
				 	+ ", 0, 0, ?, ?, NOW(), 0, 0)";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("get BBS : conn success");
			psmt = conn.prepareStatement(sql);
			System.out.println("get BBS : psmt success");
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());

			count = psmt.executeUpdate();
			System.out.println("3/3 writeBbs success");
			
		} catch (Exception e) {
			System.out.println("Write Fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0?true:false;
	}
	
	
	public boolean updateBbs(String title, String Content, int seq) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		// increment를 두개쓸수는 없으니
		String sql = "UPDATE BBS SET"
				 	+ " TITLE = ?"
				 	+ ", CONTENT = ?"
				 	+ ", WDATE = NOW()"
				 	+ "\nWHERE SEQ = ?";
		

		System.out.println("title:::::::::"+title);
		System.out.println("title:::::::::"+Content);
		System.out.println("title:::::::::"+seq);
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();
			System.out.println("updateBbs: conn success");
			psmt = conn.prepareStatement(sql);
			System.out.println("updateBbs: psmt success");
			
			psmt.setString(1, title);
			psmt.setString(2, Content);
			psmt.setInt(3, seq);

			count = psmt.executeUpdate();
			System.out.println("updateBbs success");
			
		} catch (Exception e) {
			System.out.println("Write Fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0?true:false;
	}
	
	
	/**
	 * 글 검색
	 * @param String choice, String search
	 * @return
	 */
	public List<BbsDto> getBbsSearchList(String choice, String search) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = "WHERE TITLE LIKE '%" + search + "%'";
		} else if(choice.equals("content")) {
			sWord = "WHERE CONTENT LIKE '%" + search + "%'";
		} else if(choice.equals("writer")) {
			sWord = "WHERE ID = '" + search + "'";
		} 
		
		String sql = "SELECT SEQ, ID, REG, STEP, DEPTH,"
				   + " TITLE, CONTENT, WDATE, DEL, READCOUNT\n"
				   + " FROM BBS\n" ;
			   sql += sWord;
			   sql += "ORDER BY REF DESC, STEP ASC";
		System.out.println(sql);
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("get BBS : conn success");
			psmt = conn.prepareStatement(sql);
			System.out.println("get BBS : psmt success");
			rs = psmt.executeQuery();
			System.out.println("get BBS : rs success");

			BbsDto dto = null;
			while(rs.next()) {
				dto = new BbsDto(rs.getInt(1),	//seq
									rs.getString(2),//id
									rs.getInt(3),	//reg
									rs.getInt(4),	//step
									rs.getInt(5),	//depth
									rs.getString(6),//title
									rs.getString(7),//content
									rs.getString(8),
									rs.getInt(9),
									rs.getInt(10));
				list.add(dto);
				
				System.out.println("get bbs success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get bbs fail");
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		

	
		return list;
	}
	
	/**
	 * 글의 총수
	 * @param choice
	 * @param search
	 * @return len
	 */
	public int getAllBbs(String choice, String search) {
		
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		
		String sql = "SELECT count(*) FROM BBS\n";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = "WHERE TITLE LIKE '%" + search + "%'";
		} else if(choice.equals("content")) {
			sWord = "WHERE CONTENT LIKE '%" + search + "%'";
		} else if(choice.equals("writer")) {
			sWord = "WHERE ID = '" + search + "'";
		} 
		
		
		sql += sWord;
		
		// return값
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("get BBS : conn success");
			psmt = conn.prepareStatement(sql);
			System.out.println("get BBS : psmt success");
			rs = psmt.executeQuery();
			System.out.println("get BBS : rs success");

			if(rs.next()) {
				len = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get bbs fail");
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		
		return len;
	}

	
	
	/**
	 * getBbsPageList : 페이지 
	 * @param choice
	 * @param search
	 * @param pageNumber
	 * @return
	 */
	public List<BbsDto> getBbsPageList(String choice, String search, int pageNumber) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		/******************************************************/
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = "AND TITLE LIKE '%" + search + "%'\n";
		} else if(choice.equals("content")) {
			sWord = "AND CONTENT LIKE '%" + search + "%'\n";
		} else if(choice.equals("writer")) {
			sWord = "AND ID = '" + search + "'\n";
		} 
		
		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH,"
				   + " TITLE, CONTENT, WDATE, DEL, READCOUNT,CATEGORY_CD, CODE_TITLE\n"
				   + " FROM " ;

		sql += "( SELECT ROW_NUMBER() OVER (ORDER BY REF DESC, STEP ASC) AS RNUM,\n"
				+ "  		        SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT, CATEGORY_CD, CODE_TITLE\n"
				+ "  		   FROM BBS B, CATEGORYCD C \n"
				+ " WHERE DEL != 1 AND B.CATEGORY_CD = C.P_CODE\n";
		sql += sWord;
		sql += "ORDER BY REF DESC, STEP ASC) A \n";
		sql += "WHERE RNUM BETWEEN ? AND ?";
		
		//System.out.println(sql);
		
		int start, end;
		start = 1 + 10 * pageNumber;
		end = 10 + 10 * pageNumber;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("get BBS : conn success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			
			
			System.out.println("get BBS : psmt success");
			rs = psmt.executeQuery();
			System.out.println("get BBS : rs success");

			BbsDto dto = null;
			while(rs.next()) {
				dto = new BbsDto(rs.getInt(1),	//seq
									rs.getString(2),//id
									rs.getInt(3),	//reg
									rs.getInt(4),	//step
									rs.getInt(5),	//depth
									rs.getString(6),//title
									rs.getString(7),//content
									rs.getString(8),
									rs.getInt(9),
									rs.getInt(10),
									rs.getString(11),
									rs.getString(12)
									);
				list.add(dto);
				
				System.out.println("get bbs success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get bbs fail");
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		

	
		return list;
	}
	
	
	
	/**
	 * getBbsDto 글 얻기
	 * @param seq
	 * @return
	 */
	public BbsDto getBbsDto(int seq) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT SEQ, ID, REF, STEP, DEPTH,\n"
				   + " TITLE, CONTENT, WDATE, DEL, READCOUNT\n"
				   + " FROM BBS\n" 
				   + "WHERE SEQ = ?";
		
				   
		BbsDto dto = null;   
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 get Detail : conn success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 get Detail : psmt success");
			rs = psmt.executeQuery();
			System.out.println("3/3 get Detail: rs success");

			
			while(rs.next()) {
				dto = new BbsDto(rs.getInt(1),	//seq
									rs.getString(2),//id
									rs.getInt(3),	//reg
									rs.getInt(4),	//step
									rs.getInt(5),	//depth
									rs.getString(6),//title
									rs.getString(7),//content
									rs.getString(8),
									rs.getInt(9),
									rs.getInt(10));				
				System.out.println("get Detail success");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get bbs fail");
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		
		return dto;
	}
	
	
	/**
	 * addReadCount
	 * @param seq
	 */
	public void addReadCount(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql = "UPDATE BBS SET READCOUNT = READCOUNT+1 WHERE SEQ = ?";
				   
		try {
			conn = DBConnection.getConnection();			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			psmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
	}
	
	
	/**
	 * addReadCount
	 * @param seq
	 */
	public boolean delBbs(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		String sql = "UPDATE BBS SET DEL = 1 WHERE SEQ = ?";
				   
		try {
			conn = DBConnection.getConnection();			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			count = psmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count > 0?true:false;
	}
	
	
	/**
	 * 답글 달기
	 * @param seq 부모 글번호
	 * @param bbs 새로운 답글
	 * @return
	 */
	public boolean answer(int seq, BbsDto bbs) {
		
		// 1. update
		String updateSql = " UPDATE BBS \n"
						 + " SET STEP = STEP + 1 \n"
						 + " WHERE REF = (SELECT REF FROM (SELECT REF FROM BBS A WHERE SEQ=?) A)"
						 + "  AND STEP > (SELECT STEP FROM BBS B WHERE SEQ = ?) B);";
		
		// 2. insert
		String insertSql = "INSERT INTO BBS(ID, "
						 + "				REF, STEP, DEPTH,"
						 + "				TITLE, CONTENT, WDATE, DEL, READCOUNT)"
						 + "		 VALUES(?, "
						 + "				(SELECT REF FROM BBS A WHERE SEQ=?), "
						 + "				(SELECT STEP FROM BBS B WHERE SEQ=?) + 1, "
						 + "				(SELECT DEPTH FROM BBS C WHERE SEQ=?) + 1,"
						 + "				?, ?, NOW(), 0, 0);";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
				
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false); // default : false;
			
			// 1. update
			psmt = conn.prepareStatement(updateSql);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			
			count = psmt.executeUpdate();
			
			// 2. psmt 초기화
			psmt.clearParameters();
			
			// 3. insert
			psmt = conn.prepareStatement(insertSql);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getTitle());
			psmt.setString(5, bbs.getContent());
			
			count += psmt.executeUpdate();
			
			// 4.commit;
			conn.commit();
			
		} catch (Exception e) {		
			// rollback;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DBClose.close(conn, psmt, null);
		}
		
		return count > 0? true:false;
	}
	
	
	/**
	 * CategoryDto 상위 카테고리
	 * @param seq
	 * @return
	 */
	public List<CategoryDto> getCategory() {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT P_CODE, CODE_TITLE, USE_YN\n"
				   + " FROM CATEGORYCD\n" 
				   + "WHERE USE_YN = 'Y'";
		
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		CategoryDto dto = null;   
		try {
			conn = DBConnection.getConnection();			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto = new CategoryDto(rs.getString(1),	//seq
									rs.getString(2),//id
									(rs.getString(3)).charAt(0));				

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		
		return list;
	}
	

	/**
	 * DetailCodeDto 하위 카테고리
	 * @param seq
	 * @return
	 */
	public List<DetailCodeDto> getDetailCategory(String upcode) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		/* 만약 USE_YN 값 타입이 바뀐다면? 값을 포함하는 쿼리를 모두찾아서 수정해야 한다 ! */
		String sql = "SELECT UPCODE, P_CODE, CODE_TITLE, USE_YN\n"
				   + " FROM DETAILCD \n" 
				   + "WHERE USE_YN = '1'"
				   + "  AND UPCODE = ?";
		
		
		List<DetailCodeDto> list = new ArrayList<DetailCodeDto>();

		DetailCodeDto dto = null;   
		try {
			conn = DBConnection.getConnection();			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, upcode);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto = new DetailCodeDto(rs.getString(1),
									rs.getString(2),	//seq
									rs.getString(3),//id
									(rs.getString(4)).charAt(0));				
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt,  rs);
		}
		
		return list;
	}
}
