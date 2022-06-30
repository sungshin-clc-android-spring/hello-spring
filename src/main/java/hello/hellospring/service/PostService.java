package hello.hellospring.service;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.User;
import hello.hellospring.repository.PostRepository;
import hello.hellospring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //게시글 생성
    public Long create(Post post) {
        return postRepository.save(post);
    }


    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public String deletePost(Long id) {
        return postRepository.delete(id);
    }
}
