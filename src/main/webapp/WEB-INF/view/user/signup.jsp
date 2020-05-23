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
    <link rel="icon" href="/img/icon.png" >
    <script src="/js/lib/jquery.js"></script>
    <script src="/js/lib/semantic.js"></script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div id="wrapper">
    <div class="ui hidden section divider"></div>
    <div class="ui hidden section divider"></div>
    <div class="ui container">

        <div class="ui hidden section divider"></div>

        <div class="ui grid">
            <div class="five wide column"></div>
            <div class="six wide column">

<%--                <c:if test="${messages!=null}">--%>
<%--                    <div id="#error" class="ui error message" >--%>
<%--                        <i class="close icon" onclick="closeError()"></i>--%>
<%--                        <div class="header">--%>
<%--                            There were some errors with your submission--%>
<%--                        </div>--%>
<%--                        <ul >--%>
<%--                            <c:forEach var="message" items="${messages}">--%>
<%--                                <li>${message}</li>--%>
<%--                            </c:forEach>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </c:if>--%>


<%--                <div class="ui indicating progress" data-percent="60" >--%>
<%--                    <div class="bar"></div>--%>
<%--                    <div class="label">Uploading image</div>--%>
<%--                </div>--%>

                <div class="ui placeholder black segment">
                    <h4 class="ui dividing header">User Information</h4>
                    <form:form class="ui form" action="${pageContext.request.contextPath}/users/signup" method="POST" enctype="multipart/form-data" modelAttribute="user" >

                        <div class="required field" id="username-field">
                            <label>Username</label>
                            <div class="ui left icon input">
                                <form:input id="username" path="userName" placeholder="username"></form:input>
                                <i class="user icon"></i>
                            </div>

                        </div>
                        <div class="required field">
                            <label>Password</label>
                            <div class="ui left icon input">
                                <form:input path="password" type="password" placeholder="password"></form:input>
                                <i class="lock icon"></i>
                            </div>
                        </div>
                        <div class="required field">
                            <label>Type your password again</label>
                            <div class="ui left icon input">
                                <input id="password" name="rePassword" type="password" placeholder="password">
                                <i class="lock icon"></i>
                            </div>
                        </div>
                        <div class="field">
                            <label>Avatar</label>
                            <input name="avatarFile" type="file">
                        </div>

                        <button class="ui black button" onclick="removeListUl()">Sign Up</button>

                        <div class="ui error message"></div>

                    </form:form>


                </div>
            </div>
            <div class="five wide column"></div>
        </div>

    </div>
    <jsp:include page="../footer.jsp"></jsp:include>

</div>


<script src="/js/validation/signup-validator.js"></script>

<script>

    function removeListUl() {
       setTimeout(()=>{
           document.querySelector("ul").classList.remove("list")
       },300)
    }

    function closeError() {
        $('.ui.error.message').transition('scale',function () {
            $('.ui.error.message').remove()
        })
    }

    function uploadImage() {
        let ajax = new XMLHttpRequest();
        ajax.onreadystatechange = function () {

            if (ajax.status) {

                if (ajax.status == 200 && (ajax.readyState == 4)){
                }
            }
        }
        ajax.upload.addEventListener("progress", function (event) {

            let percent = (event.loaded / event.total) * 100;
            console.log(percent);

        });

        ajax.open("POST", 'your file upload link', true);
        ajax.send(formData);
    }
</script>


</body>

</html>