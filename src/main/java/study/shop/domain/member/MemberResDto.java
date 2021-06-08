package study.shop.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든변수 생성자
public class MemberResDto {

    private String memberId;

    private String password;

    private String nickName;

    private String age;

    private String sex;

    private String authority;

    private String createDate;

    public MemberResDto(Member member){
        this.memberId = member.getMemberId();
        this.password = member.getPassword();
        this.nickName = member.getNickName();
        this.age = member.getAge();
        this.sex = member.getSex();
        this.authority = member.getRoleValue();
        this.createDate = member.getCreateDate();
    }

}
