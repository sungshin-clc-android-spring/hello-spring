package hello.hellospring.repository;

import hello.hellospring.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);

        return user.getId();
    }
    @Override
    public Long login(String email, String passwd) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //findAny는 하나라도 찾아지면 해당 객체 리턴, 없으면 optional null
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

        return "정상적으로 탈퇴되었습니다.";
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
