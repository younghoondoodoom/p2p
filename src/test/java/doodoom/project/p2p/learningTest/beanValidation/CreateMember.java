package doodoom.project.p2p.learningTest.beanValidation;

import doodoom.project.p2p.type.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMember {
    @Email
    @NotBlank
    private String email;
    @Length(min = 8, max = 15)
    private String password;
    private String passwordCheck;
    @NotNull
    private MemberStatus status;

    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    boolean isEqualPassword() {
        return this.password.equals(passwordCheck);
    }
}
