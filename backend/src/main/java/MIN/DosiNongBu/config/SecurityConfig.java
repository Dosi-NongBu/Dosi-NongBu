package MIN.DosiNongBu.config;

import MIN.DosiNongBu.auth.CustomOAuth2UserService;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) throws Exception{
        http
                //HTTP 요청 접근 제한
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/**").hasRole(RoleType.ADMIN.name())
                        .anyRequest().authenticated()
                )


                // JWT 필터 설정
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)


                //.authenticationProvider(authenticationProvider)

                // 세션을 사용하지 않고, 각 요청마다 인증을 새로 수행함 (JWT)
                .sessionManagement(securitySessionManagementConfigurer ->
                        securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 소셜 로그인 후, 엑세스 토큰 + 사용자 프로필 정보 받아오기
                .oauth2Login(oAuth2LoginConfigurer ->
                        oAuth2LoginConfigurer.userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)))
                // 로그아웃 시 이동 URL
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutSuccessUrl("/"))

                //csrf 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .headers(option -> option.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }


}
