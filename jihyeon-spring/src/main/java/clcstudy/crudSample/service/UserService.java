package clcstudy.crudSample.service;

import clcstudy.crudSample.domain.User;
import clcstudy.crudSample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        return userRepository.save(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public String updateUser(Long id, User user){
        return userRepository.update(id, user);
    }

    public String deleteUser(Long id) { return userRepository.delete(id); }
}
