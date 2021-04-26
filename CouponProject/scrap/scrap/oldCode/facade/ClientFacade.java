package scrap.oldCode.facade;

public class ClientFacade {

	//purchase coupon using a coupon instead of a couponId
	
//	public void purchaseCoupon(Coupon coupon) throws SQLException, CouponNullValueException, NegativePriceException,
//			CouponNotFoundException, CouponNoStockException, CouponOwnedException, CouponExpiredPurchaseException {
//		checkCoupon(coupon);
//		boolean exists = false;
//		List<Coupon> companyCoupons = couponsDBDAO.getCompanyCoupons(coupon.getCompanyID());
//		List<Coupon> customerCoupons = couponsDBDAO.getCustomerCoupons(thisCustomer.getId());
//		for (Coupon coupons : customerCoupons) {
//			if (coupons.equals(coupon)) {
//				throw new CouponOwnedException(coupon.getId());
//			}
//		}
//		for (Coupon coupons : companyCoupons) {
//			if (coupons.equals(coupon)) {
//				exists = true;
//				if (coupons.getAmount() == 0) {
//					throw new CouponNoStockException(coupon.getId());
//				}
//				if (coupons.getEndDate().getTime() <= System.currentTimeMillis()) {
//					throw new CouponExpiredPurchaseException(coupon.getId(), coupon.getEndDate());
//				}
//				break;
//			}
//		}
//		if (!exists) {
//			throw new CouponNotFoundException(coupon.getId());
//		}
//		couponsDBDAO.addCouponPurchase(thisCustomer.getId(), coupon.getId());
//	}

}
