package member.mem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class PetDAO {
	public  List<Pet>  selectById(Connection conn, String id)
			 throws SQLException {
				
				String sql = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				Pet pet = null; 
				List<Pet> petList = new ArrayList<Pet>();
				
				try {
					sql = "SELECT  pet_no, mem_id, pet_name, " + 
					              "pet_birth, pet_weight, pet_neuter, pet_medical, pet_memo, pet_img " + 
						  " FROM   pet " + 
						  " WHERE  mem_id=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, id);	//?세팅	
					rs = stmt.executeQuery();
					while(rs.next()) { 
						
						pet = new Pet(	rs.getInt("pet_no"),
												rs.getString("mem_id"),
												rs.getString("pet_name"), 
												rs.getDate("pet_birth"),
												rs.getDouble("pet_weight"),
												rs.getString("pet_neuter"),
												rs.getString("pet_medical"), 																														
												rs.getString("pet_memo"),
												rs.getString("pet_img"));
						
						petList.add(pet);
					}
				return petList;
				}finally{ //p593 32라인
					//resource 반납
					JdbcUtil.close(rs);
					JdbcUtil.close(stmt);
				}
			}

	

	public void insert(Connection conn, String str, String pnameArr, String pbirthArr, String weightArr,
			String neuterArr, String medicalArr, String memoArr) throws SQLException{
		String sql = null;
		PreparedStatement stmt = null;
		double w = Double.parseDouble(weightArr);
		Date d = java.sql.Date.valueOf(pbirthArr);
		try { 
			sql = " INSERT INTO pet VALUES(sequence_pet.nextval,?,?,?,?,?,?,?,' ') ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, str);
			stmt.setString(2, pnameArr);
			stmt.setDate(3, d);
			stmt.setDouble(4, w);
			stmt.setString(5, neuterArr);
			stmt.setString(6, medicalArr);
			stmt.setString(7, memoArr);
		
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}



	public void deletePetList(Connection conn, User user) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		try { 
			sql = "DELETE FROM pet WHERE mem_id=? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil.close(stmt);
		}
	}


}
