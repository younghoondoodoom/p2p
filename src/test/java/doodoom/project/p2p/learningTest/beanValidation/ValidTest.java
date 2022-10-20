package doodoom.project.p2p.learningTest.beanValidation;

import doodoom.project.p2p.type.MemberStatus;
import org.hibernate.validator.internal.metadata.core.ConstraintHelper;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidTest {

    @Test
    public void dataValid() throws Exception {
        //given
        final Validator validator =
                Validation.buildDefaultValidatorFactory().getValidator();

        final CreateMember emailErrorMember = CreateMember.builder()
                .email("test")
                .password("12345678")
                .status(MemberStatus.ACTIVE)
                .build();

        //when
        Set<ConstraintViolation<CreateMember>> constraintViolations =
                validator.validate(emailErrorMember);

        //then
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("올바른 형식의 이메일 주소여야 합니다");
    }

    @Test
    public void passwordCheck() throws Exception {
        //given
        final Validator validator =
                Validation.buildDefaultValidatorFactory().getValidator();

        final CreateMember createMember = CreateMember.builder()
                .email("test@test.com")
                .password("dudgns2684!@")
                .passwordCheck("dudgns2222!@")
                .status(MemberStatus.ACTIVE)
                .build();


        //when
        Set<ConstraintViolation<CreateMember>> constraintViolations =
                validator.validate(createMember);

        //then
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("비밀번호가 일치하지 않습니다.");
    }
}
