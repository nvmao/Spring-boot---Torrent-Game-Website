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

<jsp:include page="../header.jsp"></jsp:include>

<div id="wrapper">
    <jsp:include page="../login.jsp"></jsp:include>

    <div class="ui hidden section divider"></div>
    <div class="ui hidden section divider"></div>
    <div class="ui container">

        <div class="ui center aligned container">

            <div class="ui grid ">
                <div class="row">
                    <div class="three wide column">

                        <div class="ui purple basic tiny button grid-button" tabindex="0" style="margin-top: 15px;box-shadow: 0px 0px 0px 1px #999999 inset !important;color: #999999 !important;">
                            <i class=" th  icon"></i>
                        </div>

                        <div class="ui pink basic tiny button list-button" tabindex="0" style="margin-top: 15px;box-shadow: 0px 0px 0px 1px #999999 inset !important;color: #999999 !important;">
                                <i class=" th  list icon"></i>
                        </div>

                    </div>
                    <div class="ten wide column">
                        <div class="ui mini borderless segment" style="background: transparent;">
                            <div class="ui mini yellow secondary  pointing four item stackable menu" style="background: transparent;">
                                <a class="active sort-menu item " style="color: #999999;border-bottom: 1px solid #999999">
                                    New
                                </a>
                                <a class="sort-menu item " style="color: #999999;border-bottom: 1px solid #999999">
                                    Most commented
                                </a>
                                <a class="sort-menu item" style="color: #999999;border-bottom: 1px solid #999999">
                                    Most downloaded
                                </a>
                                <a class="sort-menu item" style="color: #999999;border-bottom: 1px solid #999999">
                                    Most loved
                                </a>
                            </div>
                        </div>

                        <select onchange="selectGenre()" name="geners"  multiple="" class="ui fluid dropdown inverted" >
                            <option value="">Select Geners</option>
                            <c:forEach var="gener" items="${geners}">
                                <option value="${gener.name}" >${gener.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="three wide column">
                        <div class="ui olive basic tiny button order-button" tabindex="0" style="margin-top: 15px" >
                           <i class="sort amount down icon"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ui inverted section divider"></div>

            <div>
                <div class="ui grid "  id="gameContainer">

                </div>
            </div>

        </div>

        <div id="mobile-menu" class="ui left sidebar inverted vertical menu" >
            <a class="item">
                <div class="ui inverted transparent icon input">
                    <input type="text" placeholder="Search...">
                    <i class="search icon"></i>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/games" class="item"><i class="home icon"></i>Home</a>
            <a href="${pageContext.request.contextPath}/games" class="item"> Game</a>

            <c:if test="${user != null}">


                <a onclick="fetchAllUsers()" id="friendHolder2" class="item" >
                    <i class="smile icon"></i>
                    Friends
                </a>

                <a  class="item " onclick="fetchNotification('1','0')" id="notificationHolder2" >
                    <i class="bell icon"></i>
                    Notification
                    <div class="floating mini ui teal label bell-count"  style="top:12%;left:80%"></div>
                </a>

                <div id="notification-popup2" class="ui popup bottom hidden">
                        <%--                    <h4 class="ui tiny blue header">Everybody is friend</h4>--%>
                    <div class="emoji-container" style="width: 200px" id="notification-list">

                    </div>
                </div>

                <div id="friend-popup2" class="ui popup right center hidden" >
                        <%--                    <h4 class="ui tiny blue header">Everybody is friend</h4>--%>
                    <div class="emoji-container" style="width: 200px;position: static" id="friendList">


                    </div>
                </div>

                <a  class=" item">
                    <img class="ui avatar image" src="${user.avatar}">
                        ${user.userName}
                        <%--                    <i class="dropdown icon"></i>--%>
                        <%--                    <div class="menu">--%>
                        <%--                        <a href="${pageContext.request.contextPath}/users/profile" class="item">--%>
                        <%--                            Profile--%>
                        <%--                        </a>--%>

                        <%--                        <c:if test="${user.authorities[0].authority=='ROLE_ADMIN'}">--%>
                        <%--                            <a href="${pageContext.request.contextPath}/games/add" class="item">--%>
                        <%--                                Add Game--%>
                        <%--                            </a>--%>
                        <%--                        </c:if>--%>
                        <%--                        <div class="item">--%>

                        <%--                            <form:form action="${pageContext.request.contextPath}/success-logout"  method="POST">--%>
                        <%--                                <button class="fluid ui button" type="submit" >Logout</button>--%>
                        <%--                            </form:form>--%>
                        <%--                        </div>--%>
                        <%--                    </div>--%>
                </a>


            </c:if>

            <c:if test="${user == null}">
                <div class="item">
                    <a class="ui basic blue button" href="${pageContext.request.contextPath}/users/signup">
                        <i class="add user icon"></i>Sign Up
                    </a>
                </div>
                <div class="item">
                    <a onclick="showLogin()" class="ui basic teal button ">
                        <i class="sign-in icon"></i>Login</a>
                    </d>
                </div>
            </c:if>

        </div>


        <div class="ui  divider"></div>
        <div class="ui center aligned container" id="button-page-holder">
            <% for(int i = 0 ; i < (long)request.getAttribute("maxPage");i++){ %>
            <a  class="ui tiny secondary inverted circular button pageButton">
                <%=i+1%>
            </a>
            <%}%>
        </div>

    </div>



    <jsp:include page="../footer.jsp"></jsp:include>
</div>

<script src="/js/game.js"></script>
<script src="/js/sortGame.js"></script>
<script src="/js/lib/fast-avg-color.js"></script>
<script src="/js/listGameDynamicBackground.js"></script>

<script>
    $('.ui.dropdown')
        .dropdown()
    ;


</script>


</body>

</html>