package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Company;
import EST.SubjectDay9.entity.User;
import EST.SubjectDay9.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String password;
    private Company company;
    private AddAddressRequest addressRequest;


    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .city(this.city)
                .company(this.company)
                .password(this.password)
                .address(addressRequest.toEntity())
                .build();
    }
}
