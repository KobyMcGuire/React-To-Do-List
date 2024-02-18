package com.techelevator.controller;

import com.techelevator.dao.ToDoItemDao;
import com.techelevator.model.ToDoItem;
import com.techelevator.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ToDoItemController {

    @Autowired
    private ToDoItemDao toDoItemDao;

    @RequestMapping(path="/toDoItems/{id}", method = RequestMethod.GET)
    public List<ToDoItem> fetchToDoItems(@PathVariable int id) {
        return toDoItemDao.fetchToDoItems(id);
    }

    @RequestMapping(path="/toDoItems", method = RequestMethod.POST)
    public ToDoItem createToDoItem(@RequestBody ToDoItem toDoItem) {
        return toDoItemDao.createToDoItem(toDoItem);
    }

    @RequestMapping(path = "/toDoItems", method = RequestMethod.PUT)
    public ToDoItem updateToDoItem(@RequestBody ToDoItem toDoItem) {
        return toDoItemDao.updateToDoItem(toDoItem);
    }

}
