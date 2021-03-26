package study.shop.domain.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 변수 생성자
public class MemberReqDTO {

    @ApiModelProperty(value = "회원ID", required = true)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String memberId;
    //@NotBlank : null과 빈 공백 문자열 (" ")을 허용하지 않음
    //@NotEmpty : null과 공백 문자열("")을 허용하지 않음

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,20}"/*(?=.*\W)*/,
            message = "비밀번호는 영문자, 특수문자가 1개 이상 들어간 8~20자의 비밀번호여야 합니다.")
    private String password;
    //@Pattern : 정규표현식에 맞는 문자열이어야 함 
    // (?=.*[0-9]) 숫자가 적어도 하나
    // (?=.*[a-zA-Z]) 영문 대,소문자중 적어도 하나
    // (?=.*\W) 특수문자 적어도 하나
    // (?=\S+$) 공백 제거
    // .{8,20} 8글자 ~ 20글자 사이
    
    @ApiModelProperty(value = "닉네임", required = true)
    private String nickName;

    @ApiModelProperty(value = "나이")
    private String age;

    @ApiModelProperty(value = "성별", required = true)
    private String sex;

    public Member toEntity(){
        return  Member.builder()
                .id(memberId)
                .nickName(nickName)
                .password(password)
                .age(age)
                .sex(sex)
                .role(Role.MEMBER)
                .build();
    }
}
