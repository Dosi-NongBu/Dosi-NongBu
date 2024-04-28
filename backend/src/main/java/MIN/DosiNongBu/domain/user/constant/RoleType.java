package MIN.DosiNongBu.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 사용자 권한 타입
@Getter
@RequiredArgsConstructor
public enum RoleType {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
