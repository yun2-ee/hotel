package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//586
//이클래스는 (외부에서) Connection을 구할 때 사용하는 클래스이다.
//외부에서는 connection이 필요할 때 
//ConnectionProvider.getConnection(); 사용하면된다
//Connection 참조변수 = ConnectionProvider.getConnection();
//==>Connection 제공 클래스
public class ConnectionProvider {	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:seadog");
	}

}
