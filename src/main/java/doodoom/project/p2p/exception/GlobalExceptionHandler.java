package doodoom.project.p2p.exception;

import doodoom.project.p2p.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

import static doodoom.project.p2p.type.ErrorCode.INVALID_INPUT_VALUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleMemberException(MemberException e) {
        log.warn("{} is occurred", e.getErrorCode());
        ErrorResponse response =
                ErrorResponse.builder().errorCode(e.getErrorCode()).errorMessage(e.getErrorMessage()).build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        log.warn("{} is occurred", INVALID_INPUT_VALUE);
        ErrorResponse response =
                ErrorResponse.builder().errorCode(INVALID_INPUT_VALUE).errorMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage()).build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.warn("Exception is occurred", e);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
