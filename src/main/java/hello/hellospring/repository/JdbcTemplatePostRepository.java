package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import hello.hellospring.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", post.getUserId());
        parameters.put("title", post.getTitle());
        parameters.put("contents", post.getContents());
        parameters.put("img_path", post.getImg_path());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        return key.longValue();
    }

//    public String getUserName(long userId) {
//        String sql = "select name from user where id = ?";
//        Object[] params = new Object[]{userId};
//        String userName = jdbcTemplate.queryForObject(sql, params, String.class);
//        return userName;
//    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Post post) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from post join user on post.userId = user.id", postRowMapper());
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContents(rs.getString("contents"));
            post.setImg_path(rs.getString("img_path"));
            post.setUserId(rs.getLong("userId"));
            post.setUserName(rs.getString("name"));
            return post;
        };
    }
}
