package se.ecutb.cardealer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static se.ecutb.cardealer.constants.Messages.*;
import static se.ecutb.cardealer.constants.RegExp.PHONE_REGEXP_PATTERN;
import static se.ecutb.cardealer.constants.RegExp.USERNAME_REGEXP_PATTERN;

@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -3389715527027131362L;

    @Id
    private String id;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 3, max = 40, message = WRONG_SIZE_MSG)
    private String name;
    @NotNull(message = EMPTY_FIELD_MSG)
    @Past(message = WRONG_DATE_MSG)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    @Pattern(regexp = USERNAME_REGEXP_PATTERN, message = WRONG_USERNAME_MSG)
    private String username;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Size(min = 4, max = 40, message = WRONG_SIZE_MSG)
    private String password;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Email(message= WRONG_EMAIL_MSG)
    private String email;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    @Pattern(regexp = PHONE_REGEXP_PATTERN, message = WRONG_PHONE_MSG)
    private String phonenumber;
    @NotEmpty(message = EMPTY_FIELD_MSG)
    private List<String> acl;
}
