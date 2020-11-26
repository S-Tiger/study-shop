package study.shop.apicontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import study.shop.domain.posts.PostsReqDTO;
import study.shop.domain.posts.PostsResDTO;
import study.shop.service.PostsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "게시글 API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class PostsApiController {

    private final PostsService postsService;

    @ApiOperation(value = "게시글 등록", notes = "게시글을 등록 합니다.")
    @PostMapping("/posts")
    public PostsResDTO save(HttpServletResponse res, HttpServletRequest req, @RequestBody PostsReqDTO reqDTO){
        return postsService.save(reqDTO);
    }

    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회 합니다.")
    @GetMapping("/posts/{id}")
    public PostsResDTO get(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id){
        return postsService.get(id);
    }

    @ApiOperation(value = "게시글 목록", notes = "게시글을 목록을 조회 합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지 번호", paramType = "query", dataType = "string", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "페이지 크기", paramType = "query", dataType = "string", defaultValue = "20"),
            @ApiImplicitParam(name = "sort", value = "목록 정렬순서 (Property,asc 또는,desc 입력)", paramType = "query", dataType = "string", allowMultiple = true)
    })
    @GetMapping("/posts")
    public Page<PostsResDTO> getList(HttpServletResponse res, HttpServletRequest req, @ApiIgnore Pageable pageable){
        return postsService.getList(pageable);
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정 합니다.")
    @PutMapping("/posts/{id}")
    public PostsResDTO delete(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id, @RequestBody PostsReqDTO reqDTO){
        return postsService.update(id, reqDTO);
    }
    
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제 합니다.")
    @DeleteMapping("/posts/{id}")
    public void delete(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id){
        postsService.delete(id);
    }
}
