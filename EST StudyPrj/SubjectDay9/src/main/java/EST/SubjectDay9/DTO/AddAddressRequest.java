package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAddressRequest {
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private AddGeoRequest geo;

    public Address toEntity(){
        return Address.builder()
                .street(this.street)
                .suite(this.suite)
                .city(this.city)
                .zipcode(this.zipcode)
                .geo(geo.toEntity())
                .build();
    }
}
