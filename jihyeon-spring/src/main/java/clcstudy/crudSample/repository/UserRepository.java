package clcstudy.crudSample.repository;

import clcstudy.crudSample.domain.User;

import java.util.List;

public interface UserRepository {
    Long save(User user);
    Long login(User user);

    String update(Long id, User user);
    String delete(Long id);
    List<User> findAll();
}
