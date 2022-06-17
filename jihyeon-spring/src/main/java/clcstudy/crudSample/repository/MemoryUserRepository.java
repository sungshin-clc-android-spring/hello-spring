package clcstudy.crudSample.repository;

import clcstudy.crudSample.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(User user) {
        user.setUserId(++sequence);
        store.put(user.getUserId(), user);
        return user.getUserId();
    }

    @Override
    public String update(Long id, User user) {
        User u = store.get(id);
        u.setEmail(user.getEmail());
        u.setPasswd(user.getPasswd());
        u.setName(user.getName());

        store.replace(id, u);

        return "정보 수정이 완료되었습니다.";
    }

    @Override
    public String delete(Long id) {
        store.remove(id);
        return "정상적으로 탈퇴 되었습니다.";
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void cleareStore() {
        store.clear();
    }
}
