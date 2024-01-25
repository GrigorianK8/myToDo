package com.example.mytodo.servlet;

import com.example.mytodo.manager.TodoManager;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet(urlPatterns = "/editTodo")
public class UpdateToDoServlet extends HttpServlet {

    private TodoManager todoManager = new TodoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo todo = todoManager.getToDoById(id);
        req.setAttribute("todo", todo);
        req.getRequestDispatcher("/WEB-INF/editTodo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int todoId = Integer.parseInt(req.getParameter("todoId"));
        String title = req.getParameter("title");
        String status = req.getParameter("status");
        String finishDateStr = req.getParameter("finishDate");
        Date finishDate = null;
        try {
            finishDate = DateUtil.stringToDate(finishDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ToDo updatedTodo = new ToDo();
        updatedTodo.setId(todoId);
        updatedTodo.setTitle(title);
        updatedTodo.setStatus(status);
        updatedTodo.setFinishDate(finishDate);

        todoManager.update(updatedTodo);
        resp.sendRedirect(req.getContextPath() + "/todos");
    }
}
