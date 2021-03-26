package study.shop.domain.postsfile;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.shop.domain.posts.Posts;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsFileReqDTO {

    @ApiModelProperty(value = "파일경로명")
    private String filePathNm;

    @ApiModelProperty(value = "파일논리명")
    private String fileLogcNm;

    @ApiModelProperty(value = "파일물리명")
    private String filePhysicsNm;

    @ApiModelProperty(value = "파일확장자명")
    private String fileExpansionNm;

    @ApiModelProperty(value = "파일크기")
    private String fileSize;

    public PostsFile toEntity(Posts posts){
        return PostsFile.builder()
                .posts(posts)
                .filePathNm(filePathNm)
                .fileLogcNm(fileLogcNm)
                .filePhysicsNm(filePhysicsNm)
                .fileExpansionNm(fileExpansionNm)
                .fileSize(fileSize)
                .downloadNum(0L)
                .build();
    }
}
