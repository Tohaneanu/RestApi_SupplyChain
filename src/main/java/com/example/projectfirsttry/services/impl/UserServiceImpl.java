package com.example.projectfirsttry.services.impl;

import com.example.projectfirsttry.entities.Roles;
import com.example.projectfirsttry.entities.User;
import com.example.projectfirsttry.exceptions.NotFoundException;
import com.example.projectfirsttry.repository.UserRepository;
import com.example.projectfirsttry.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
     public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject("select id,name,password from user where id=?",
                    (resultSet, i) -> {
                        User user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setPassword(resultSet.getString("password"));
                        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT name from roles where user_id=?", userId);
                        return user;
                    },
                    userId);

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("product not found");
        }

    }


}
