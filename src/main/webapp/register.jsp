<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<section class="register-section">
    <div class="container d-flex justify-content-center flex-column">
        <h2>Register your session</h2>
        <form method="post" class="register-form">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <button class="register-button" type="submit">Register</button>
        </form>
    </div>
</section>
</body>
</html>
