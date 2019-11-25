package coupon.project.facades;

import coupon.project.Exceptions.couponAlreadyPurchasedException;
import coupon.project.Exceptions.couponDateExpired;
import coupon.project.Exceptions.couponNotFoundException;
import coupon.project.Exceptions.invalidAmountException;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    public void purchaseCoupon(Coupon coupon) throws invalidAmountException, couponAlreadyPurchasedException, couponNotFoundException, couponDateExpired {
        if (coupon.getAmount() == 0) {
            throw new invalidAmountException();
        }
        Calendar c = Calendar.getInstance();
        if (coupon.getEndDate().before(c.getTime())) {
            throw new couponDateExpired();
        }

        if (!isCouponPurchased(coupon))
            throw new couponAlreadyPurchasedException();
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
            if (c.getId() == (coupon.getId())) {
                return false;
            }
        }
        return true;
    }

    public Customer getCustomerDetails() {
        return customerDB.findOneCustomer(customerId);
    }

}
