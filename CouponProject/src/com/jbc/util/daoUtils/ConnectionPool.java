package com.jbc.util.daoUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>ConnectionPool</code> bean that manages the connections to the SQL DB
 * Server.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see util#DBUtils
 * @see util#ConnectionsSizeUtils
 */
public final class ConnectionPool {

	/* attributes */
	private static ConnectionPool instance;
	private boolean close;
	private Set<Connection> connections;

	/* constructor, singleton */
	private ConnectionPool() {
		try {
			Class.forName(DBUtils.DB_DRIVER.toString()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		connections = new HashSet<Connection>();
	}

	/**
	 * Creates a <code>ConnectionPool</code> instance, and limiting the instances of
	 * the <code>ConnectionPool</code> to 1, returning the same instance, if this
	 * method is called upon multiple times , allowing multiple objects use the same
	 * instance.
	 * 
	 * @return <code>ConnectionPool</code> instance, if this method is called upon
	 *         multiple times, returns the same instance that was first created.
	 * @see #ConnectionPool()
	 * @see #instance
	 */
	public synchronized static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	/**
	 * Creates a <code>Connection</code>, to let objects access the SQL DB Server,
	 * the limit of the connections is based on the <code>ConnectionsSize</code>
	 * <code>MAX</code> value , if the limit is reached and another
	 * <code>Connection</code> is trying to use the method, it will
	 * <code>wait</code> for a <code>Connection</code> to leave the SQL DB and only
	 * then will get notified by the <code>restoreConnection</code> and will get to
	 * create its <code>Connection</code>.
	 * 
	 * @return <code>Connection</code> to the SQL DB Server
	 * @see ConnectionsSizeUtils
	 * @see DBUtils
	 * @see #closeAllConnections()
	 * @see #connections
	 * @see #close
	 */
	public Connection getConnection() {
		try {
			while (true) {
				synchronized (instance) {
					if (connections.size() < ConnectionsSizeUtils.MAX.toInt() && !close) {
						Connection con = DriverManager.getConnection(DBUtils.DB_URL.toString(),
								DBUtils.DB_USER.toString(), DBUtils.DB_PASS.toString());
						con.setAutoCommit(true);
						connections.add(con);
						return con;
					}
					if (!close) {
						System.err.println("Connections are currently full, waiting for a connection to open up...");
						instance.wait();
					}
					if (close) {
						System.err.println(
								"We closed all connections and not opening new ones for now, try another day.");
						return null;
					}
				}
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
			closeAllConnections();
		}
		return null;
	}

	/**
	 * Removes and closes a <code>Connection</code> to let other connections to
	 * enter the SQL DB Server, this method will make sure to wait for the
	 * <code>Connection</code> to finish attempting to connect into the SQL DB
	 * Server before trying to <code>notify</code> it, or others waiting to enter.
	 * 
	 * @param connection
	 * @see #connections
	 */
	public synchronized void releaseConnection(Connection connection) {
		synchronized (instance) {
			connections.remove(connection);
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			instance.notify();
		}
	}

	/**
	 * Closes all connections, and doesn't let any new connections to be created,
	 * making sure that you will be able to safely close the SQL DB Server.
	 * 
	 * @see #connections
	 * @see #close
	 */
	public synchronized void closeAllConnections() {
		close = true;
		synchronized (instance) {
			instance.notifyAll();
		}
		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connections.removeAll(connections);
	}

	/**
	 * Re-enables the creation of new connections with the method
	 * <code>getConnection</code>, after the <code>closeAllConnections</code> method
	 * has been called
	 * 
	 * @see #close
	 */
	public synchronized void openConnections() {
		close = false;
	}

	/**
	 * 
	 * @return The number of connections currently connected to the SQL DB Server.
	 * @see #connections
	 */
	public synchronized int numConnections() {
		return connections.size();
	}

	/**
	 * 
	 * @return The maximum number of connections that can be connected to the SQL DB
	 *         Server.
	 * @see ConnectionsSizeUtils
	 */
	public synchronized int maxConnections() {
		return ConnectionsSizeUtils.MAX.toInt();
	}
}
