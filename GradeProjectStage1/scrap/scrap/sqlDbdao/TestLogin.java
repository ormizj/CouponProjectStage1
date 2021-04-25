package scrap.sqlDbdao;

import java.sql.SQLException;
import java.util.Date;

import com.jbc.beans.Company;
import com.jbc.beans.Coupon;
import com.jbc.beans.Customer;
import com.jbc.exception.NegativePriceException;
import com.jbc.exception.company.CompanyNotFoundException;
import com.jbc.exception.company.CompanyNullValueException;
import com.jbc.exception.company.CompanyDuplicateValueException;
import com.jbc.exception.coupon.CouponExpiredCreateException;
import com.jbc.exception.coupon.CouponExpiredPurchaseException;
import com.jbc.exception.coupon.CouponNoStockException;
import com.jbc.exception.coupon.CouponNotFoundException;
import com.jbc.exception.coupon.CouponNullValueException;
import com.jbc.exception.coupon.CouponOwnedException;
import com.jbc.exception.coupon.CouponDuplicateValueException;
import com.jbc.exception.customer.CustomerNotFoundException;
import com.jbc.exception.customer.CustomerNullValueException;
import com.jbc.exception.customer.CustomerDuplicateValueException;
import com.jbc.facade.AdminFacade;
import com.jbc.facade.CompanyFacade;
import com.jbc.facade.CustomerFacade;
import com.jbc.facade.LoginManager;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.daoUtils.ConnectionPool;
import com.jbc.util.facadeUtils.ClientTypeUtils;

@SuppressWarnings("unused")
public class TestLogin {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, InterruptedException, CouponExpiredCreateException,
			CouponNullValueException, NegativePriceException, CouponDuplicateValueException, CouponNoStockException,
			CompanyNullValueException, CompanyDuplicateValueException, CompanyNotFoundException,
			CustomerNullValueException, CustomerDuplicateValueException, CustomerNotFoundException,
			CouponNotFoundException, CouponExpiredPurchaseException, CouponOwnedException {

		ConnectionPool conPool = ConnectionPool.getInstance();
		LoginManager login = LoginManager.getInstance();

//		CustomerFacade client = (CustomerFacade) login.login("poop@gmail.com", "4321", ClientTypeUtils.CUSTOMER);
//		CompanyFacade client = (CompanyFacade) login.login("gmc@gmail.com", "1234", ClientTypeUtils.COMPANY);
//		AdminFacade client = (AdminFacade) login.login("admin@admin.com", "admin", ClientTypeUtils.ADMINISTRATOR);
		

		conPool.closeAllConnections();
	}

}
