package study.shop.domain.postsfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsFileResDTO {

    private String filePathNm;

    private String fileLogcNm;

    private String filePhysicsNm;

    private String fileExpansionNm;

    private String fileSize;

    public PostsFileResDTO(PostsFile postsFile) {
        this.filePathNm = postsFile.getFilePathNm();
        this.fileLogcNm = postsFile.getFileLogcNm();
        this.filePhysicsNm = postsFile.getFilePhysicsNm();
        this.fileExpansionNm = postsFile.getFileExpansionNm();
        this.fileSize = postsFile.getFileSize();
    }
}
