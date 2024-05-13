package MIN.DosiNongBu.config;

import MIN.DosiNongBu.auth.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    //private final CustomOAuth2UserService customOAuth2UserService;
    //private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    //private final CustomAuthSuccessHandler customAuthSuccessHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception{
        http

                //csrf 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // 세션을 사용하지 않고, 각 요청마다 인증을 새로 수행함 (JWT)
                .sessionManagement(securitySessionManagementConfigurer ->
                        securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //HTTP 요청 접근 제한
                .authorizeHttpRequests((authorize) -> authorize
                        //.requestMatchers("/api/**", "/hello").permitAll()
                        //.requestMatchers("/", "/h2-console/**").permitAll()
                        .anyRequest().permitAll()
                )


                // oauth2 로그인 기능에 대한 설정 진입점
/*                .oauth2Login(oAuth2LoginConfigurer ->
                        // oauth2 로그인 성공 이후 사용자 정보 가져오기
                        oAuth2LoginConfigurer
                                .successHandler(customOAuth2SuccessHandler)
                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)))*/

                // JWT 필터 설정
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }



}
