package study.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shop.domain.member.Member;
import study.shop.domain.member.MemberReqDto;
import study.shop.domain.member.MemberResDto;
import study.shop.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final이 선언된 Class를 주입
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepo;

    /**
     * 회원정보 저장
     * @param reqDto
     */
    @Transactional
    public void joinUser(MemberReqDto reqDto){
        memberRepo.findByMemberId(reqDto.getMemberId()).ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다."); });
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        reqDto.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        memberRepo.save(reqDto.toEntity());
    }

    /**
     * 회원정보 목록
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 조회 속도가 개선
    public Page<MemberResDto> getList(Pageable pageable){
        Page<Member> memberList = memberRepo.findAll(pageable);
        if (memberList.isEmpty()){
            new IllegalArgumentException("해당 데이터가 존재하지 않습니다.");
        }
        List<MemberResDto> result = memberList.stream().map(o -> new MemberResDto(o)).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, memberList.getTotalElements());
    }

    /**
     * 회원정보 수정
     * @param memberId
     * @param reqDTO
     * @return
     */
    @Transactional
    public MemberResDto update(String memberId, MemberReqDto reqDTO){
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        return new MemberResDto(member.toUpdate(reqDTO));
    }

    /**
     * 회원정보 삭제
     * @param memberId
     */
    @Transactional
    public void delete (String memberId){
        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        memberRepo.delete(member);
    }

    /**
     * Spring Security 로그인
     * @param memberId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepo.findByMemberId(memberId).get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(member.getRoleValue()));
        return new User(member.getMemberId(), member.getPassword(), authorities);
    }

    /**
     * 회원 아이디로 회원정보 검색
     * @param memberId
     * @return
     */
    public MemberResDto findByMemberId(String memberId) {
        Member member = memberRepo.findByMemberId(memberId).orElseThrow(() ->  new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        MemberResDto ResDTO = new MemberResDto(member);
        return ResDTO;
    }
}
