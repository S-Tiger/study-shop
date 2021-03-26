package study.shop.domain.postsfile;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import study.shop.domain.BaseEntity;
import study.shop.domain.posts.Posts;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DynamicUpdate
@Entity
@Table(name = "STUDY_POSTS_FILE")
public class PostsFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSTS_FILE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    @Column(nullable = false)
    private String filePathNm;

    @Column(nullable = false)
    private String fileLogcNm;

    @Column(nullable = false)
    private String filePhysicsNm;

    private String fileExpansionNm;

    private String fileSize;

    private Long downloadNum;

}
