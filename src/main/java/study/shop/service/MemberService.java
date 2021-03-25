package study.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shop.domain.member.Member;
import study.shop.domain.member.MemberReqDTO;
import study.shop.domain.member.MemberResDTO;
import study.shop.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //final이 선언된 Class를 주입
public class MemberService /*implements UserDetailsService*/ {

    private final MemberRepository memberRepo;

    @Transactional
    public void save(MemberReqDTO reqDTO){
        memberRepo.findByLoginId(reqDTO.getLoginId()).ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다."); });
        memberRepo.save(reqDTO.toEntity());
    }

    public MemberResDTO get(Long id){
        Member member = memberRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        return new MemberResDTO(member);
    }

    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 조회 속도가 개선
    public Page<MemberResDTO> getList(Pageable pageable){
        Page<Member> memberList = memberRepo.findAll(pageable);
        if (memberList.isEmpty()){
            new IllegalArgumentException("해당 데이터가 존재하지 않습니다.");
        }
        List<MemberResDTO> result = memberList.stream().map(o -> new MemberResDTO(o)).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, memberList.getTotalElements());
    }

    @Transactional
    public MemberResDTO update(Long id, MemberReqDTO reqDTO){
        Member member = memberRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        return new MemberResDTO(member.toUpdate(reqDTO));
    }

    @Transactional
    public void delete (Long id){
        Member member = memberRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        memberRepo.delete(member);
    }

//    @Override
//    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
//        Member member = memberRepo.findByLoginId(loginId).get();
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        return new User(member.getL)
//    }
}
