package MIN.DosiNongBu.auth;

import MIN.DosiNongBu.auth.dto.OAuthAttributes;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    /* 사용자가 로그인 했을 때 정보 가져오기
    */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserServices = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserServices.loadUser(userRequest);

        // 로그인 플랫폼 구분 Id
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /* OAuth 2.0 공급자의 설정에서 사용자 식별자를 가져오는 부분
        * Google : sub, Naver : response
        */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // User 에 저장 할 수 있도록 변환
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 신규 유저 등록 & 기존 유저 변경 사항 반영
        User user = registrateOrUpdate(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User registrateOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getProfileImage()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}


