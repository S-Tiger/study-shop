package study.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import study.shop.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> , JpaSpecificationExecutor<Member> {

    public Optional<Member> findByMemberId(String memberId);

}
