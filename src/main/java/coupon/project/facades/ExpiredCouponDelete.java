package coupon.project.facades;

import coupon.project.DB.CompanyDBDAO;
import coupon.project.DB.CouponDBDAO;
import coupon.project.DB.CustomerDBDAO;
import coupon.project.Exceptions.companyNotFoundException;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ExpiredCouponDelete implements Runnable {

    @Autowired
    protected CouponDBDAO coupDB;
    @Autowired
    protected CompanyDBDAO compDB;
    @Autowired
    protected CustomerDBDAO custDB;

    private boolean exit = false;

    @Override
    public void run() {
        while (!exit) {
            try {

                Calendar c = Calendar.getInstance();
                List<Coupon> allCoupons = coupDB.getAllCoupons();
                List<Coupon> trashBin = new ArrayList<>();
                for (Coupon allCoupon : allCoupons) {
                    //check what coupon is expired
                    if (allCoupon.getEndDate().before(c.getTime())) {
                        //run on all customer coupons
                        List<Customer> customersCouponsToRemove = custDB.getAllCustomers();
                        for (Customer customer : customersCouponsToRemove) {
                            List<Coupon> custCoupon = customer.getCoupons();
                            custCoupon.remove(allCoupon);
                            custDB.updateCustomer(customer);
                        }
                        //run on all company coupons
                        List<Company> companyCouponsToRemove = compDB.getAllCompanies();
                        for (Company company : companyCouponsToRemove) {
                            List<Coupon> compCoupon = company.getCoupons();
                            compCoupon.remove(allCoupon);
                            compDB.updateCompany(company);
                        }
                        //add expired coupons to list to be removed
                        trashBin.add(allCoupon);
                    }

                    System.out.println("Expired Coupon Checker launched successfully");
                    TimeUnit.HOURS.sleep(24); //sleep for 24h
                }
                //remove the trash bin
                for (Coupon removeList : trashBin) {
                    System.out.println("Coupon removed: " + "ID:" + removeList.getId() + " " + "Title:" + removeList.getTitle());
                    coupDB.deleteCoupon(removeList.getId());
                    trashBin.remove(removeList);
                }

            } catch (InterruptedException | companyNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop() {
        exit = true;
    }

}
