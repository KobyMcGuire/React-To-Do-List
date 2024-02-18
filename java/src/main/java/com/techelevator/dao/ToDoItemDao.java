package com.techelevator.dao;

import com.techelevator.model.ToDoItem;

import java.util.List;

public interface ToDoItemDao {

    List<ToDoItem> fetchToDoItems(int listId);

    ToDoItem createToDoItem(ToDoItem toDoItem);

    ToDoItem updateToDoItem(ToDoItem toDoItem);
}
