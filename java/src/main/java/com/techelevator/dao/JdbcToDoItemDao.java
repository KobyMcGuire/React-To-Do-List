package com.techelevator.dao;

import com.techelevator.model.ToDoItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcToDoItemDao implements ToDoItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcToDoItemDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public ToDoItem fetchToDo(int id) {
        return null;
    }


}
