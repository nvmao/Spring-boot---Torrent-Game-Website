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

    <script src="/js/jquery.js"></script>
    <script src="/js/semantic.js"></script>

</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>

<div class="ui hidden section divider"></div>
<div class="ui container">

    <div class="ui center aligned container">

    </div>

    <div class="ui black segment">
        <h4 class="ui dividing header">Game Information</h4>
        <form:form class="ui form" action="${pageContext.request.contextPath}/games/${game.id}/edit" method="POST" enctype="multipart/form-data" modelAttribute="game" >

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
                <img class="ui small image" src="${game.posterPhoto}">
                <input name="pPhoto" type="file">
            </div>
            <div class="required field">
                <label>Hover Photo</label>
                <img class="ui small image" src="${game.hoverPhoto}">
                <input name="hPhoto" type="file">
            </div>

            <div class="required field">
                <label>Detail Photos</label>
                <div class="ui grid">
                    <c:forEach var="photo" items="${game.photos}">
                        <div class="two wide column">
                            <img class="ui fluid image" src="${photo.link}">
                        </div>
                    </c:forEach>
                </div>

                <div class="ui hidden divider"></div>
                <div class="ui basic secondary button" onclick="showModal()">Add </div>
            </div>


            <div class="required field">
                <label>Publisher</label>
                <div class="ui selection dropdown">
                    <input name="publisher_id" type="hidden" value="${game.publisher.id}">
                    <i class="dropdown icon"></i>
                    <div class="default text">${game.publisher.name}</div>
                    <div class="menu">
                        <c:forEach var="p" items="${publishers}">
                            <div class="item" data-value="${p.id}">${p.name}</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="required field">
                <label>Description</label>
                <Form:textarea rows="10" path="description"></Form:textarea>
            </div>

            <form:input path="posterPhoto" type="hidden" value="${game.posterPhoto}"></form:input>
            <form:input path="hoverPhoto" type="hidden" value="${game.hoverPhoto}" ></form:input>

            <button class="ui black button">Submit</button>

        </form:form>
    </div>

    <div class="ui modal">
        <div class="header">Add image</div>
        <div class="content">
            <div class="ui black segment">
                <form:form class="ui form" action="${pageContext.request.contextPath}/photos/add" method="post" enctype="multipart/form-data">
                    <input name="gameId" type="hidden" value="${game.id}">
                    <div class="field">
                        <input type="file" name="photo" multiple>
                    </div>
                    <button class="ui basic secondary button">Add</button>
                </form:form>
            </div>
        </div>
    </div>


</div>

<jsp:include page="../footer.jsp"></jsp:include>

<script>

    function showModal(){
        $('.ui.modal')
            .modal('show')
    }

    $('.ui.dropdown').dropdown()

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>
</body>

</html>