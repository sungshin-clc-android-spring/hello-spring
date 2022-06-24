package clcstudy.crudSample;

import clcstudy.crudSample.repository.JdbcTemplatePostRepository;
import clcstudy.crudSample.repository.JdbcTemplateUserRepository;
import clcstudy.crudSample.repository.PostRepository;
import clcstudy.crudSample.repository.UserRepository;
import clcstudy.crudSample.service.PostService;
import clcstudy.crudSample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new JdbcTemplatePostRepository(dataSource);
    }
}