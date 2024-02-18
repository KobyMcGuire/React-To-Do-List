package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ToDoItem;
import com.techelevator.model.ToDoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcToDoItemDao implements ToDoItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcToDoItemDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<ToDoItem> fetchToDoItems(int listId) {
        List<ToDoItem> items = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM to_do_item " +
                     "WHERE list_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        }
        catch (Exception e) {
            throw new DaoException("There was an issue fetching the to-do items.", e);
        }

        return items;
    }

    @Override
    public ToDoItem createToDoItem(ToDoItem toDoItem) {
        String sql = "INSERT INTO to_do_item (list_id, title, description) " +
                     "VALUES (?, ?, ?) " +
                     "RETURNING item_id";

        try {
            int itemId = jdbcTemplate.queryForObject(sql, int.class, toDoItem.getListId(), toDoItem.getTitle(), toDoItem.getDescription());
            toDoItem.setId(itemId);
        }
        catch (Exception e) {
            throw new DaoException("There was an issue creating a to-do item.", e);
        }

        return toDoItem;
    }

    @Override
    public ToDoItem updateToDoItem(ToDoItem toDoItem) {
        String sql = "UPDATE to_do_item " +
                     "SET list_id = ?, title = ?, description = ? " +
                     "WHERE item_id = ?";

        int rowCount = jdbcTemplate.update(sql, toDoItem.getListId(), toDoItem.getTitle(), toDoItem.getDescription(), toDoItem.getId());

        if (rowCount < 1) {
            throw new DaoException("There was an issue updating a to-do item.");
        }

        return toDoItem;
    }


    private ToDoItem mapRowToItem(SqlRowSet row) {
        ToDoItem item = new ToDoItem();
        item.setId(row.getInt("item_id"));
        item.setListId(row.getInt("list_id"));
        item.setTitle(row.getString("title"));
        item.setDescription(row.getString("description"));
        return item;
    }
}
