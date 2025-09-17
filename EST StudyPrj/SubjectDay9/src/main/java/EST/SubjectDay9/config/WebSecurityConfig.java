package EST.SubjectDay9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer configure() {      // 1. 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring().requestMatchers(toH2Console()) //h2 DB 콘솔 접근시 보안 무시 설정
                .requestMatchers("/static/**"); //정적 리소스(css, js, 이미지) 등은 인증/인가를 거치지 않게 설정
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->              // 3.인증, 인가 설정
                        auth.requestMatchers("/login", "/signup", "/user").permitAll()      //로그인, 회원가입, 사용자 생성 페이지는 모두 접근 가능
                                //.requestMatchers("/new-article").hasRole("ADMIN")
                                .anyRequest().authenticated())  //requestMatchers 이외의 요청은 인증(로그인 필요)
                .formLogin(auth -> auth.loginPage("/login")     // 4. 폼 기반 로그인 활성화 및 설정
                        .defaultSuccessUrl("/albums"))    //로그인 성공 시 리다이렉트 할 페이지 지정
                .logout(auth -> auth.logoutSuccessUrl("/login") // 5.로그아웃 설정
                        .invalidateHttpSession(true)    //세션 무효화
                        .clearAuthentication(true))     //인증 정보 삭제
                .csrf(auth -> auth.disable());                  // 6.csrf 비활성화
        return httpSecurity.build();
    }
    // 7.패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
