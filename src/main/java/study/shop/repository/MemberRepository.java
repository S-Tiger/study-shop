package study.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import study.shop.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member,String> , JpaSpecificationExecutor<Member> {

}
