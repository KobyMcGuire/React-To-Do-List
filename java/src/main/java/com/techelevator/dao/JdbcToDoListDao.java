package com.techelevator.dao;

import com.techelevator.model.ToDoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcToDoListDao implements ToDoListDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcToDoListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDoList> fetchToDoLists() {
        List<ToDoList> result = null;
        String sql = "SELECT * FROM toDoList";

        return null;
    }

    @Override
    public ToDoList fetchToDoList(int id) {
        return null;
    }
}
