package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import qna.model.QnaDTO;
import qna.model.WriterDTO;
import qna.service.QnaWriterRequest;

public class QnaDAO {
	
	/* Q&A�Խ��� �� ����Ʈ */
	public List<QnaDTO> select(Connection conn, int startRow, int size, String qnaseach)throws SQLException {
		System.out.println(startRow);
		System.out.println(size);
		System.out.println(qnaseach);
		
		String sql = "SELECT QNA_NO, QNA_TITLE, MEM_ID, QNA_WRDAY, QNA_RES_CHECK, QNA_READCOUNT, QNA_RES, QNA_CONTENT FROM (SELECT ROWNUM AS RNUM, A.* FROM  QNA A ";
		if(qnaseach!=null && !"".equals(qnaseach)) {
			sql +="WHERE (QNA_TITLE = ? or MEM_ID = ?) ";
		}
		sql += ") ";
		sql += "WHERE (RNUM BETWEEN ? AND ? AND QNA_ISSHOW='Y') ";
		if(qnaseach!=null && !"".equals(qnaseach)) {
			sql +=" AND (QNA_TITLE = ? or MEM_ID = ?) ";
		}
		sql += "ORDER BY QNA_WRDAY desc";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println(sql);
		try {
			stmt = conn.prepareStatement(sql);
			
			if(qnaseach!=null && !"".equals(qnaseach)) {
				stmt.setString(1, qnaseach);
				stmt.setString(2, qnaseach);
				stmt.setInt(3, startRow);
				stmt.setInt(4, size);
				stmt.setString(5, qnaseach);
				stmt.setString(6, qnaseach);
			}else {
				stmt.setInt(1, startRow);
				stmt.setInt(2, size);
			}
			rs = stmt.executeQuery();
			List<QnaDTO> result = new ArrayList<QnaDTO>();
			
			while(rs.next()) {
				result.add(converQnaDTO(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	private QnaDTO converQnaDTO(ResultSet rs)throws SQLException {
		return 
			new QnaDTO(rs.getInt("QNA_NO"),
				new WriterDTO(rs.getString("MEM_ID")),
				rs.getString("QNA_TITLE"),
				rs.getString("QNA_RES_CHECK"),
				rs.getDate("QNA_WRDAY"),
				rs.getInt("QNA_READCOUNT"),
				rs.getString("QNA_RES"),
				rs.getString("QNA_CONTENT"));
	}
	
	public int selectCount(Connection conn)throws SQLException{
		String sql = "SELECT count(*) as cnt FROM qna";
		System.out.println(sql);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}
	public QnaDTO selectById(Connection conn, int no)throws SQLException {
		String sql = "SELECT QNA_NO, MEM_ID, QNA_TITLE, QNA_RES_CHECK, QNA_TITLE, QNA_WRDAY, QNA_READCOUNT, QNA_RES, QNA_CONTENT "
					+ "FROM QNA "
					+ "WHERE QNA_NO =?";
		System.out.println(sql);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			QnaDTO qnaDto = null;
			if(rs.next()) {
				System.out.println("1111");
				qnaDto = converQnaDTO(rs);
				System.out.println("2222");
			}
			
			System.out.println(qnaDto);
			return qnaDto;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public void incrementReadCount(Connection conn, int no)throws SQLException{
		String sql = "UPDATE QNA SET QNA_READCOUNT = QNA_READCOUNT+1 WHERE QNA_NO = ?";
		System.out.println(sql);
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);;
			stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
				
	}
	
	//�۾��� ��û
	public QnaDTO insert(Connection conn, QnaDTO qnaDto)throws SQLException {
		String sql = "INSERT INTO QNA(QNA_NO, MEM_ID, QNA_TITLE, QNA_CONTENT, QNA_WRDAY, QNA_RES_CHECK, QNA_READCOUNT) "
					+"VALUES(sequence_qna.nextval,?,?,?,?,?,0) ";
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt0="+pstmt);
			System.out.println("qnaDto="+qnaDto);
			
			pstmt.setString(1, qnaDto.getWriter().getId());
			pstmt.setString(2, qnaDto.getTitle());
			pstmt.setString(3, qnaDto.getContent());
			pstmt.setTimestamp(4, toTimestamp(qnaDto.getWrday()));
			pstmt.setString(5, qnaDto.getCheck());
			
			
			System.out.println("pstmt1="+pstmt);
			
			int insertedCount = pstmt.executeUpdate();
			System.out.println("insertedCount ="+insertedCount);
			if(insertedCount>0) {
				stmt = conn.createStatement();
				//rs = stmt.executeQuery("SELECT sequence_qna.currval as qna_no FROM dual");
				rs = stmt.executeQuery("SELECT nvl(max(QNA_NO),0)  FROM QNA");
				if(rs.next()) {
					int no  = rs.getInt(1);
					System.out.println("no="+no);
					
					//return new QnaDTO(no, qnaDto.getWriter(), qnaDto.getTitle(), qnaDto.getCheck(), qnaDto.getWrday(),0, qnaDto.getRes(), qnaDto.getContent());
					QnaDTO  qnadto= new QnaDTO(no, qnaDto.getWriter(), qnaDto.getTitle(), qnaDto.getCheck(), qnaDto.getWrday(),0, qnaDto.getRes(), qnaDto.getContent());
					return qnadto;
				}
			
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date wrday) {
		return new Timestamp( wrday.getTime());
	}

	/*�Խù� ���� ���*/
	public int delete(Connection conn, int no) throws SQLException {

		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE QNA SET QNA_ISSHOW='N' WHERE QNA_NO=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	public int update(Connection conn, int qnaNumber, String res) throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE QNA SET QNA_RES_CHECK='답변완료', QNA_RES=? WHERE QNA_NO=?";
			System.out.println("�Խñ� �亯�ޱ��� ������ =      "+sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, res);
			stmt.setInt(2, qnaNumber);
			return stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	//�Խù� ����
	public int update2(Connection conn, int qnaNumber, String title, String content)throws SQLException {
		String sql = null;
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE QNA SET QNA_TITLE=?, QNA_CONTENT=? WHERE QNA_NO=?";
			System.out.println("�Խñ� �亯�ޱ��� ������ =      "+sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setInt(3, qnaNumber);
			return stmt.executeUpdate();
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	
}