package study.shop.domain.posts;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shop.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsReqDto {

    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    private String content;

    @ApiModelProperty(value = "작성자", required = true)
    private String author;

    public Posts toEntity(Member member){
        return  Posts.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
