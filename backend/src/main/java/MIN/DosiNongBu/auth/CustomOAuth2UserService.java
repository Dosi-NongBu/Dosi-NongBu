package MIN.DosiNongBu.auth;


import MIN.DosiNongBu.auth.dto.OAuthAttributes;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.user.UserRepository;
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

/*
* OAuth2 로그인 성공 이후 사용자 정보를 가져올 때
* OAuth2UserService 의 기본 구현체는 DefaultOAuth2UserService 이지만, 해당 클래스를 상속받는 CustomOAuthUserService 사용
* */
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;


    /*
    * 사용자 정보 가져오기
    * AccessToken 까지 얻은 다음 실행
    * */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserServices = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserServices.loadUser(userRequest);

        // 로그인 플랫폼 구분 Id
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /*
        * OAuth 2.0 공급자의 설정에서 사용자 식별자를 가져오는 부분
        * 각 소셜 계정마다 유니한 id를 전달
        * 구글의 경우 'sub' / 네이버의 경우 'id' ...
        * */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자가 새로운 사용자이거나, 변경 사항이 있는 경우
        User user = registrateOrUpdate(attributes);



        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleType().getKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
        );
    }

    /*
    * findByEmail 로 사용자가 존재한다면 update
    * 없다면 toEntity 로 새로 만들기
    * */
    private User registrateOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getProfileImage()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
