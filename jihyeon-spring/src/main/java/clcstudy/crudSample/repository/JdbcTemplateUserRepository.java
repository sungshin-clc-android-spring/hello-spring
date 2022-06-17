package clcstudy.crudSample.repository;

import clcstudy.crudSample.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("passwd", user.getPasswd());
        parameters.put("name", user.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        return key.longValue();
    }

    @Override
    public String update(Long id, User user) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getLong("id"));
            user.setEmail(rs.getString(("email")));
            user.setName(rs.getString("name"));
            user.setPasswd(rs.getString("passwd"));
            return user;
        };
    }
}
