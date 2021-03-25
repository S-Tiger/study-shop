package study.shop.apicontroller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import study.shop.domain.member.MemberReqDTO;
import study.shop.domain.member.MemberResDTO;
import study.shop.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "회원 API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation(value = "회원 등록", notes = "회원을 등록 합니다.")
    @PostMapping("/member")
    public int save(HttpServletResponse res, HttpServletRequest req, @RequestBody MemberReqDTO reqDTO){
        int result;
        try {
            memberService.save(reqDTO);
            result = 1;
        }catch (IllegalStateException e){
            result = 0;
        }
        return result;
    }

    @ApiOperation(value = "회원 조회", notes = "회원을 조회 합니다.")
    @GetMapping("/member/{id}")
    public MemberResDTO get(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id){
        return memberService.get(id);
    }

    @ApiOperation(value = "회원 목록", notes = "회원을 목록을 조회 합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지 번호", paramType = "query", dataType = "string", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "페이지 크기", paramType = "query", dataType = "string", defaultValue = "20"),
            @ApiImplicitParam(name = "sort", value = "목록 정렬순서 (Property,asc 또는,desc 입력)", paramType = "query", dataType = "string", allowMultiple = true)
    })
    @GetMapping("/member")
    public Page<MemberResDTO> getList(HttpServletResponse res, HttpServletRequest req, @ApiIgnore Pageable pageable){
        return memberService.getList(pageable);
    }

    @ApiOperation(value = "회원 수정", notes = "회원을 수정 합니다.")
    @PutMapping("/member/{id}")
    public MemberResDTO delete(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id, MemberReqDTO reqDTO){
        return memberService.update(id, reqDTO);
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제 합니다.")
    @DeleteMapping("/member/{id}")
    public void delete(HttpServletResponse res, HttpServletRequest req, @PathVariable Long id){
        memberService.delete(id);
    }
}
