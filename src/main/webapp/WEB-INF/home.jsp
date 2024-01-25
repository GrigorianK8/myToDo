<%@ page import="com.example.mytodo.model.ToDo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mytodo.manager.TodoManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>TODO</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<div class="container m-5 p-2 rounded mx-auto bg-light shadow">
    <div class="row m-1 p-4">
        <div class="col">
            <div class="p-1 h1 text-primary text-center mx-auto display-inline-block">
                <i class="fa fa-check bg-primary text-white rounded p-2"></i>
                <u>My Todo-s</u>
            </div>
        </div>
    </div>

    <div class="row m-1 p-3">
        <div class="col col-11 mx-auto">
            <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                <div class="col">
                    <form method="post" action="/addTodo" class="text-center">
                        <input class="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                               type="text" placeholder="Add new ..">
                        <br>
                        Title: <input type="text" name="title"><br>
                        Created Date: <input type="datetime-local" name="createdDate"><br>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="p-2 mx-4 border-black-25 border-bottom"></div>

    <div class="row m-1 p-3 px-5 justify-content-end">
        <div class="col-auto d-flex align-items-center">
            <label class="text-secondary my-2 pr-2 view-opt-label">Filter</label>
            <select class="custom-select custom-select-sm btn my-2">
                <option value="all" selected>All</option>
                <option value="completed">Completed</option>
                <option value="active">Active</option>
                <option value="has-due-date">Has due date</option>
            </select>
        </div>
        <div class="col-auto d-flex align-items-center px-1 pr-3">
            <label class="text-secondary my-2 pr-2 view-opt-label">Sort</label>
            <select class="custom-select custom-select-sm btn my-2">
                <option value="added-date-asc" selected>Added date</option>
                <option value="due-date-desc">Due date</option>
            </select>
            <i class="fa fa fa-sort-amount-asc text-info btn mx-0 px-0 pl-1" data-toggle="tooltip"
               data-placement="bottom" title="Ascending"></i>
            <i class="fa fa fa-sort-amount-desc text-info btn mx-0 px-0 pl-1 d-none" data-toggle="tooltip"
               data-placement="bottom" title="Descending"></i>
        </div>
    </div>

    <div class="row mx-1 px-5 pb-3 w-80">
        <div class="col mx-auto">

            <%
                List<ToDo> todos = (List<ToDo>) request.getAttribute("todos");
                if (todos != null && !todos.isEmpty()) {
                    for (ToDo todo : todos) {
            %>
            <div class="row px-3 align-items-center todo-item rounded">
                <div class="col-auto m-1 p-0 d-flex align-items-center">
                    <h2 class="m-0 p-0">

                        <% if ("COMPLETED".equals(todo.getStatus())) { %>
                        <i class="fa fa-check-square-o text-primary btn m-0 p-0"></i>
                        <% } else { %>
                        <i class="fa fa-square-o text-primary btn m-0 p-0"></i>
                        <% } %>
                    </h2>
                </div>
                <div class="col px-1 m-1 d-flex align-items-center">

                    <input type="text"
                           class="form-control form-control-lg border-0 edit-todo-input bg-transparent rounded px-3"
                           readonly value="<%= todo.getTitle() %>" title="<%= todo.getTitle() %>"/>
                </div>
                <div class="col-auto m-1 p-0 px-3">
                    <a href="/editTodo?id=<%= todo.getId() %>" class="btn btn-primary">Edit</a>
                </div>
                <div class="col-auto m-1 p-0 todo-actions">
                <form action="/deleteToDo" method="get">
                    <input type="hidden" name="id" value="<%= todo.getId() %>">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
            </div>
            <%
                }
            } else {
            %>

            <div class="row px-3">
                <div class="col">
                    <p>No todos available.</p>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootlint/1.1.0/bootlint.min.js"></script>

<script src="/js/app.js"></script>
</body>
</html>
