package study.shop.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityMemberVO implements UserDetails {

    private String username; //회원ID

    private String password; //비밀번호

    private boolean enabled; //계정 활성여부 (true: 사용가능)

    private boolean accountNonExpired; //계정 만료여부 (true: 사용가능)

    private boolean accountNonLocked; //계정 잠김여부 (true: 사용가능)

    private boolean credentialsNonExpired; //비밀번호 만료여부 (true: 사용가능)

    private Collection<? extends GrantedAuthority> authorities; //계정 권한 목록

    public SecurityMemberVO(Member entity){
        this.username = entity.getId();
        this.password = "{noop}" + entity.getPassword();
    }

}
