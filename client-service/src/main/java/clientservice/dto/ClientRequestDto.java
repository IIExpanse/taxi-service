package clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Positive
    private Integer age;
    private String login;
}
