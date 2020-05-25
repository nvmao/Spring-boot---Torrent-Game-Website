<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Games</title>
    <link rel="stylesheet" type="text/css" href="/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/lib/jquery.js"></script>
    <script src="/js/lib/semantic.js"></script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div id="wrapper" style="padding-top: 100px">
    <div class="ui hidden section divider"></div>
    <div class="ui container">

        <a class="ui basic blue button" href="${pageContext.request.contextPath}/publishers"> Manage Publisher </a>
        <a class="ui basic blue button" href="${pageContext.request.contextPath}/genres"> Manage Genres </a>

        <div class="ui center aligned container">

        </div>

        <div class="ui black segment">
            <h4 class="ui dividing header">Game Information</h4>
            <form:form class="ui form" action="${pageContext.request.contextPath}/games/add" method="POST" enctype="multipart/form-data" modelAttribute="game" >

                <div class="required field">
                    <label>Name</label>
                    <form:input path="name"></form:input>
                </div>
                <div class="required field">
                    <label>Download Link</label>
                    <form:input path="downloadLink"></form:input>
                </div>
                <div class="required field">
                    <label>Poster Photo</label>
                    <input name="pPhoto" type="file" accept="image/x-png,image/gif,image/jpeg">
                </div>
                <div class="required field">
                    <label>Hover Photo</label>
                    <input name="hPhoto" type="file" accept="image/x-png,image/gif,image/jpeg">
                </div>
                <div class="required field">
                    <label>Publisher</label>
                    <div class="ui selection dropdown">
                        <input name="publisher_id" type="hidden" value="${game.publisher[0].id}">
                        <i class="dropdown icon"></i>
                        <div class="default text">Publisher</div>
                        <div class="menu">
                            <c:forEach var="p" items="${publishers}">
                                <div class="item" data-value="${p.id}">${p.name}</div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="required field">
                    <label>Genre</label>
                    <select  name="genre_tags"  multiple="" class="ui fluid dropdown inverted" >
                        <option value="">Select Geners</option>
                        <c:forEach var="gener" items="${genres}">
                            <option value="${gener.name}" >${gener.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class=" field">
                    <label>Description</label>
                    <Form:textarea path="description"></Form:textarea>
                </div>

                <button onclick="removeListUl()" class="ui black button">Submit</button>

                <div class="ui error message"></div>
            </form:form>
        </div>


    </div>
    <jsp:include page="../login.jsp"></jsp:include>
    <jsp:include page="../footer.jsp"></jsp:include>
</div>

<script src="/js/validation/add-game-validator.js"></script>
<script>

    function removeListUl() {
        setTimeout(()=>{
            document.querySelector("ul").classList.remove("list")
        },300)
    }

    $('.ui.dropdown').dropdown()

    $('.ui.fluid')
        .dropdown()
    ;

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>
</body>

</html>