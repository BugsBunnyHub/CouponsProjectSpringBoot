package coupon.project.DB;


import coupon.project.beans.Company;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDBDAO {

    @Autowired
    private CompanyRepo companyRepo;

    public void addCompany(Company company) {
        companyRepo.save(company);
    }

    public void updateCompany(@NotNull Company company) {
        if (companyRepo.existsById(company.getId()))
            companyRepo.save(company);

    }

    public void deleteCompany(int id) {
        companyRepo.deleteById(id);
    }

    public List<Company> GetAllCompanies() {
        return companyRepo.findAll();
    }

    public void findOneCompany(int id) {
        companyRepo.findById(id);
    }

    public Company isCompanyExists(String email, String password) {
        return companyRepo.findByEmailAndPassword(email, password);

    }

    public Company findCompanyByEmail(String email) {
        return companyRepo.findByEmail(email);
    }

    public Company findCompanyByName(String name) {
        return companyRepo.findByName(name);

    }

}
