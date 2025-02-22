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
                <p>You have to choose the right answer. If you'll be wrong you're lost</p>
            </div>
        </div>
    </div>
</header>
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2>Question</h2>
                <p>${question}}</p>
                <div id="answer">
                    <button class="btn btn-success" id="trueButton">True</button>
                    <button class="btn btn-danger" id="falseButton">False</button>
                </div>
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