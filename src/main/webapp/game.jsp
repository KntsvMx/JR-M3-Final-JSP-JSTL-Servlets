<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Galactic Quest</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h1>Galactic Quest has started</h1>
                <p>Quest has started. You have a choice to finish at this step or logout. If you will choose one of the
                    step you will have defeated</p>
                <div class="button-container">
                    <form action="<c:url value="/logout"/>" method="get">
                        <button type="submit" class="btn btn-primary">Logout</button>
                    </form>
                    <form action="<c:url value="/finish"/>" method="get">
                        <button type="submit" class="btn btn-primary">Finish</button>
                    </form>
                </div>
            </div>
        </div>
</header>
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <form action="<c:url value="/question"/>" method="post">
                    <h2>Question</h2>
                    <p><%
                        String question = (String) session.getAttribute("question");
                        out.println(question);
                    %></p>
                    <div id="answer">
                        <button class="btn btn-success" name="answer" value="true">True</button>
                        <button class="btn btn-danger" name="answer" value="false">False</button>
                    </div>
                </form>
                <div class="question-timer" id="timer"></div>
            </div>
        </div>
    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <p>© 2025 Galactic Quest</p>
            </div>
        </div>
    </div>
</footer>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>