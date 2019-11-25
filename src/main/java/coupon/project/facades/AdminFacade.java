package coupon.project.facades;

import coupon.project.Exceptions.companyExistsException;
import coupon.project.Exceptions.companyNotFoundException;
import coupon.project.beans.Company;
import coupon.project.beans.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminFacade extends ClientFacade {
    @Override
    //hard codded login since it's Admin
    public boolean login(String email, String password) {
        return email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin");
    }

    // Company methods
    public void addCompany(Company company) throws companyExistsException {

        companyDB.addCompany(company);
    }

    public void deleteCompany(int id) {
        companyDB.deleteCompany(id);
    }

    public void updateCompany(Company company) throws companyNotFoundException {
        companyDB.updateCompany(company);
    }

//    public void updateCompany(Company company) throws CompanyExistsException {
//        List<Company> allCompanies = companyDB.GetAllCompanies();
//        for (Company comp : allCompanies) {
//            if (comp.getName().contentEquals(company.getName()) && comp.getId() == company.getId()) {
//                throw new CompanyExistsException();
//            }else companyDB.updateCompany(company);
//        }
//    }

    public List<Company> findAllCompanies() {
        return companyDB.getAllCompanies();
    }

    public void findOneCompany(int id) {
        companyDB.findOneCompany(id);
    }

    public Company isCompanyExists(String email, String password) {
        return companyDB.isCompanyExists(email, password);

    }

    public Company findCompanyByEmail(String email) {
        return companyDB.findCompanyByEmail(email);
    }

    public Company findCompanyByName(String name) {
        return companyDB.findCompanyByName(name);

    }

    //Customer methods
    public void addCustomer(Customer customer) {
        customerDB.addCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDB.deleteCustomer(id);
    }

    public void updateCustomer(Customer customer) {
        customerDB.updateCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDB.getAllCustomers();
    }

    public Customer isCustomerExists(String email, String password) {
        return customerDB.isCustomerExists(email, password);
    }

    public Customer findCustomerByEmail(String email) {
        return customerDB.isCustomerEmailExists(email);
    }

    public void findCustomerById(int id) {
        customerDB.findOneCustomer(id);
    }


}
