import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

interface ToDoList {
  id: number;
  title: string;
  description: string;
}

export default {
  getToDoLists() {
    return http.get("/toDoLists");
  },

  createToDoList(toDoList: ToDoList) {
    return http.post("/toDoLists", toDoList);
  },

  updateToDoList(toDoList: ToDoList) {
    return http.put("/toDoLists", toDoList);
  },
};
