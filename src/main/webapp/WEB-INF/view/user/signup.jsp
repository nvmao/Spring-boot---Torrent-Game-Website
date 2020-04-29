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

    <div class="ui hidden section divider"></div>

        <div class="ui grid">
            <div class="five wide column"></div>
            <div class="six wide column">
                <div class="ui black segment">
                        <h4 class="ui dividing header">User Information</h4>
                        <form:form class="ui form" action="${pageContext.request.contextPath}/users/signup" method="POST" enctype="multipart/form-data" modelAttribute="user" >

                            <div class="required field">
                                <label>Username</label>
                                <div class="ui left icon input">
                                    <form:input path="userName"></form:input>
                                    <i class="user icon"></i>
                                </div>

                            </div>
                            <div class="required field">
                                <label>Password</label>
                                <div class="ui left icon input">
                                    <form:input path="password" type="password"></form:input>
                                    <i class="lock icon"></i>
                                </div>
                            </div>
                            <div class="required field">
                                <label>Type your password again</label>
                                <div class="ui left icon input">
                                    <input name="rePassword" type="password">
                                    <i class="lock icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>Avatar</label>
                                <input name="avatarFile" type="file">
                            </div>

                            <button class="ui black button">Sign Up</button>

                        </form:form>
                    </div>
            </div>
            <div class="five wide column"></div>
        </div>

    <div class="ui hidden section divider"></div>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>

</html>