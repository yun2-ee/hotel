package qna;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import qna.model.QnaContent;
import qna.model.WriterDTO;

public class QnaContentDAO {
	
	public QnaContent selectById(Connection conn, int no)throws SQLException{
		String sql = "SELECT QNA_NO, QNA_CONTENT, QNA_TITLE, QNA_WRDAY, MEM_ID "
					+ "FROM QNA "
					+ "WHERE QNA_NO = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			System.out.println("QnaContent"+sql);
			rs = stmt.executeQuery();
			QnaContent content = null;
			if(rs.next()) {
				content = new QnaContent(
							rs.getInt("QNA_NO"),
							rs.getString("QNA_CONTENT"), 
							rs.getString("QNA_TITLE"),
							rs.getDate("QNA_WRDAY"),
							new WriterDTO(rs.getString("MEM_ID"))
				);
			}
			return content;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public QnaContent insert(Connection conn, QnaContent content) throws SQLException {
		System.out.println("public QnaContent insert() 진입");
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "insert into qna(QNA_NO, MEM_ID, QNA_TITLE, QNA_CONTENT, QNA_WRDAY) values(?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			System.out.println("QnaContentDAO의 stmt0 = "+stmt);
			System.out.println("QnaContentDAO의 content.getNo()="+content.getNo());
			System.out.println("QnaContentDAO의 content.getWriter().getId()="+content.getWriter().getId());
			System.out.println("QnaContentDAO의 content.getTitle()="+content.getTitle());
			System.out.println("QnaContentDAO의 content.getContent()="+content.getContent());
			System.out.println("QnaContentDAO의 content.getWrday()="+content.getWrday());
			
			stmt.setInt(1, content.getNo());
			stmt.setString(2, content.getWriter().getId());
			stmt.setString(3, content.getTitle());
			stmt.setString(4, content.getContent());
			stmt.setDate(5, (Date)content.getWrday());
			System.out.println("QnaContentDAO의 stmt1 = "+stmt);
			int insertedCount = stmt.executeUpdate();
			System.out.println("QnaContent insert()의  insertedCount ="+insertedCount);
			if(insertedCount>0) {
				System.out.println("if(insertedCount>0)  content="+content);
				return content;
				
			}else {
				System.out.println("QnaContentDAO의 else문 안");
				return null;
			}
		}finally {
			JdbcUtil.close(stmt);
		}
	}
}
