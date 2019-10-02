package coupon.project.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "Coupons", uniqueConstraints =
        {@UniqueConstraint(columnNames = "title", name = "uniqueTitleConstraint")})
@Component
@Scope("prototype") //can create many coupons
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company companyID;
    @Column
    @Min(1)
    @Max(16)
    private int amount;
    @NotBlank
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private Category category;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private double price;

    //empty con for hibernate
    public Coupon() {
    }

    public Coupon(@NotBlank Company companyID, @NotBlank int amount, @NotBlank String title, String description, String image, Category category, Date startDate, Date endDate, double price, @NotBlank int enabled) {
        this.companyID = companyID;
        this.amount = amount;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    //should be never used since it's @Id, however DB would ignore setId
    public void setId(int id) {
        this.id = id;
    }

    public Company getCompanyID() {
        return companyID;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}