package study.shop.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shop.domain.postsfile.PostsFileResDTO;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsResDto {

    private Long no;

    private String title;

    private String content;

    private String author;

    private String createDate;

    private Long postsViews;

    private List<PostsFileResDTO> postsFileList;

    public PostsResDto(Posts posts){
        this.no = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getMember().getNickName();
        this.createDate = posts.getCreateDate().substring(0, posts.getCreateDate().indexOf("T"));
        this.postsViews = posts.getPostsViews();
        if (posts.getPostsFileList() != null) {
            this.postsFileList = posts.getPostsFileList().stream().map(o -> new PostsFileResDTO(o)).collect(Collectors.toList());
        }
    }
}
