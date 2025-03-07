<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <table>
                    <tr>
                        <th>Username</th>
                        <th>IP</th>
                        <th>Score</th>
                    </tr>
                    <tr>
                        <td>${player.username}</td>
                        <td>${player.ip}</td>
                        <td>${player.score}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>
</body>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</html>
