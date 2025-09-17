package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Geo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddGeoRequest {
    private Long id;
    private String lat;
    private String lng;

    public Geo toEntity(){
        return Geo.builder()
                .lat(this.lat)
                .lng(this.lng)
                .build();
    }
}
