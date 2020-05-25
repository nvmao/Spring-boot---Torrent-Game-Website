

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


</head>
<body  >

<jsp:include page="../header.jsp"></jsp:include>

<div id="wrapper" style="padding-bottom: 14.3rem">
    <jsp:include page="../login.jsp"></jsp:include>


    <div class="ui section hidden divider"></div>
    <div class="ui section hidden divider"></div>
    <div class="ui section hidden divider"></div>
    <div class="ui section hidden divider"></div>
    <div class="ui section hidden divider"></div>

    <div class="ui  container">
        <div class="ui centered grid">
            <c:forEach var="pub" items="${genres}">
                <div class="five wide column">
                    <h1 class="ui olive basic big button" style="width: 100%">${pub.name}</h1>
                    <a class="ui red tiny label" href="${pageContext.request.contextPath}/genres/${pub.id}/delete">X</a>
                </div>
            </c:forEach>
        </div>

        <div class="ui centered grid" style="margin-top: 100px">
            <form:form class="ui form" method="POST" action="${pageContext.request.contextPath}/genres/add">
                <input name="name" type="text" placeholder="publisher">
                <button class="ui basic blue button">Add</button>
            </form:form>
        </div>

    </div>





    <jsp:include page="../footer.jsp"></jsp:include>
</div>

<script src="/js/lib/fast-avg-color.js"></script>



</body>


</html>