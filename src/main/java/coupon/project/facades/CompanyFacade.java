package coupon.project.facades;

import coupon.project.Exceptions.companyNotFoundException;
import coupon.project.Exceptions.couponAlreadyInUseException;
import coupon.project.Exceptions.couponNotFoundException;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyFacade extends ClientFacade {
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    @Override
    public boolean login(String email, String password) {
        Company company = companyDB.isCompanyExists(email, password);
        if (company != null) {
            this.companyId = company.getId();
            return true;
        } else
            return false;
    }

    //add new coupon
    public Coupon addCoupon(Coupon coupon) throws couponAlreadyInUseException, companyNotFoundException {
        //check if this company has another coupon with the same title
        Company company = companyDB.findOneCompany(this.companyId);
        List<Coupon> companyCoupons = couponDB.getCouponByCompany(company);

        for (Coupon companyCoupon : companyCoupons) {
            if (coupon.getTitle().equalsIgnoreCase(companyCoupon.getTitle()))
                throw new couponAlreadyInUseException();
        }
        Coupon newCoupon = couponDB.addCoupon(coupon);
        company.getCoupons().add(newCoupon);
        companyDB.updateCompany(company);
        return coupon;
    }

    public void deleteCoupon(int couponId) {
        Coupon couponToDelete = couponDB.getOneCoupon(couponId);
        Company comp = couponToDelete.getCompanyID();
        comp.removeCoupon(couponToDelete);

        List<Customer> customers = customerDB.getAllCustomers();
        for (Customer customer : customers) {
            customer.getCoupons().remove(couponToDelete);
            customerDB.updateCustomer(customer);
        }
    }

    public void updateCoupon(Coupon coupon) throws couponNotFoundException {
        couponDB.updateCoupon(coupon);
    }

    public Coupon getOneCoupon(int id) {
        return couponDB.getOneCoupon(id);
    }

    public Company getCompanyDetails() {
        return companyDB.findOneCompany(companyId);
    }
}


