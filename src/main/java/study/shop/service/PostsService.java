package study.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shop.domain.member.Member;
import study.shop.domain.posts.Posts;
import study.shop.domain.posts.PostsReqDTO;
import study.shop.domain.posts.PostsResDTO;
import study.shop.repository.MemberRepository;
import study.shop.repository.PostsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //final이 선언된 Class를 주입
public class PostsService {

    private final PostsRepository postsRepo;
    private final MemberRepository memberRepo;

    @Transactional
    public PostsResDTO save(PostsReqDTO reqDTO){
        Optional<Member> member = memberRepo.findByLoginId(reqDTO.getAuthor());

        return new PostsResDTO(postsRepo.save(reqDTO.toEntity(member.get())));
    }

    public PostsResDTO get(Long id){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
        return new PostsResDTO(posts);
    }

    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 조회 속도가 개선
    public Page<PostsResDTO> getList(Pageable pageable){
        Page<Posts> postsList = postsRepo.findAll(pageable);
        if (postsList.isEmpty()){
            new IllegalArgumentException("해당 데이터가 존재하지 않습니다.");
        }
        List<PostsResDTO> result = postsList.stream().map(o -> new PostsResDTO(o)).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, postsList.getTotalElements());
    }

    @Transactional
    public PostsResDTO update(Long id, PostsReqDTO reqDTO){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."+ id));
        return new PostsResDTO(posts.toUpdate(reqDTO));
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."+ id));
        postsRepo.delete(posts);
    }

}
