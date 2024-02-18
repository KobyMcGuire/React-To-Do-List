import React, { useState } from "react";
import ToDoListService from "../services/ToDoListService";
import { useEffect } from "react";

interface ToDoList {
  id: number;
  title: string;
  description: string;
}

export const ToDoListContainer = () => {
  //State variables using useState
  const [toDoLists, setToDoLists] = useState<ToDoList[]>([]);

  // Hook to grab API data
  useEffect(() => {
    ToDoListService.getToDoLists()
      .then((response) => {
        if (response.status === 200) {
          setToDoLists(response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  });

  return (
    <>
      <h1>ToDoListContainer</h1>
      <ul>
        {toDoLists.map((toDoList) => (
          <li key={toDoList.id}>{toDoList.title}</li>
        ))}
      </ul>
    </>
  );
};
