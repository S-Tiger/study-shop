package study.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.shop.domain.posts.PostsResDTO;
import study.shop.service.PostsService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/posts")
public class PostsController {

    private final PostsService postsService;

    // 게시글 목록 페이지
    @GetMapping()
    public String getList(Model model, @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "id") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,sort);
        Page<PostsResDTO> result = postsService.getList(pageable);
        model.addAttribute("posts", result);
        return "/posts/list";
    }

    // 게시글 상세조회 페이지
    @GetMapping(value = "/{id}")
    public String get(Model model, @PathVariable Long id){
        PostsResDTO result = postsService.get(id);
        model.addAttribute("posts", result);
        return "/posts/read";
    }

    // 게시글 작성 페이지
    @GetMapping(value = "/write")
    public String save(Principal user, Model model){
        String userId = user.getName();
        model.addAttribute("userId", userId);
        return "/posts/write";
    }

    // 게시글 수정 페이지
    @GetMapping(value = "/{id}/update")
    public String update(Model model, @PathVariable Long id){
        PostsResDTO result = postsService.get(id);
        model.addAttribute("posts", result);
        return "/posts/update";
    }
}
