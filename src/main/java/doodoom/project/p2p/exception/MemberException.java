package doodoom.project.p2p.exception;

import doodoom.project.p2p.type.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
