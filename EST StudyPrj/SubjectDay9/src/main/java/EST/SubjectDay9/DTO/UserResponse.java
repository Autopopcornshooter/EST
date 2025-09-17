package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.entity.Company;
import EST.SubjectDay9.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private Company company;

    private String phone;

    private String city;

    private String password;

    private List<Album> albums;

    private AddressResponse address;

//    public UserResponse(User user){
//        this.id =user.getId();
//        this.name=user.getName();
//        this.email=user.getEmail();
//        this.company=user.getCompany();
//        this.phone=user.getPhone();
//        this.city=user.getCity();
//        this.password=user.getPassword();
//        this.albums=user.getAlbums();
//        this.addressResponse= new AddressResponse(user.getAddress());
//    }

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .city(this.city)
                .password(this.password)
                .address(address.toEntity())
                .build();
    }
}
