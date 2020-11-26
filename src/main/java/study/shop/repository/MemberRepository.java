package study.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shop.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByLoginId(String loginId);
}
