package free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import free.model.Free;
import jdbc.JdbcUtil;

public class FreeDAO<FreeContent> {
					
	//수정
		public int update(Connection conn,
				int freeNo,String title, String content,Date date) throws SQLException {
			
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE free  "+
				      "SET 	  free_title=?, free_moday=to_char(sysdate,'YYYYMMDD'), free_content=? "+
					  "WHERE  free_no=?";		
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, title);	//?세팅
				//stmt.setTimestamp(2, toTimestamp(date));
				stmt.setString(2, content);
				stmt.setInt(3, freeNo);
				
				System.out.println("-------- FreeDAO" + title + content + freeNo + " -------");
				
				return stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil.close(stmt);
			}
		}//update
		
		//(페이지별로 원하는 특정 범위의)글목록 조회 
		public static List<Free> select(Connection conn, int i, int size) throws SQLException {
	         
	         String sql = "SELECT *FROM (SELECT ROWNUM num, A.* " + 
	                 "          FROM FREE a where free_isShow='Y') " + 
	                 " WHERE NUM BETWEEN ? AND ?";
	                   
	         PreparedStatement stmt = null;
	         ResultSet rs = null;
	         
	         try {
	            //쿼리실행-객체준비,실행
	            stmt = conn.prepareStatement(sql);
	            //?셋팅
	            stmt.setInt(1, i+1); 
	            stmt.setInt(2, i + size); 
	            rs = stmt.executeQuery();
	            
	            //다양한 type의  data의 개수가 여러개
	            List<Free>  result  = new ArrayList<Free>();
	             
	            while( rs.next() ) {
	       
	               result.add(convertFree(rs)); //select해서 return받은 record수만큼 반복
	            }
	            
	            return result;
	         }finally {//자원반납
	            JdbcUtil.close(rs);
	            JdbcUtil.close(stmt);
	         }
	      }//select()1
		
		
		private static Free convertFree(ResultSet rs) throws SQLException {
			
			return 
				new Free(rs.getInt("free_no"),
						 rs.getString("mem_id"), 
						rs.getString("free_title"), 
						rs.getString("free_content"), 
						rs.getDate("free_wrday"), 
						rs.getDate("free_moday"), 
						rs.getInt("free_readcount"));
		}
		
		//전체 게시물수 조회<-DB 테이블의 전체 record수 
		public static int selectCount(Connection conn) throws SQLException{
			String sql = "SELECT  COUNT(*) as cnt "+
					 "FROM FREE " +
					 "WHERE free_isShow='Y'";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		
			if( rs.next() ) {
				return rs.getInt("cnt"); 
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
			
		
		}
		
		//테이블에서  특정글번호 조회
		public Free selectById(Connection conn, int no)  throws SQLException {
			String sql = "SELECT free_no, mem_id, " + 
					     "       free_title, free_content, free_wrday, free_moday, free_readcount "+
						 "FROM free "+
						 "WHERE free_no = ? "+
						 "AND  free_isshow='Y'";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				//쿼리실행-객체준비,실행
				stmt = conn.prepareStatement(sql);
				//?셋팅
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				Free free = null;
				if( rs.next() ) {  //select결과가 존재한다면
					free = convertFree(rs);
				}
				return free;
			}finally {//자원반납
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}//selectById

		//특정글번호 조회수증가
		public void incrementReadCount(Connection conn, int no) 
				throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE  free "+
				      "SET     free_readcount = free_readcount+1 "+
				      "WHERE   free_no = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);	//?세팅		
				stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil.close(stmt);
			}		
		}//incrementReadCount
				
		public Free insert(Connection conn, Free free) throws SQLException {
			String sql = null;
			PreparedStatement pstmt = null;//insert를 실행하기위한 객체
			Statement stmt = null; //마지막 insert된 글번호를 조회하기위한 객체
			ResultSet rs = null;   //마지막 insert된 글번호를 담기위한 객체
			try {
				sql 
				= "INSERT INTO FREE(free_no,mem_id,free_title,free_content,free_wrday,free_moday,free_readcount) " + 
				  "VALUES(sequence_free.nextval,?,?,?,?,?,0)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,free.getId());	//?세팅	
				pstmt.setString(2,free.getTitle());
				pstmt.setString(3,free.getContent());
				pstmt.setTimestamp(4,toTimestamp(free.getWrday()));
				pstmt.setTimestamp(5,toTimestamp(free.getModay()));
				
				int insertedCount = pstmt.executeUpdate();
				if(insertedCount>0) { 
					
			
					stmt = conn.createStatement();
					
					rs = stmt.executeQuery("SELECT sequence_free.currval as free_no FROM dual");
					
					if(rs.next()) {  
						Integer newNum = rs.getInt(1);
						return  new Free(newNum, 
								free.getId(),
								free.getTitle(),
								free.getContent(),
								free.getWrday(), 
								free.getModay(), 0);
					}//if
					
				}//if
			
				return null;
			}finally{
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}//write
		
		//Date타입을 Timestamp으로 변환하는 함수
		private Timestamp toTimestamp(Date wrday) {
			
			return new Timestamp( wrday.getTime() );
		}
		//삭제
		public int delete(Connection conn, int no) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			try {
				sql = "UPDATE free "+
				      "SET 	  free_isShow='N' "+
				      "WHERE  free_no=?";			
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);	//?세팅		
				return stmt.executeUpdate();
			}finally{ //resource 반납
				JdbcUtil.close(stmt);
			}
		}//delete
	
		

	
}
