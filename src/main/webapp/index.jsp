<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="StartPageStyle.css">
</head>
<body>
<header>
    <div id="header">
        <span>Добро пожаловать в Dark Souls Shop</span>
    </div>
</header>
<div id="FormBlock">
    <select id="user_selector">
        <option value="1">1</option>
        <option value="2">2</option>
    </select>
    <br>
    <button type="submit" id="Submit" onclick="enter()">Войти</button>
</div>
<script src="jquery-3.6.0.min.js"></script>
<script src="script.js"></script>
</body>
</html>