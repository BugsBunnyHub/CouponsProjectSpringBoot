package coupon.project.facades;

import coupon.project.beans.Company;

import java.util.List;

public class AdminFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {
        return email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin");
    }

    // Company methods
    public void addCompany(Company company) {
        companyDB.addCompany(company);
    }

    public void deleteCompany(int id) {
        companyDB.deleteCompany(id);
    }

    public List<Company> GetAllCompanies() {
        return companyDB.GetAllCompanies();
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


}
