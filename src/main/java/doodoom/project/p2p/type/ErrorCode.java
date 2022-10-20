package doodoom.project.p2p.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXIST("이메일이 중복 되었습니다.");
    private final String description;
}
