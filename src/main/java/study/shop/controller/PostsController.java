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

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/posts")
public class PostsController {

    private final PostsService postsService;

    @GetMapping()
    public String getList(Model model, @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "id") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,sort);
        Page<PostsResDTO> result = postsService.getList(pageable);
        model.addAttribute("posts", result);
        return "/posts/list";
    }

    @GetMapping(value = "/{id}")
    public String get(Model model, @PathVariable Long id){
        PostsResDTO result = postsService.get(id);
        model.addAttribute("posts", result);
        return "/posts/read";
    }

    @GetMapping(value = "/save")
    public String save(){
        return "/posts/save";
    }

    @GetMapping(value = "/{id}/update")
    public String update(Model model, @PathVariable Long id){
        PostsResDTO result = postsService.get(id);
        model.addAttribute("posts", result);
        return "/posts/update";
    }
}
