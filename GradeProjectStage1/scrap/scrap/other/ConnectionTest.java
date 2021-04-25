package scrap.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.jbc.util.daoUtils.DBUtils;

public class ConnectionTest {

	private Set<Connection> connections = new HashSet<Connection>();
	private Object obj = new Object();
	private boolean close;

	public static void main(String[] args) throws SQLException, InterruptedException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		ConnectionTest test = new ConnectionTest();
		Class.forName(DBUtils.DB_DRIVER.toString()).newInstance();
		Connection c1 = test.getConnection();
		Connection c2 = test.getConnection();
		c1.close();
		c2.close();
		test.getSize();
	}

	public Connection getConnection() throws SQLException, InterruptedException {
		while (true) {
			synchronized (obj) {
				if (connections.size() < 11 && !close) {
					Connection TempCon = DriverManager.getConnection(DBUtils.DB_URL.toString(),
							DBUtils.DB_USER.toString(), DBUtils.DB_PASS.toString());
					TempCon.setAutoCommit(true);
					connections.add(TempCon);
					return TempCon;
				}
				obj.wait();
				if (close) {
					System.err.println("We closed all connections, try another day");
					return null;
				}
			}
		}
	}

	public void getSize() {
		System.out.println(connections.size());
	}
}
