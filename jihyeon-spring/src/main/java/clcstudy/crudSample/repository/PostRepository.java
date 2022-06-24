package clcstudy.crudSample.repository;

import clcstudy.crudSample.domain.Post;
import clcstudy.crudSample.domain.User;

import java.util.List;

public interface PostRepository {
    Long upload(Post post);
    List<Post> findAll();
}
