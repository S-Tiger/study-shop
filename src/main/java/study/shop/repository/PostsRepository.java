package study.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shop.domain.posts.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
