package study.shop.domain.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import study.shop.domain.BaseEntity;
import study.shop.domain.member.Member;
import study.shop.domain.postsfile.PostsFile;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DynamicUpdate
@Entity
@Table(name = "STUDY_POSTS")
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입을 TEXT로 변경하고 싶을경우
    private String content;

    private Long postsViews;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts", cascade = CascadeType.ALL)
    private List<PostsFile> postsFileList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Posts toUpdate(PostsReqDto reqDTO){
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        return this;
    }

    public Posts views(){
        this.postsViews ++;
        return this;
    }

}
