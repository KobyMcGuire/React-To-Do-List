package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ToDoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcToDoListDao implements ToDoListDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcToDoListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDoList> fetchToDoLists() {
        List<ToDoList> lists = new ArrayList<>();
        String sql = "SELECT * FROM to_do_list";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                lists.add(mapRowToList(results));
            }
        }
        catch(Exception e) {
            throw new DaoException("There was a problem fetching a list of to-do lists.", e);
        }

        return lists;
    }

    @Override
    public ToDoList createToDoList(ToDoList toDoList) {
        String sql = "INSERT INTO to_do_list (title, description) " +
                     "VALUES (?, ?) " +
                     "RETURNING list_id";

        try {
            int id = jdbcTemplate.queryForObject(sql, int.class, toDoList.getTitle(), toDoList.getDescription());
            toDoList.setId(id);
        }
        catch (Exception e) {
            throw new DaoException("There was an error creating a to-do list.", e);
        }

        return toDoList;
    }

    @Override
    public ToDoList updateToDoList(ToDoList toDoList) {
        String sql = "UPDATE to_do_list " +
                     "SET title = ?, description = ? " +
                     "WHERE list_id = ?";

        int rowCount = jdbcTemplate.update(sql, toDoList.getTitle(), toDoList.getDescription(), toDoList.getId());

        if (rowCount < 1) {
            throw  new DaoException("There was issue updating a to-do list.");
        }

        return toDoList;
    }


    private ToDoList mapRowToList(SqlRowSet row) {
        ToDoList list = new ToDoList();
        list.setId(row.getInt("list_id"));
        list.setTitle(row.getString("title"));
        list.setDescription(row.getString("description"));
        return list;
    }
}
