package hello.hellospring;

import hello.hellospring.repository.JdbcTemplatePostRepository;
import hello.hellospring.repository.JdbcTemplateUserRepository;
import hello.hellospring.repository.PostRepository;
import hello.hellospring.repository.UserRepository;
import hello.hellospring.service.PostService;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }
    @Bean
    public PostRepository postRepository() {
        return new JdbcTemplatePostRepository(dataSource);
    }
}
