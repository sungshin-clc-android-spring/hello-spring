package clcstudy.crudSample.service;

import clcstudy.crudSample.domain.Post;
import clcstudy.crudSample.domain.User;
import clcstudy.crudSample.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long upload(Post post) {
        return postRepository.upload(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }
}
