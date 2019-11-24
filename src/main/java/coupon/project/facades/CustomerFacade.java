package coupon.project.facades;

import coupon.project.Exceptions.CouponAlreadyPurchasedException;
import coupon.project.Exceptions.InvalidAmountException;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFacade extends ClientFacade {
    private int customerId;

    @Override
    //customer login with email and password
    public boolean login(String email, String password) {
        Customer customer = customerDB.isCustomerExists(email, password);
        if (customer != null) {
            this.customerId = customer.getId();
            return true;
        } else
            return false;
    }

    //TODO Add Date check
    public void purchaseCoupon(Coupon coupon) throws InvalidAmountException, CouponAlreadyPurchasedException {
        if (coupon.getAmount() == 0) {
            throw new InvalidAmountException();
        }

        if (!isCouponPurchased(coupon))
            throw new CouponAlreadyPurchasedException();
        else {
            Customer customer = customerDB.findOneCustomer(customerId);
            customer.getCoupons().add(coupon);
            customerDB.updateCustomer(customer);
            coupon.setAmount(couponDB.getOneCoupon(coupon.getId()).getAmount() - 1);
            couponDB.updateCoupon(coupon);
        }
    }

    public void deleteCouponPurchase(Coupon coupon) {
        Customer customer = customerDB.findOneCustomer(customerId);
        customer.getCoupons().remove(coupon);
        customerDB.updateCustomer(customer);
    }

    private boolean isCouponPurchased(Coupon coupon) {
        Customer customer = customerDB.findOneCustomer(customerId);
        List<Coupon> coupons = customer.getCoupons();
        for (Coupon c : coupons) {
            if (c.getId().equals(coupon.getId())) {
                return false;
            }
        }
        return true;
    }

}
