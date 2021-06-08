package study.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shop.domain.member.MemberReqDto;
import study.shop.domain.member.MemberResDto;
import study.shop.service.MemberService;

/**
 * 회원 컨트롤러
 */
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping(value = "/login")
    public String getLogin(){
        return "/member/login";
    }

    /**
     * 로그인 처리 페이지
     * @return
     */
    @GetMapping(value = "/login/result")
    public String loginResult(){
        return "/member/loginSuccess";
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping(value = "/signup")
    public String getSignUp(){
        return "/member/signup";
    }

    /**
     * 회원가입 처리
     * @param reqDto
     * @return
     */
    @PostMapping("/signup")
    public String postSignup(MemberReqDto reqDto) {
        memberService.joinUser(reqDto);
        return "redirect:/member/login";
    }

    /**
     * 로그아웃 결과 페이지
     * @return
     */
    @GetMapping("/logout/result")
    public String logout() {
        return "/member/logout";
    }

    /**
     * 내정보
     * @param authentication
     * @return
     */
    @GetMapping("/myInfo")
    public String myInfo(Authentication authentication, Model model) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        MemberResDto resDto = memberService.findByMemberId(user.getUsername());
        model.addAttribute("loginMember", resDto);
        return "/member/myInfo";
    }


}