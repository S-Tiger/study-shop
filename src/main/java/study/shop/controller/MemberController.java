package study.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shop.domain.member.MemberResDTO;
import study.shop.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/list")
    public String getList(Model model, Pageable pageable) {
        Page<MemberResDTO> result = memberService.getList(pageable);
        model.addAttribute("members", result);
        return "/member/list";
    }

    @GetMapping(value = "/save")
    public String join(){
        return "/member/save";
    }

    @GetMapping(value = "/login")
    public String getLogin(){
        return "/member/login";
    }

    @PostMapping(value = "/login")
    public String postLogin(HttpServletRequest request, Model model){
        return null;

    }


}