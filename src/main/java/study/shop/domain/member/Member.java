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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DynamicUpdate
@Entity
@Table(name = "STUDY_MEMBER")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 30, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(length = 3)
    private String age;

    private String sex;

    @Enumerated(EnumType.STRING) //Enum값을 어떤 형태로 저장할지 결졍 Default값은 int로 된 숫자
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
