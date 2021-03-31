package study.shop.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import study.shop.service.MemberService;

@Configuration
@EnableWebSecurity // 어노테이션을 추가하여 Spring Security 설정할 클래스라고 정의
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService MemberService;

    @Bean // 비밀번호 암호화 객체, Bean으로 등록하여 서비스에서 사용
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override // WebSecurity는 FiterChainProxy를 생성하는 필터
    public void configure(WebSecurity web)throws Exception{
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**"
                ,"/webjars/**", "/swagger-resources/**", "/swagger-ui.html");
    }

    @Override // HttpSecurity는 http요청에 대한 웹 기반 보안을 구성성
    protected void configure(HttpSecurity http)throws Exception{
//        http.httpBasic().disable(); // REST API사용하여 기본설정 사용 안함 (기본설정은 비인증시 로그인폼 화면으로 리다이렉트)
//        http.csrf().disable(); // REST API이므로 csrf 보안이 필요 없음
//        // 페이지 권한 설정
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/member/myinfo").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN");
        // 로그인 설정
        http.formLogin()
                    .loginPage("/member/login") // login경로로 접근하면 SpringSecurity에서 제공하는 From사용, 컨트롤러에 URL 매핑이 되어야한다.
                    .defaultSuccessUrl("/member/login/result") // 로그인 성공시 이동되는 페이지, 컨트롤러에서 URL 매핑이 되어야한다.
                    .permitAll()
                    .usernameParameter("memberId"); // 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데, 해당 메서드를 통해 파라미터명을 변경할 수 있다.
        // 로그아웃 설정
        http.logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 기본 URL(/logout)이 아닌 다른 URL로 재정의
                    .logoutSuccessUrl("/member/logout/result") // 로그 아웃시 이동되는 페이지, 컨트롤러에서 URL 매핑이 되어야한다.
                    .invalidateHttpSession(true); // HTTP 세션을 초기화 하는 작업
        // 에러처리 핸들링
        http.exceptionHandling().accessDeniedPage("/error");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(MemberService).passwordEncoder(passwordEncoder());
    }
}
