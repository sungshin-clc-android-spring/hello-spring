package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long save(Post post);
    Optional<Post> findById(Long id);
    Optional<Post> findByTitle(String title);
    String update(Long id, Post post);
    String delete(Long id);
    List<Post> findAll();
}
