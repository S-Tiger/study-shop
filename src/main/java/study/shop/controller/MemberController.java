package study.shop.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shop.domain.member.MemberReqDTO;
import study.shop.domain.member.MemberResDTO;
import study.shop.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping(value = "/login")
    public String getLogin(){
        return "/member/login";
    }

    // 로그인 처리
    @GetMapping(value = "/login/result")
    public String loginResult(HttpServletRequest request, Model model){
        return "/member/loginSuccess";
    }

    // 회원가입 페이지
    @GetMapping(value = "/signup")
    public String getSignUp(){
        return "/member/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String postSignup(MemberReqDTO memberDto) {
        memberService.joinUser(memberDto);
        return "redirect:/member/login";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "/member/logout";
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String dispMyInfo() {
        return "/member/myinfo";
    }


}