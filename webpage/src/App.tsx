import { useState } from "react";
import { ToDoListContainer } from "./components/ToDoListContainer";
import "./App.css";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <ToDoListContainer></ToDoListContainer>
    </>
  );
}

export default App;
