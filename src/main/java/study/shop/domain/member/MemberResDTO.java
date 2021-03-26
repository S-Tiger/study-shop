package study.shop.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 변수 생성자
public class MemberResDTO {

    private String memberId;

    private String password;

    private String nickName;

    private String age;

    private String sex;

    private String authority;

    public MemberResDTO(Member member){
        this.memberId = member.getId();
        this.password = member.getPassword();
        this.nickName = member.getNickName();
        this.age = member.getAge();
        this.sex = member.getSex();
        this.authority = member.getRoleValue();
    }

}
