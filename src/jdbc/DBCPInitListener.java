package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


//p583
//poolConfig 컨텍스트 초기화 파라미터를 이용해서
//Connection pool을 초기화하는 클래스
public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//웹 어플리케이션을 초기화 할때 호출한다.
		String poolConfig = 
				sce.getServletContext().getInitParameter("poolConfig");
		//지정한 이름을 갖는 컨텍스트 초기화 파라미터의 값을 리턴해준다 .
		//자바 코드로 getServletContext().getInitParameter("컨텍스트 파라미터 이름")을 통해 ServletContext 객체를 가져와서 해당 초기화 매개변수를 사용한다.


		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");

			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			//DBCP가 커넥션풀에 커넥션을 보관할때 사용하는 PoolableConnectionFactory 생성
			//실제로 내부적으로 커넥션을 담고있고 관리하는데 기능을 제공함
			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			String validationQuery = prop.getProperty("validationQuery");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				//유효성 검사
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			//유휴 커넥션 검사주기
			poolConfig.setTestWhileIdle(true);
			//풀에있는 커넥션 유효성 검사 유무 설정
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(minIdle);
			//커넥션 최소갯수 설정
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			poolConfig.setMaxTotal(maxTotal);
			//커넥션 최대 갯수 설정
			
			//커넥션 풀 생성 . 인자로는 위에서 생성한 PoolableConnectionFactory와 GenericObjectPoolConfig를사용
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			//커넥션풀을 연결
			poolableConnFactory.setPool(connectionPool);
			
			//커넥션 풀을 제공하는 jdbc드라이버를 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:seadog");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
			//위에서 커넥션 풀드라이버에 생성한 커넥션풀을 등록함
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if (value == null) return defaultValue;
		return Integer.parseInt(value);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//웹어플리케이션 종료할때 호출
	}

}
