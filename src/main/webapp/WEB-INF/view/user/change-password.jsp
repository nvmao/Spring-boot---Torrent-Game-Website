

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
    <div class="ui hidden section divider"></div>
    <div class="ui hidden section divider"></div>
    <div class="ui container">

        <div class="ui hidden section divider"></div>

        <div class="ui grid">

            <div class="four wide column"></div>
            <div class="eight wide column">

                <div class="ui placeholder blue segment" style="opacity: 95%">

                    <div class="ui centered grid">
                        <div class="ten wide column">
                            <form:form action="${pageContext.request.contextPath}/password/save-password" method="POST" class="ui form">
                                <div class="field">
                                    <label>New Password</label>
                                    <input type="text" style="position: fixed; left: -10000000px;" disabled/>
                                    <div class="ui left icon input">
                                        <input type="text" id="password" name="password" placeholder="password">
                                        <i class="mail icon"></i>
                                    </div>
                                </div>
                                <input name="token" value="${token}" hidden>
                                <div class="field">
                                    <label>Type Password Again</label>
                                    <input type="text" style="position: fixed; left: -10000000px;" disabled/>
                                    <div class="ui left icon input">
                                        <input type="text"  name="rePassowrd" placeholder="password">
                                        <i class="mail icon"></i>
                                    </div>
                                </div>
                                <button class="ui teal submit button">Submit</button>

                                <div class="ui error message"></div>

                                <c:if test="${message != null}">
                                    <div class="ui center aligned compact red message" style="width: 100%">
                                        <p>${message}!</p>
                                    </div>
                                </c:if>


                            </form:form>
                        </div>
                    </div>

                </div>
            </div>
            <div class="four wide column"></div>
        </div>

    </div>
    <jsp:include page="../login.jsp"></jsp:include>
    <jsp:include page="../footer.jsp"></jsp:include>

    <script src="/js/validation/reset-passsword-validator.js"></script>

</div>



</body>

</html>

