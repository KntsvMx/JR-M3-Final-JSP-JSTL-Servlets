<%@ page import="model.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Statistic</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2>Statistic</h2>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>IP</th>
                        <th>Score</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Player player = (Player) session.getAttribute("player");
                        if (player != null) {
                    %>
                    <tr>
                        <td><%= player.getName() %>
                        </td>
                        <td><%= player.getIp() %>
                        </td>
                        <td><%= player.getScore() %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="button-container">
            <form action="<c:url value="/logout"/>" method="get">
                <button type="submit" class="btn btn-primary">Finish</button>
            </form>
        </div>
    </div>
</section>
</body>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
