package study.shop.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shop.domain.member.Member;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 변수 생성자
public class PostsResDTO {

    private Long no;

    private String title;

    private String content;

    private String author;

    private String createDate;

    public PostsResDTO(Posts posts){
        this.no = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getMember().getNickName();
        this.createDate = posts.getCreateDate().substring(0, posts.getCreateDate().indexOf("T"));
    }
}
