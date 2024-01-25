package com.example.mytodo.servlet;

import com.example.mytodo.manager.TodoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteToDo")
public class DeleteToDoServlet extends HttpServlet {

    private TodoManager todoManager = new TodoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        todoManager.deleteTodo(id);
        resp.sendRedirect("/home");
    }
}
