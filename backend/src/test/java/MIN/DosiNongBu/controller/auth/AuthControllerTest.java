package MIN.DosiNongBu.controller.auth;

import MIN.DosiNongBu.controller.auth.dto.JoinRequestDto;
import MIN.DosiNongBu.controller.auth.dto.LoginRequestDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    public void after(){
        userRepository.deleteAll();
    }

    @Test
    void 회원가입() throws Exception{
        //given
        JoinRequestDto joinRequestDto = JoinRequestDto.builder()
                .name("안재민")
                .email("michael1208@naver.com")
                .password("password123")
                .build();

        String url = "http://localhost:" + port + "/auth/join";

        //when
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url, joinRequestDto, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo("안재민");
    }

    @Test
    void 로그인() throws Exception{
        //given
        User user = User.builder()
                .email("michael1208@naver.com")
                .password(passwordEncoder.encode("password123"))
                .name("안재민")
                .profileImage(null)
                .nickname(null)
                .currentAddress(null)
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();

        userRepository.save(user);

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("michael1208@naver.com")
                .password("password123")
                .build();

        String url = "http://localhost:" + port + "/auth/login";

        //when
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url, loginRequestDto, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo("안재민");

        System.out.println(all.get(0).getPassword());
    }



}