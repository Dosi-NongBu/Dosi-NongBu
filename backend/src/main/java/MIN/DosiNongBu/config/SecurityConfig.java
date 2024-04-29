package MIN.DosiNongBu.config;

import MIN.DosiNongBu.auth.CustomOAuth2UserService;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    //private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception{
        http

                //csrf 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .headers(option -> option.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // 기본 인증 로그인 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // 세션을 사용하지 않고, 각 요청마다 인증을 새로 수행함 (JWT)
                .sessionManagement(securitySessionManagementConfigurer ->
                        securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


                //HTTP 요청 접근 제한
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/**").hasRole(RoleType.ADMIN.name())
                        .anyRequest().authenticated()
                )

                //.authenticationProvider(authenticationProvider)


                // 소셜 로그인 후, 엑세스 토큰 + 사용자 프로필 정보 받아오기
                .oauth2Login(oAuth2LoginConfigurer ->
                        oAuth2LoginConfigurer.userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)))

                // JWT 필터 설정
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)


                // 로그아웃 시 이동 URL
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.logoutSuccessUrl("/"));



        return http.build();
    }


}
