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

    <script src="/js/jquery.js"></script>
    <script src="/js/semantic.js"></script>

</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<div class="ui hidden section divider"></div>
<div class="ui container">


    <div class="ui card">
        <div class="image">
            <div class="ui rotate reveal image">
                <div class="visible content">
                    <img src="${user.avatar}" >
                </div>
                <div class="hidden content">
                    <img src="${user.avatar}" >
                </div>
            </div>
        </div>
        <div class="content">
            <a class="ui header red">${user.userName}</a>
            <div class="meta">
                <span class="date">Joined in 2013</span>
            </div>
            <div class="description">
                <c:forEach var="authority" items="${user.authorities}">
                    ${authority.authority}
                </c:forEach>
            </div>
        </div>
        <div class="extra content">
            <a>
                <i class="user icon"></i>
                22 Friends
            </a>
        </div>
    </div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

<script>
    $('.ui.dropdown').dropdown()

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>
</body>

</html>