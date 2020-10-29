package se.ecutb.cardealer.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class User {

    @Id
    private String id;
    private String name;
    private LocalDate birthday;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private List<String> acl;
}
