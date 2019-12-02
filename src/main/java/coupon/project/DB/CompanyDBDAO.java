package coupon.project.DB;


import coupon.project.Exceptions.companyNotFoundException;
import coupon.project.beans.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDBDAO {

    @Autowired
    private CompanyRepo companyRepo;

    public void addCompany(Company company) {
        this.companyRepo.save(company);
    }

    public Company updateCompany(Company company) throws companyNotFoundException {
        if (companyRepo.existsById(company.getId())) {
            companyRepo.save(company);
        } else {
            throw new companyNotFoundException();
        }
        return company;
    }

    public void deleteCompany(int id) {
        companyRepo.deleteById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    public Company findOneCompany(int id) {
        Optional<Company> opt = companyRepo.findById(id);
        return opt.orElse(null);
    }

    public Company isCompanyExists(String email, String password) {
        return companyRepo.findCompanyByEmailAndPassword(email, password);

    }

    public Company findCompanyByEmail(String email) {
        return companyRepo.findCompanyByEmail(email);
    }

    public Company findCompanyByName(String name) {
        return companyRepo.findCompanyByName(name);

    }

}
