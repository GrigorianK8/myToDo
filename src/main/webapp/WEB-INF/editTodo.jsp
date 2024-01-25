<%@ page import="com.example.mytodo.model.ToDo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Edit ToDo</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<%
    List<ToDo> todos = (List<ToDo>) request.getAttribute("todos");
    if (todos != null && !todos.isEmpty()) {
        ToDo todo = todos.get(0);
        String status = todo.getStatus().toString();
        String finishDate = new SimpleDateFormat("yyyy-MM-dd").format(todo.getFinishDate());
%>

<div class="container m-5 p-2 rounded mx-auto bg-light shadow">
    <div class="row m-1 p-4">
        <div class="col">
            <div class="p-1 h1 text-primary text-center mx-auto display-inline-block">
                <i class="fa fa-check bg-primary text-white rounded p-2"></i>
                <u>Edit ToDo</u>
            </div>
        </div>
    </div>

    <div class="row m-1 p-3">
        <div class="col col-11 mx-auto">
            <form action="/editTodo" method="post">
                <input type="hidden" id="todoId" name="todoId" value="<%= request.getParameter("id") %>">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" value="<%= todo.getTitle() %>">
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status">
                        <option value="TODO" <%= status.equals("TODO") ? "selected" : "" %>>TODO</option>
                        <option value="IN_PROGRESS" <%= status.equals("IN_PROGRESS") ? "selected" : "" %>>IN PROGRESS</option>
                        <option value="DONE" <%= status.equals("DONE") ? "selected" : "" %>>DONE</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="finishDate">Finish Date:</label>
                    <input type="date" class="form-control" id="finishDate" name="finishDate" value="<%= finishDate %>">
                </div>
                <button type="submit" class="btn btn-primary">Update Todo</button>
            </form>
        </div>
    </div>
</div>

<% } %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="/js/editTodo.js"></script>
</body>
</html>
