package scrap.sqlDbdao;

import java.sql.SQLException;
import java.util.List;

import com.jbc.beans.Coupon;
import com.jbc.dao.dbdao.CouponsDBDAO;
import com.jbc.util.daoUtils.ConnectionPool;

@SuppressWarnings("unused")
public class TestCouponsDBDAO {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException {
		
		ConnectionPool con = ConnectionPool.getInstance();
		CouponsDBDAO dao = new CouponsDBDAO(con.getConnection());
		
//		List<Coupon> coupons = dao.getAllCoupons();;
//		for (Coupon coupon : coupons) {
//			System.out.println(coupon);
//		}

		con.closeAllConnections();

	}

}
