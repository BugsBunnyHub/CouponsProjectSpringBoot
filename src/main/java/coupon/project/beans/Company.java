package coupon.project.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "Companies", uniqueConstraints={@UniqueConstraint(columnNames = "name"),@UniqueConstraint(columnNames = "email")})
//@Table(name = "Companies",
//      uniqueConstraints = {@UniqueConstraint(columnNames = "name"),
//                @UniqueConstraint(columnNames = "email")})
@Component
@Scope("prototype") //can create many companies
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//GeneratedValue is used to specify how the primary key should be
    //mapped, GenerationType.IDENTITY ex: ID: 1...2...3
    private int id;
    @NotBlank
    @Column(name = "name", unique = true) //"unique" can be used as a shortcut for UniqueConstraint
    private String name;
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank
    @Column(name = "password")
    private String password;
    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "companyID", orphanRemoval = true, cascade = CascadeType.ALL)
    //One company can have many coupons
    //EAGER to create the companies as soon as possible
    //MappedBy mapping the in the DB
    //orphanRemoval is an entirely ORM-specific,It marks "child" entity to be removed when it's no longer -->
    // referenced from the "parent" entity, e.g. when you remove the child entity from the corresponding collection of
    // the parent entity
    //Cascade for auto delete coupons and other related obj
    private List<Coupon> coupons = new ArrayList<>();

    //empty con for hibernate
    public Company() {
    }

    public Company(@NotBlank String name, @NotBlank String email, @NotBlank String password, List<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public Company(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    //setId should not be used since it's @id(DB would ignore the set anyways)
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public void removeCoupon(Coupon coupon) {
        this.coupons.remove(coupon);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
