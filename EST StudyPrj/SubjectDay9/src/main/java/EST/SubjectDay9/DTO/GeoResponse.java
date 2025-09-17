package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Geo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GeoResponse {
    private Long id;
    private String lat;
    private String lng;

    public GeoResponse(Geo geo){
        this.id=geo.getId();
        this.lat=geo.getLat();
        this.lng=geo.getLng();
    }
    public Geo toEntity(){
        return Geo.builder()
                .lat(this.lat)
                .lng(this.lng)
                .build();
    }
}
