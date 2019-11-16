package coupon.project;

import coupon.project.DB.CompanyDBDAO;
import coupon.project.beans.Category;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.facades.AdminFacade;
import coupon.project.facades.CompanyFacade;
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
        try {

            LoginManger loginManger = ctx.getBean(LoginManger.class);

            //Admin
            AdminFacade admin = (AdminFacade) loginManger.login("admin@admin.com", "admin", ClientType.Admin);
            //Add customer - works
            //Customer c1 = new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123");
            //admin.addCustomer(c1);
            //Update customer - TODO doesn't work
            //admin.updateCustomer(c1.setFirstName("DanielUpdated")));
            //Print all customers - works
            //System.out.println(admin.getAllCustomers());
            //Delete customer - works
            //admin.deleteCustomer(c1.getId());
            //Check if customer exists by email and password - works
            //System.out.println(admin.isCustomerExists("DanielShatz@gmail.com", "123"));
            //Add company - works
            Company company1 = new Company("Shatz ltd", "shatz@shatz.com", "123");
            admin.addCompany(company1);
            //Update company TODO doesn't work
            //admin.updateCompany();
            //Print all companies - works
            //System.out.println(admin.GetAllCompanies());
            //Delete company - works after creating company1, doesn't work if called after creation TODO check why
            //admin.deleteCompany(company1.getId());

            //Company
            CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
            CompanyFacade companyFacade = (CompanyFacade) loginManger.login("shatz@shatz.com", "123", ClientType.Company);
            //Add coupon - works
            Date today = getToday();
            Date tomorrow = getTomorrow();
            Coupon coupon = new Coupon(company1, 100, "test coupon", "test description", "image.jpg", Category.Electricity, today, tomorrow, 555.5);
            companyFacade.addCoupon(coupon);
            //Update coupon
            //Print all coupons for company
            //Delete coupon

            //Customer
            //Purchase coupon


//            CustomerDBDAO cu1 = ctx.getBean(CustomerDBDAO.class);
//            cu1.addCustomer(new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123")); // Works
//
//            CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
//            Company c1 = new Company("Shatz ltd", "shatz@shatz.com", "123"); // Works
//            co1.addCompany(c1);
//
//            CompanyFacade cF = (CompanyFacade) loginManger.login("shatz@shatz.com", "123", ClientType.Company);
//            Coupon co2 = new Coupon(c1, 100, "title", "description", "cat.jpg", Category.Electricity, new Date(2656565656565L), Date.valueOf("2019-01-01"), 5555.5);
//            cF.addCoupon(co2);
//
//            CustomerFacade customerFacade = (CustomerFacade) loginManger.login("DanielShatz@gmail.com", "123", ClientType.Customer);
//            List<Coupon> coupons = repo.findAllByAmountIsGreaterThanAndEndDateIsAfter(0, new Date(Instant.now().getEpochSecond() * 1000)); // getEpochSecond get's time  in seconds, *1000 to get it in miliseconds
//            Coupon coupon = coupons.get(0);
//            customerFacade.purchaseCoupon(coupon.getId());

        } catch (Exception e) {
            // this null comes from here I think
            e.printStackTrace();
        }


    }

}
