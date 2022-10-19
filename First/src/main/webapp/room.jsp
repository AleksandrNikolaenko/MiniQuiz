<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room</title>
</head>
<body>
    <% String userLogin = (String) session.getAttribute("user_login"); %>
    <h1>User: <%=userLogin%></h1><br>
    <% if ("admin".equals(userLogin)) { %>
    <form action="all_stats">
        <button type="submit">All stats</button>
        <button type="submit" formaction="users">Users</button><br>
        Click this for <a href="index.html">logout</a>
    </form>
    <% } else { %>
    <form action="quiz.html">
        <button type="submit">Quiz</button>
        <button type="submit" formaction="my_stats">My stats</button>
        <button type="submit" formaction="all_stats">All stats</button><br>
        Click this for <a href="index.html">logout</a>
    </form>
    <% } %>
</body>
</html>
