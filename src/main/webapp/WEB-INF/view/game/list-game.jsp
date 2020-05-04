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


    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/semantic.js"></script>


</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>


<div class="ui hidden section divider"></div>
<div class="ui hidden section divider"></div>
<div class="ui  container">

    <div class="ui center aligned container">

        <div class="ui grid ">
            <div class="row">
                <div class="three wide column">

                    <div class="ui animated basic tiny button" tabindex="0">
                        <div onclick="toggleSidebar()" class="hidden content">
                            <i class=" th  icon"></i></div>
                        <div class="visible content">
                            <i class=" th  icon"></i>
                        </div>
                    </div>
                    <div class="ui animated basic tiny button" tabindex="0">
                        <div onclick="toggleSidebar()" class="hidden content">
                            <i class=" th  list icon"></i>
                        </div>
                        <div class="visible content">
                            <i class=" th  list icon"></i>

                        </div>
                    </div>
                    <div class="ui animated basic tiny button" tabindex="0">
                        <div onclick="toggleSidebar()" class="hidden content"><i class="sort amount down icon"></i></div>
                        <div class="visible content">
                            <i class="sort amount down icon"></i>
                        </div>
                    </div>
                </div>
                <div class="ten wide column">
                    <div class="ui mini borderless  segment">
                        <div class="ui mini  secondary pointing four item menu">
                            <a class=" item ">
                                Everything
                            </a>
                            <a class="active item ">
                                New
                            </a>
                            <a class="item">
                                Most downloaded
                            </a>
                            <a class="item">
                                Most like
                            </a>
                        </div>
                    </div>
                </div>
                <div class="three wide column">
                    <div class="ui animated basic secondary big button" tabindex="0">
                        <div onclick="toggleSidebar()" class="hidden content">
                            <i class="tasks icon"></i>
                        </div>
                        <div class="visible content">
                            <i onclick="toggleSidebar()" class=" tasks icon"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui inverted section divider"></div>

        <div>
            <div class="ui grid "  id="gameContainer">
                <%--                <c:forEach var="game" items="${games}">--%>
                <%--                    <div class="four wide column">--%>
                <%--                        <div class="ui card">--%>
                <%--                            <div class="ui slide masked reveal image">--%>
                <%--                                <img src="${game.posterPhoto}" class="visible content">--%>
                <%--                                <img src="${game.hoverPhoto}" class="hidden content">--%>
                <%--                            </div>--%>
                <%--                            <div class="content">--%>
                <%--                                <a href="${pageContext.request.contextPath}/games/${game.id}" class="ui small header">${game.name}</a>--%>
                <%--                                <div class="meta">--%>
                <%--                                    <span class="date">${game.publisher.name}</span>--%>
                <%--                                </div>--%>
                <%--                            </div>--%>
                <%--                            <div class="extra content">--%>
                <%--                            <span class="left floated like">--%>
                <%--                                <a class="ui circular label">--%>
                <%--                                    <i class="like icon"></i>23--%>
                <%--                                </a>--%>
                <%--                                <a class="ui blue circular label">--%>
                <%--                                    <i class="comment icon"></i>${game.getCommentCount()}--%>
                <%--                                </a>--%>
                <%--                            </span>--%>
                <%--                                <span class="right floated star">--%>
                <%--                               <a class="ui grey circular label">--%>
                <%--                                    <i class="download icon"></i>100--%>
                <%--                                </a>--%>
                <%--                            </span>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                </c:forEach>--%>
            </div>
        </div>

    </div>
    <div>
        <div class="ui right sidebar inverted vertical list">
            <a class="item">
                1
            </a>
            <a class="item">
                2
            </a>
            <a class="item">
                3
            </a>
        </div>

    </div>
    <div class="ui  divider"></div>
    <div class="ui center aligned container">
        <% for(int i = 0 ; i < (long)request.getAttribute("maxPage");i++){ %>
        <a  class="ui tiny secondary inverted circular button pageButton">
            <%=i+1%>
        </a>
        <%}%>
    </div>

</div>


<jsp:include page="../login.jsp"></jsp:include>


<jsp:include page="../footer.jsp"></jsp:include>



<script src="/js/game.js"></script>

</body>

</html>