package coupon.project.DB;

import coupon.project.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

    Company findCompanyByEmailAndPassword(String email, String password);

    Company findCompanyByEmail(String email);

    Company findCompanyByName(String name);


}
