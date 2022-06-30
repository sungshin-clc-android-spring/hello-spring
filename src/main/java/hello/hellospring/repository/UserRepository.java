package hello.hellospring.repository;

import hello.hellospring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Long save(User user);
    Long login(String email, String passwd);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    String update(Long id, User user);
    String delete(Long id);
    List<User> findAll();
}
