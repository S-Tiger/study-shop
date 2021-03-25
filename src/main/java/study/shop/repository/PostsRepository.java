package study.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import study.shop.domain.posts.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> , JpaSpecificationExecutor<Posts> {
}
