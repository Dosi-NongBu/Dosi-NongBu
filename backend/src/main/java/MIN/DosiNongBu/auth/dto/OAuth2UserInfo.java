package MIN.DosiNongBu.auth.dto;

import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/*
* 어떤 소셜 플랫폼인지 체크하고
* 그에 맞는 dto 를 생성
* */
@Getter
public class OAuth2UserInfo {
    // 이건 뭐지
    private Map<String, Object> attributes;

    // OAuth 2.0 Provider 별 사용자 식별자
    private String nameAttributeKey;

    // 소셜에서 가져올 사용자 정보
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public OAuth2UserInfo(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    /*
    * OAuth 2.0 Provider 링크 해주기
    * 인증요청 플랫폼을 구분하여 각각의 사용자 정보 형태에 맞는 OAuthAttributes 객체를 가져옴
    * Google, Naver
    * */
    public static OAuth2UserInfo of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){

        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2UserInfo ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .build();
    }

    private static OAuth2UserInfo ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("profile_image"))
                .build();
    }

    /*
    * 기존에 없는 사용자 일 때
    * attributes 에 있는 값들로 User 엔티티 생성
    * */
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .profileImage(profileImage)
                .roleType(RoleType.USER)
                .build();
    }

}
