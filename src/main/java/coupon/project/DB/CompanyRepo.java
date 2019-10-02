package coupon.project.DB;

import coupon.project.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

    Company findByEmailAndPassword(String email, String password);

    Company findByEmail(String email);

    Company findByName(String name);


}
