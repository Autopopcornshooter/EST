package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponse geo;

    public AddressResponse(Address address){
        this.id=address.getId();
        this.street= address.getStreet();;
        this.suite=address.getSuite();
        this.city=address.getCity();
        this.zipcode=address.getZipcode();
        geo =new GeoResponse(address.getGeo());
    }

    public Address toEntity(){
        return Address.builder()
                .street(this.street)
                .suite(this.suite)
                .city(this.city)
                .zipcode(this.zipcode)
                .geo(this.geo.toEntity())
                .build();
    }
}
