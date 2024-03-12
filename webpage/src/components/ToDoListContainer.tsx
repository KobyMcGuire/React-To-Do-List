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

  function showToDoItems() {}

  return (
    <>
      <h1 className="text-center">To-Do List</h1>
      <ul className="list-group">
        {toDoLists.map((toDoList) => (
          <li
            onClick={showToDoItems}
            className="list-group-item"
            key={toDoList.id}
          >
            {toDoList.title}

            <button
              type="button"
              className="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#toDoModal"
            >
              {" "}
              View To-Do List Items
            </button>
          </li>
        ))}
      </ul>

      <div
        className="modal fade"
        id="toDoModal"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                To Do Items
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">...</div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="button" className="btn btn-primary">
                Save changes
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
