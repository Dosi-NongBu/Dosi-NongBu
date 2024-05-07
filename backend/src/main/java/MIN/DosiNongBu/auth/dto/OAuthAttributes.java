package MIN.DosiNongBu.auth.dto;

import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    /* OAuth 2.0 Provider 에 따라 링크
    * 사용자 프로필에 대한 응답 포맷에 각자 다르기 때문에
    */
    public static OAuthAttributes of(String registrationId, String nameAttributeKey, Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver(nameAttributeKey, attributes);
        }

        return ofGoogle(nameAttributeKey, attributes);
    }

    private static OAuthAttributes ofGoogle(String nameAttributeKey, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .nameAttributeKey(nameAttributeKey)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .build();
    }

    private static OAuthAttributes ofNaver(String nameAttributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributes(attributes)
                .nameAttributeKey(nameAttributeKey)
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .profileImage((String) response.get("profile_image"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .role(RoleType.USER)
                .build();
    }
}

