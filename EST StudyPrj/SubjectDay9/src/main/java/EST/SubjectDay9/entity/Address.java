package EST.SubjectDay9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="suite")
    private String suite;
    @Column(name="city")
    private String city;
    @Column(name="zipcode")
    private String zipcode;

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="GEO_ID")
    private Geo geo;

    @Builder
    public Address(String street, String suite, String city, String zipcode, Geo geo){
        this.street= street;
        this.suite=suite;
        this.city=city;
        this.zipcode=zipcode;
        this.geo=geo;
    }


}
