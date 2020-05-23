

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

            <div class="four wide column"></div>
            <div class="eight wide column">

                <div class="ui placeholder segment">

                    <c:if test="${param.error != null}">
                        <div class="ui error message">
                            <ul>
                                <li>Username or password incorrect</li>
                            </ul>
                        </div>
                    </c:if>


                    <div class="ui two column very relaxed stackable grid">
                        <div class="column">
                            <form:form action="${pageContext.request.contextPath}/do-login" method="POST" class="ui form">
                                <div class="field">
                                    <label>Username</label>
                                    <input type="text" style="position: fixed; left: -10000000px;" disabled/>
                                    <div class="ui left icon input">
                                        <input type="text" name="username" placeholder="Username">
                                        <i class="user icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>Password</label>
                                    <div class="ui left icon input">
                                        <input name="password"  type="password" placeholder="*******">
                                        <i class="lock icon"></i>
                                    </div>
                                </div>
                                <button class="ui teal submit button">Login</button>




                            </form:form>
                        </div>
                        <div class="middle aligned column">
                            <div class="ui big button">
                                <i class="signup icon"></i>
                                Sign Up
                            </div>


                        </div>
                    </div>

                </div>
            </div>
            <div class="four wide column"></div>
        </div>

    </div>
    <jsp:include page="../footer.jsp"></jsp:include>

</div>



</body>

</html>

