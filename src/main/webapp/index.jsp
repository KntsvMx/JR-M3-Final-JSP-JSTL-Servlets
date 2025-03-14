<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <h1>Galactic Quest</h1>
                <p>Embark on an interstellar adventure with Galactic Quest! Test your knowledge about the cosmos, from
                    planets and stars to black holes and galaxies. Each question will take you deeper into the mysteries
                    of the universe. Are you ready to become a space explorer?</p>
                <div class="button-container">
                    <form method="get" action="<c:url value='/start'/>">
                        <button type="submit" class="btn btn-primary">Start Test Quest</button>
                    </form>
                    <form method="get" action="<c:url value='/logout'/>">
                        <c:if test="${errorMessage != null}">
                            <p class="error">${errorMessage}</p>
                        </c:if>
                        <button type="submit" class="btn btn-primary">Logout</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>
<section class="section">
    <div class="container d-flex justify-content-center align-items-center">
        <div class="text-center">
            <h1>Embark on an interstellar adventure with Galactic Quest!</h1>
            <p>Welcome to Galactic Quest! In this game, you will journey through the vast expanse of space,
                answering
                questions that will challenge your understanding of the universe. Whether you're a seasoned
                astronomer
                or a curious novice, Galactic Quest offers a fun and educational experience for all. Press
                "Start Test
                Quest" to begin your adventure among the stars!</p>
        </div>
        <img src="images/space_image.png" class="side-image" alt="Space Image">
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