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
            //System.out.println("Added customer with info:");
            //System.out.println(c1.toString());
            //Update customer - works
            //System.out.println("Customer email before update: " + c1.getEmail());
            //c1.setEmail("DanielUpdated@gmail.com");
            //System.out.println("Customer email after update: " + c1.getEmail());
            //admin.updateCustomer(c1);
            //Print all customers - works
            //System.out.println(admin.getAllCustomers());
            //Delete customer - works
            //admin.deleteCustomer(c1.getId());
            //Check if customer exists by email and password - works
            //System.out.println(admin.isCustomerExists("DanielShatz@gmail.com", "123"));

            //Add company - works
            Company company1 = new Company("Shatz ltd", "shatz@shatz.com", "123");
            //admin.addCompany(company1);
            //Update company - works
            //System.out.println("Company name before update: " + company1.getName());
            //company1.setName("Shatz Updated ltd");
            //System.out.println("Company name after update" + company1.getName());
            //admin.updateCompany();
            //Print all companies - works
            //System.out.println(admin.GetAllCompanies());
            //Delete company - works after creating company1, doesn't work if called after creation TODO check why
            //admin.deleteCompany(company1.getId());

            //Company
            CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
            co1.addCompany(company1);
            CompanyFacade companyFacade = (CompanyFacade) loginManger.login("shatz@shatz.com", "123",
                    ClientType.Company);
            //Add coupon - works
            Date today = getToday();
            Date tomorrow = getTomorrow();
            Coupon coupon1 = new Coupon(company1, 100, "test coupon", "test description",
                    "image.jpg", Category.Electricity, today, tomorrow, 555.5);
            //companyFacade.addCoupon(coupon1);
            //Update coupon - TODO doesn't work
            //System.out.println("Coupon amount before update: " + coupon1.getAmount());
            //coupon1.setAmount(600);
            //companyFacade.updateCoupon(coupon1);
            //System.out.println("Coupon amount after update: " + coupon1.getAmount());
            //Print all coupons for company
            //Delete coupon

            //Customer
            //Purchase coupon


//
//            CustomerFacade customerFacade = (CustomerFacade) loginManger.login("DanielShatz@gmail.com", "123", ClientType.Customer);
//            List<Coupon> coupons = repo.findAllByAmountIsGreaterThanAndEndDateIsAfter(0, new Date(Instant.now().getEpochSecond() * 1000)); // getEpochSecond get's time  in seconds, *1000 to get it in miliseconds
//            Coupon coupon = coupons.get(0);
//            customerFacade.purchaseCoupon(coupon.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
