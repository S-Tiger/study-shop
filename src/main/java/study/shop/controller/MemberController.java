package study.shop.controller;

import com.sun.deploy.net.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.shop.domain.member.MemberResDTO;
import study.shop.service.MemberService;

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
}