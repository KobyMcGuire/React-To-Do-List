package com.techelevator.controller;

import com.techelevator.dao.ToDoListDao;
import com.techelevator.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ToDoListController {

    @Autowired
    private ToDoListDao toDoListDao;

    @RequestMapping(path="/toDoLists", method = RequestMethod.GET)
    public List<ToDoList> fetchToDoLists() {
        return toDoListDao.fetchToDoLists();
    }

    @RequestMapping(path="/toDoLists", method = RequestMethod.POST)
    public ToDoList createToDoList(@RequestBody ToDoList toDoList) {
        return toDoListDao.createToDoList(toDoList);
    }

    @RequestMapping(path = "/toDoLists", method = RequestMethod.PUT)
    public ToDoList updateToDoList(@RequestBody ToDoList toDoList) {
        return toDoListDao.updateToDoList(toDoList);
    }

    @RequestMapping(path="/toDoLists/{id}", method = RequestMethod.GET)
    public ToDoList fetchToDoListById(@PathVariable int id) {
        return null;
    }
}
