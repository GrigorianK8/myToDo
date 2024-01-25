package com.example.mytodo.servlet;

import com.example.mytodo.manager.TodoManager;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/todo")
public class ToDoServlet extends HttpServlet {

    private TodoManager todoManager = new TodoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside ToDoServlet doGet method");
        List<ToDo> todos = todoManager.getAllTodos();
        System.out.println("Number of todos: " + todos.size());
        req.setAttribute("todos", todos);

        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
