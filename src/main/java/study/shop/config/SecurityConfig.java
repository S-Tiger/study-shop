//package study.shop.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import study.shop.service.MemberService;
//
//@Configuration
//@EnableWebSecurity //@EnableWebSecurity 어노테이션을 추가하여 Spring Security 설정할 클래스라고 정의.
//@AllArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private MemberService memberService;
//
//    @Bean //비밀번호 암호화 객체, Bean으로 등록하여 서비스에서 사용
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override //WebSecurity는 FiterChainProxy를 생성하는 필터
//    public void configure(WebSecurity web)throws Exception{
//        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
//    }
//
//    @Override //HttpSecurity는 http요청에 대한 웹 기반 보안을 구성성
//   protected void configure(HttpSecurity http)throws Exception{
//        http.authorizeRequests()
//                // 페이지 권한 설정
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/user/myinfo").hasRole("MEMBER")
//                    .antMatchers("/**").permitAll()
//                .and() // 로그인 설정
//                    .formLogin()
//                    .loginPage("/member/login") //login경로로 접근하면 SpringSecurity에서 제공하는 From사용, 컨트롤러에 URL 매핑이 되어야한다.
//                    .defaultSuccessUrl("/user/login/result") //로그인 성공시 이동되는 페이지, 컨트롤러에서 URL 매핑이 되어야한다.
//                    .permitAll()
//                    .usernameParameter("loginId") //로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데, 해당 메서드를 통해 파라미터명을 변경할 수 있다.
//                .and() // 로그아웃 설정
//                    .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 기본 URL(/logout)이 아닌 다른 URL로 재정의
//                    .logoutSuccessUrl("/user/logout/result")
//                    .invalidateHttpSession(true) //HTTP 세션을 초기화 하는 작업
//                .and()
//                // 403 예외처리 핸들링
//                .exceptionHandling().accessDeniedPage("/user/denied");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
//}
