package study.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shop.domain.member.Member;
import study.shop.domain.posts.Posts;
import study.shop.domain.posts.PostsReqDto;
import study.shop.domain.posts.PostsResDto;
import study.shop.repository.MemberRepository;
import study.shop.repository.PostsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final이 선언된 Class를 주입
public class PostsService {

    private final PostsRepository postsRepo;
    private final MemberRepository memberRepo;

    /**
     * 게시글 작성
     * @param reqDto
     * @return
     */
    @Transactional
    public PostsResDto save(PostsReqDto reqDto){
        Optional<Member> member = memberRepo.findByMemberId(reqDto.getAuthor());

        return new PostsResDto(postsRepo.save(reqDto.toEntity(member.get())));
    }

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public PostsResDto get(Long id){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        return new PostsResDto(posts);
    }

    /**
     * 게시글 목록
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 조회 속도가 개선
    public Page<PostsResDto> getList(Pageable pageable){
        Page<Posts> postsList = postsRepo.findAll(pageable);
        if (postsList.isEmpty()){
            new IllegalArgumentException("해당 데이터가 존재하지 않습니다.");
        }
        List<PostsResDto> result = postsList.stream().map(o -> new PostsResDto(o)).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, postsList.getTotalElements());
    }

    /**
     * 게시글 수정
     * @param id
     * @param reqDto
     * @return
     */
    @Transactional
    public PostsResDto update(Long id, PostsReqDto reqDto){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."+ id));
        return new PostsResDto(posts.toUpdate(reqDto));
    }

    /**
     * 게시글 삭제
     * @param id
     */
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."+ id));
        postsRepo.delete(posts);
    }

}
