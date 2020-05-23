

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Games</title>
    <link rel="stylesheet" type="text/css" href="/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="icon" href="/img/icon.png" >
    <link rel="stylesheet" type="text/css" href="/css/Semantic-UI-Alert.css">

    <script src="/js/lib/sockjs.js"></script>
    <script src="/js/lib/stomp.js"></script>
    <script src="/js/lib/jquery.js"></script>
    <script src="/js/lib/semantic.js"></script>
    <script src="/js/lib/Semantic-UI-Alert.js"></script>



    <style>

    </style>

</head>
<body  >

<jsp:include page="header.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="login.jsp"></jsp:include>

        <div class="ui hidden section divider"></div>
        <div class="ui hidden section divider"></div>
        <div class="ui hidden section divider"></div>
        
        
        <div class="ui container">
    
            <div class="ui center aligned container">
    
                <img style="width: 100%" src="/uploads/message/admin+github+404.jpg">
               
    
            </div>
    
    
    
        <jsp:include page="footer.jsp"></jsp:include>
    </div>

<script src="/js/game.js"></script>
<script src="/js/sortGame.js"></script>
<script src="/js/lib/fast-avg-color.js"></script>
<script src="/js/listGameDynamicBackground.js"></script>


</body>

</html>