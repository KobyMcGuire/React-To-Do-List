package com.techelevator.dao;

import com.techelevator.model.ToDoList;

import java.util.List;

public interface ToDoListDao {

    List<ToDoList> fetchToDoLists();

    ToDoList createToDoList(ToDoList toDoList);

    ToDoList updateToDoList(ToDoList toDoList);
}
