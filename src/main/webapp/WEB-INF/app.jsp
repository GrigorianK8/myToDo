<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>

<body>



<div class="main">

    <% if (session.getAttribute("msg") != null) { %>
    <span style="color: darkred"><%=session.getAttribute("msg")%></span>
    <% session.removeAttribute("msg"); %>
    <% } %>

    <input type="checkbox" id="chk" aria-hidden="true">

    <div class="signup">
        <form action="/register" method="post">
            <label for="chk" aria-hidden="true">Sign up</label>
            <input type="text" name="name" placeholder="name" required="">
            <input type="text" name="surname" placeholder="surname" required="">
            <input type="email" name="email" placeholder="email" required="">
            <input type="text" name="password" placeholder="password" required="">
            <button type="submit">Sign up</button>
        </form>
    </div>

    <div class="login">
        <form action="/login" method="post">
            <label for="chk" aria-hidden="true">Login</label>
            <input type="email" name="email" placeholder="email" required="">
            <input type="password" name="password" placeholder="password" required="">
            <button type="submit">Login</button>
        </form>
    </div>
</div>

</body>
</html>
