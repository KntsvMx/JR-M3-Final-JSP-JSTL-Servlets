<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Failure</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h1>Galactic Quest</h1>
                <p>Oh no! You have failed the quest. Would you like to try again?</p>
                <div class="button-container">
                    <form method="get" action="<c:url value="/start"/>">
                        <button type="submit" class="btn btn-primary">Try Again</button>
                    </form>
                    <form method="get" action="<c:url  value="/logout"/>">
                        <c:if test="${errorMessage != null}">
                            <p class="error">${errorMessage}</p>
                        </c:if>
                        <button type="submit" class="btn btn-primary">Logout</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
