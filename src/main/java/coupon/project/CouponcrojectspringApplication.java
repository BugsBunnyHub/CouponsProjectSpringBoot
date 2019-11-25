package coupon.project;

import coupon.project.DB.CouponDBDAO;
import coupon.project.beans.Category;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import coupon.project.facades.AdminFacade;
import coupon.project.facades.CompanyFacade;
import coupon.project.facades.CustomerFacade;
import coupon.project.facades.ExpiredCouponDelete;
import coupon.project.login.ClientType;
import coupon.project.login.LoginManger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class CouponcrojectspringApplication {

    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(CouponcrojectspringApplication.class, args);
        ExpiredCouponDelete expiredCouponDelete = ctx.getBean(ExpiredCouponDelete.class);
        Thread t = new Thread(expiredCouponDelete);
        t.start();

        try {

            LoginManger loginManger = ctx.getBean(LoginManger.class);

            //Admin
            AdminFacade admin = (AdminFacade) loginManger.login("admin@admin.com", "admin", ClientType.Admin);

            //Add customer - works
            Customer c1 = new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123");
            admin.addCustomer(c1);
            System.out.println("Added customer with info: " + c1.toString());
            //Update customer - works
            System.out.println("Customer email before update: " + c1.getEmail());
            c1.setEmail("DanielUpdated@gmail.com");
            admin.updateCustomer(c1);
            System.out.println("Customer email after update: " + c1.getEmail());
            //Print all customers - works
            //System.out.println(admin.getAllCustomers());
            //Delete customer - works
            //admin.deleteCustomer(c1.getId());
            //System.out.println("Deleted customer: " + c1.toString());
            //Check if customer exists by email and password - works
            //System.out.println(admin.isCustomerExists("DanielUpdated@gmail.com", "123"));

            //Add company - works
            Company company1 = new Company("Shatz ltd", "shatz@shatz.com", "123");
            admin.addCompany(company1);
            //Update company - works
            System.out.println("Company name before update: " + company1.getName());
            company1.setName("Shatz Updated ltd");
            System.out.println("Company name after update: " + company1.getName());
            admin.updateCompany(company1);
            //Print all companies - works
            //System.out.println(admin.getAllCompanies());
            //Delete company - works
            //admin.deleteCompany(company1.getId());

            //Company
            CouponDBDAO cou1 = ctx.getBean(CouponDBDAO.class);
            CompanyFacade companyFacade = (CompanyFacade) loginManger.login("shatz@shatz.com", "123",
                    ClientType.Company);
            //Add coupon - works
            //Static dates to make the coupon valid for 24h from the creation date for testing
            Date today = getToday();
            Date tomorrow = getTomorrow();
            Coupon coupon = new Coupon(companyFacade.getCompanyDetails(), 100, "test coupon", "test description",
                    "image.jpg", Category.Electricity, today, tomorrow, 555.5);
            companyFacade.addCoupon(coupon);
            System.out.println("coupon ID/Title: " + coupon.getId() + " " + coupon.getTitle());
            //Update coupon - works
            System.out.println("Coupon amount before update: " + coupon.getAmount());
            coupon.setAmount(6000);
            companyFacade.updateCoupon(coupon);
            System.out.println("Coupon amount after update: " + coupon.getAmount());
            //Print all coupons for company - works
            //System.out.println(cou1.getCouponByCompany(company1));
            //Delete coupon - works
            //companyFacade.deleteCoupon(coupon.getId());

            //Customer
            CustomerFacade customerFacade = (CustomerFacade) loginManger.login("DanielUpdated@gmail.com", "123",
                    ClientType.Customer);
            //Purchase coupon - works
            customerFacade.purchaseCoupon(coupon);
            System.out.println("Purchased coupon by customer is: " + coupon.getTitle());
            //Delete coupon purchase - works
            //customerFacade.deleteCouponPurchase(coupon);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //enable to see StackTrace errors for debugging
            //e.printStackTrace();
        } finally {
            expiredCouponDelete.stop();
        }


    }

}
