package scrap.other;

import java.sql.SQLException;

import com.jbc.util.daoUtils.ConnectionPool;

public class ConnectionsMaxTest {

	static ConnectionPool con;

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InterruptedException {
		con = ConnectionPool.getInstance();
		new Thread(run).start();
		Thread.sleep(3000);
		con.closeAllConnections();
		System.out.println("Main " + con.numConnections());
	}

	final static Runnable run = new Runnable() {
		@Override
		public void run() {
			try {
				int i = 0;
				while (i++ < 11) {
					con.getConnection();
					System.out.println("Thread " + con.numConnections());
				}
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	};

}
