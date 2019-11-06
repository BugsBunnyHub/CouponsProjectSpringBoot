package coupon.project.facades;

import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

//TODO add to all facdes @service
@Component
public class CompanyFacade extends ClientFacade {
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    @Bean
    public CompanyFacade companyFacade2() {
        return this;
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
    public void addCoupon(Coupon coupon) {
        //check if this company has another coupon with the same title
        Company company = companyDB.findOneCompany(this.companyId);
        List<Coupon> companyCoupons = couponDB.getCouponByCompany(company);

        for (Coupon companyCoupon : companyCoupons) {
            if (coupon.getTitle().equalsIgnoreCase(companyCoupon.getTitle()))
                System.out.println("Coupon is already registered!"); //TODO: Replace
        }
        company.getCoupons().add(coupon);
        companyDB.updateCompany(company);
    }

    //delete coupon for all customers that have that coupon
    public void deleteCoupon(Coupon coupon) {
        List<Customer> customers = customerDB.findCustomerByCoupons(coupon);
        for (Customer customer : customers) {
            customer.getCoupons().remove(coupon);
            customerDB.updateCustomer(customer);
        }
    }

    public void updateCoupon(Coupon coupon) {
        couponDB.updateCoupon(coupon);
    }
}


