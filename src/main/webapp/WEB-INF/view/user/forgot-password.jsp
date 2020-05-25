

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
                            <form:form action="${pageContext.request.contextPath}/password/reset" method="POST" class="ui form">
                                <div class="field">
                                    <label>Email</label>
                                    <input type="text" style="position: fixed; left: -10000000px;" disabled/>
                                    <div class="ui left icon input">
                                        <input type="text" name="email" placeholder="email">
                                        <i class="mail icon"></i>
                                    </div>
                                </div>
                                <button class="ui teal submit button">Submit</button>

                                <c:if test="${messageR != null}">
                                    <div class="ui center aligned compact red message" style="width: 100%">
                                        <p>${messageR}!</p>
                                    </div>
                                </c:if>

                                <c:if test="${messageG != null}">
                                    <div class="ui center aligned compact green message" style="width: 100%">
                                        <p>${messageG}!</p>
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

</div>



</body>

</html>

