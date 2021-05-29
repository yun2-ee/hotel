package board.gall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jdbc.JdbcUtil;

public class GallImgDAO {

	public void insert(Connection conn, Integer gall_no, GallImgDTO img) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		int count=0;
		try {
			sql="INSERT INTO img (img_no,gall_no,img_old,img_new) " + 
					"VALUES (sequence_img.nextval,?,?,?)";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			stmt.setString(2,img.getImg_old());
			stmt.setString(3,img.getImg_new());
			count = stmt.executeUpdate();
		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
	}

	public GallImgDTO selectImg(Connection conn, int gall_no) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT *FROM( " + 
					"SELECT rownum r, img_no,gall_no,img_old,img_new " + 
					"FROM img " + 
					"WHERE gall_no=? order by img_no) "+
					"WHERE r=1"; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				String img_new=rs.getString("img_new");
				if(img_new==null||img_new.length()==0) {
					img_new="http://placehold.it/250x200";
				}
				String img_old=rs.getString("img_old");
				if(img_old==null||img_old.length()==0) {
					img_old="";
				}
				return new GallImgDTO(gall_no,rs.getInt("img_no"), img_old,img_new);
			}
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<GallImgDTO> selectById(Connection conn, int gall_no) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql= 	"SELECT rownum r, img_no,gall_no,img_old,img_new " + 
					"FROM img " + 
					"WHERE gall_no=? order by img_no ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			rs = stmt.executeQuery();
			List<GallImgDTO> imgList = new ArrayList<GallImgDTO>();
			while(rs.next()) {
				
				imgList.add(new GallImgDTO(gall_no,rs.getInt("img_no"), rs.getString("img_old"),rs.getString("img_new")));
			}
			return imgList;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public void delete(Connection conn, int no) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		try {
			sql="DELETE " + 
					"FROM img " + 
					"WHERE gall_no=?";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,no);
			stmt.executeUpdate();
		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
	
	}

	public GallImgDTO selectByImgNo(Connection conn, Integer img_no) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql= 	"SELECT rownum r, img_no,gall_no,img_old,img_new " + 
					"FROM img " + 
					"WHERE img_no=? order by img_no ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,img_no);
				rs = stmt.executeQuery();
				if(rs.next()) {
				return new GallImgDTO(0,rs.getInt("img_no"), rs.getString("img_old"),rs.getString("img_new"));
				}else {
					return null;
				}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public void deleteImgNo(Connection conn, Integer img_no) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null; //insert를 실행하기위한 객체
		try {
			sql="DELETE " + 
					"FROM img " + 
					"WHERE img_no=?";
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,img_no);
			stmt.executeUpdate();
		}finally { //resource 반납
			JdbcUtil.close(stmt);
		}
		
	}

	
	
	
	
	
	
	/*
	public GallImgDTO selectById(Connection conn, int gall_no) {
		String sql=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			sql="SELECT img_no,gall_no,img_name,img_path FROM img where gall_no=?"; //여기에서 setString으로 값을 넣으면 'value'로들어가기 때문에
																												//해당컬럼명이아닌 문자열'value'로 인식되어 쿼리문 작동X된다
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,gall_no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new GallImgDTO(rs.getInt("gall_no"), rs.getInt("img_no"), rs.getString("img_name"), rs.getString("img_path"));
			}else {
			return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	*/
}
