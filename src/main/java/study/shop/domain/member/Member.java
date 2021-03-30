package study.shop.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import study.shop.domain.BaseEntity;
import study.shop.domain.posts.Posts;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든변수 생성자
@Getter
@Builder // Builder 어노테이션
@DynamicUpdate // DynamicUpdate 지원 어노테이션
@Entity // Entity선언 어노테이션
@Table(name = "STUDY_MEMBER")
public class Member extends BaseEntity {

    @Id // Id 선언 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = AutoIncrement, Auto = MySql계열(AUTOINCREMENT), Oracle계열(SEQUENCE)
    private Long id;

    @Column(length = 30, nullable = false, unique = true) // 컬럼속성 정하는 어노테이션
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(length = 3)
    private String age;

    private String sex;

    @Enumerated(EnumType.STRING) // Enum값을 어떤 형태로 저장할지 결졍 Default값은 int로 된 숫자
    @Column(nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Posts> postsList;

    public Member toUpdate(MemberReqDTO dto){
        this.password = dto.getPassword();
        this.nickName = dto.getNickName();
        this.age = dto.getAge();
        this.sex = dto.getSex();
        return this;
    }

    public String getRoleValue(){
        return this.role.getValue();
    }
}
