package study.shop.domain.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 변수 생성자
public class MemberReqDTO {

    @ApiModelProperty(value = "로그인ID", required = true)
    private String loginId;

    @ApiModelProperty(value = "패스워드", required = true)
    private String password;

    @ApiModelProperty(value = "닉네임", required = true)
    private String nickName;

    @ApiModelProperty(value = "나이")
    private String age;

    @ApiModelProperty(value = "성별", required = true)
    private String sex;

    public Member toEntity(){
        return  Member.builder()
                .loginId(loginId)
                .nickName(nickName)
                .password(password)
                .age(age)
                .sex(sex)
                .role(Role.GUEST)
                .build();
    }
}
