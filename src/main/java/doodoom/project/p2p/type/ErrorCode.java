package doodoom.project.p2p.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXIST("이메일이 중복 되었습니다."),
    INVALID_INPUT_VALUE("입력 값이 올바르지 않습니다.");
    private final String description;
}
