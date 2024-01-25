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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/addTodo")
public class AddToDoServlet extends HttpServlet {

    private TodoManager todoManager = new TodoManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        if (todoManager.todoTitleExists(title)) {
            req.getSession().setAttribute("msg", "Title with this name already exists");
            resp.sendRedirect("/home");
            return;
        }

        LocalDate createdDate = LocalDate.now();

        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            resp.sendRedirect("/login");
            return;
        }
        Integer userId = loggedInUser.getId();

        String status = "NEW";

        ToDo newTodo = ToDo.builder()
                .title(title)
                .createdDate(Date.valueOf(createdDate))
                .userId(userId)
                .status(status)
                .build();

        todoManager.add(newTodo);
        System.out.println("Todo added successfully");
        resp.sendRedirect("/todo");
    }
}
