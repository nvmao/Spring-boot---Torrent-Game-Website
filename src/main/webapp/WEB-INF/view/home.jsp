

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


</head>
<body  >

<jsp:include page="header.jsp"></jsp:include>

<div id="wrapper" style="padding-bottom: 9.3rem">
    <jsp:include page="login.jsp"></jsp:include>



    <div style="background: #162c36;padding-bottom: 10%;padding-top: 10%">
        <div class="ui container">

            <div class="ui grid">
                <div class="six wide column" style="padding-top: 50px">
                    <h1 class="ui massive header" style="color: #999999;font-family: Tangerine;font-style: italic;"></h1>
                    <h4 class="ui tiny header" style="color: #999999;font-style: italic;"></h4>
                </div>
                <div class="ten wide column">
                    <div class="games-box">
                        <c:forEach var="game" items="${loveGames}">
                            <img class="ui tiny image game-mini-box" src="${game.posterPhoto}">
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="ui section hidden divided"></div>

        </div>

    </div>

    <svg class="svg-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 10" preserveAspectRatio="none">
        <line x1="100%" y1="0%" x2="0%" y2="100%" stroke="#B8C4C2" style="stroke-width: 0.1;"></line>
        <polygon points="100 0 100 10 0 10" />
    </svg>

    <div style="background: #1b2428;padding-bottom: 2%;padding-top:2%">
        <div class="ui container" >

            <a class="ui massive header" style="color: #999999">Recommend</a>
            <a class="ui basic small blue button" style="float:right;margin-top: 30px"> See more </a>
            <h2 class="ui section divider" style="margin-top: 2px"></h2>
            <div class="ui section hidden divider"></div>

            <div class="ui grid">
                <div class="eight wide column">
                    <div id="big-gameBoxCard" class="ui card gameBoxCard" >
                        <div class="ui slide left masked reveal image">
                            <img src="${randomGames[0].posterPhoto}" class="visible content posterImage">
                            <img src="${randomGames[0].hoverPhoto}" class="hidden content">
                        </div>
                        <div class="content">
                            <a href="${CONTEXT_PATH}/games/${randomGames[0].id}" class="ui small header">${randomGames[0].name}</a>
                            <p>${randomGames[0].description}</p>
                            <div class="meta">
                                <span class="date">${randomGames[0].publisher.name}</span>
                            </div>
                            <div class="meta">
                                <span class="date">
                                    <c:forEach var="gerne" items="${randomGames[0].gernes}" varStatus="loop">
                                        ${gerne.name}
                                        <c:if test="${loop.index < randomGames[0].gernes.size()-1}">
                                            ,
                                        </c:if>
                                    </c:forEach>
                                </span>
                            </div>
                        </div>
                        <div class="extra content">
                        <span class="left floated like">
                            <a class="ui mini circular label" id="like_${randomGames[0].id}" onclick="loveGame('${randomGames[0].id}','${randomGames[0].loveCount}')" >
                                <i class="like icon" ></i>${randomGames[0].loveCount}
                            </a>
                            <a class="ui mini blue circular label">
                                <i class="comment icon"></i>${randomGames[0].commentCount}
                            </a>
                        </span>
                            <span class="right floated star">
                           <a class="ui mini grey circular label">
                                <i class="download icon"></i>${randomGames[0].downloadCount}
                            </a>
                        </span>
                        </div>
                    </div>
                </div>
                <div class="eight wide column">
                    <div class="ui grid">
                        <c:forEach var="game" items="${randomGames}" varStatus="loop">
                            <c:if test="${loop.index > 0}">
                                <div class="eight wide column">
                                    <div class="ui card gameBoxCard" >
                                        <div class="ui slide left masked reveal image">
                                            <img src="${game.posterPhoto}" class="visible content posterImage">
                                            <img src="${game.hoverPhoto}" class="hidden content">
                                        </div>
                                        <div class="content">
                                            <a href="${CONTEXT_PATH}/games/${game.id}" class="ui small header">${game.name}</a>
                                            <div class="meta">
                                                <span class="date">${game.publisher.name}</span>
                                            </div>
                                            <div class="meta">
                                                <span class="date">
                                                    <c:forEach var="gerne" items="${game.gernes}" varStatus="loop">
                                                        ${gerne.name}
                                                        <c:if test="${loop.index < game.gernes.size()-1}">
                                                            ,
                                                        </c:if>
                                                    </c:forEach>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="extra content">
                                        <span class="left floated like">
                                            <a class="ui mini circular label" id="like_${game.id}" onclick="loveGame('${game.id}','${game.loveCount}')" >
                                                <i class="like icon" ></i>${game.loveCount}
                                            </a>
                                            <a class="ui mini blue circular label">
                                                <i class="comment icon"></i>${game.commentCount}
                                            </a>
                                        </span>
                                            <span class="right floated star">
                                           <a class="ui mini grey circular label">
                                                <i class="download icon"></i>${game.downloadCount}
                                            </a>
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <svg class="svg-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 10" preserveAspectRatio="none">
        <line x1="100%" y1="0%" x2="0%" y2="100%" stroke="#B8C4C2" style="stroke-width: 0.1;"></line>
        <polygon points="100 0 100 10 0 10" />
    </svg>


    <div style="background: #162c36;padding-bottom: 2%;padding-top:2%">
        <div class="ui container">

            <a class="ui massive header" style="color: #999999">Latest Games</a>
            <a class="ui basic small blue button" style="float:right;margin-top: 30px"> See more </a>
            <h2 class="ui section divider" style="margin-top: 2px"></h2>
            <div class="ui section hidden divider"></div>

            <div class="ui centered grid">
                <c:forEach var="game" items="${latestGames}" varStatus="loop">
                    <div class="four wide column">
                        <div class="ui card gameBoxCard" >
                            <div class="ui slide left masked reveal image">
                                <img src="${game.posterPhoto}" class="visible content posterImage">
                                <img src="${game.hoverPhoto}" class="hidden content">
                            </div>
                            <div class="content">
                                <a href="${CONTEXT_PATH}/games/${game.id}" class="ui small header">${game.name}</a>
                                <div class="meta">
                                    <span class="date">${game.publisher.name}</span>
                                </div>
                                <div class="meta">
                                    <span class="date">
                                        <c:forEach var="gerne" items="${game.gernes}" varStatus="loop">
                                            ${gerne.name}
                                            <c:if test="${loop.index < game.gernes.size()-1}">
                                                ,
                                            </c:if>
                                        </c:forEach>
                                    </span>
                                </div>
                            </div>
                            <div class="extra content">
                                    <span class="left floated like">
                                        <a class="ui mini circular label" id="like_${game.id}" onclick="loveGame('${game.id}','${game.loveCount}')" >
                                            <i class="like icon" ></i>${game.loveCount}
                                        </a>
                                        <a class="ui mini blue circular label">
                                            <i class="comment icon"></i>${game.commentCount}
                                        </a>
                                    </span>
                                <span class="right floated star">
                                       <a class="ui mini grey circular label">
                                            <i class="download icon"></i>${game.downloadCount}
                                        </a>
                                    </span>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>
    </div>
    <svg class="svg-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 10" preserveAspectRatio="none">
        <line x1="100%" y1="0%" x2="0%" y2="100%" stroke="#B8C4C2" style="stroke-width: 0.1;"></line>
        <polygon points="100 0 100 10 0 10" />
    </svg>

    <div style="background: #1b2428;padding-bottom: 2%;padding-top:2%">
        <div class="ui container">

            <a class="ui massive header" style="color: #999999">Most Downloaded Games</a>
            <a class="ui basic small blue button" style="float:right;margin-top: 30px"> See more </a>
            <h2 class="ui section divider" style="margin-top: 2px"></h2>
            <div class="ui section hidden divider"></div>


            <div class="ui centered grid">
                    <c:forEach var="game" items="${downloadGames}" varStatus="loop">
                        <div class="four wide column">
                            <div class="ui card gameBoxCard" >
                                <div class="ui slide left masked reveal image">
                                    <img src="${game.posterPhoto}" class="visible content posterImage">
                                    <img src="${game.hoverPhoto}" class="hidden content">
                                </div>
                                <div class="content">
                                    <a href="${CONTEXT_PATH}/games/${game.id}" class="ui small header">${game.name}</a>
                                    <div class="meta">
                                        <span class="date">${game.publisher.name}</span>
                                    </div>
                                    <div class="meta">
                                        <span class="date">
                                            <c:forEach var="gerne" items="${game.gernes}" varStatus="loop">
                                                ${gerne.name}
                                                <c:if test="${loop.index < game.gernes.size()-1}">
                                                    ,
                                                </c:if>
                                            </c:forEach>
                                        </span>
                                    </div>

                                </div>

                                <div class="extra content">
                                        <span class="left floated like">
                                            <a class="ui mini circular label" id="like_${game.id}" onclick="loveGame('${game.id}','${game.loveCount}')" >
                                                <i class="like icon" ></i>${game.loveCount}
                                            </a>
                                            <a class="ui mini blue circular label">
                                                <i class="comment icon"></i>${game.commentCount}
                                            </a>
                                        </span>
                                    <span class="right floated star">
                                           <a class="ui mini grey circular label">
                                                <i class="download icon"></i>${game.downloadCount}
                                            </a>
                                        </span>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>
        </div>
    </div>
    <svg class="svg-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 10" preserveAspectRatio="none">
        <line x1="100%" y1="0%" x2="0%" y2="100%" stroke="#B8C4C2" style="stroke-width: 0.1;"></line>
        <polygon points="100 0 100 10 0 10" />
    </svg>

    <div style="background: #162c36;padding-bottom: 2%;padding-top:2%">
        <div class="ui container">

            <a class="ui massive header" style="color: #999999">Gernes</a>
<%--            <a class="ui basic small blue button" style="float:right;margin-top: 30px"> See more </a>--%>
            <h2 class="ui section divider" style="margin-top: 2px"></h2>
            <div class="ui section hidden divider"></div>

            <div class="ui grid">
                <div class="ui centered grid">
                    <c:forEach var="gerne" items="${gernes}" varStatus="loop">
                        <c:if test="${loop.index %2 ==0}">
                            <div class="five wide column">
                                <a class="ui big basic olive button" style="width: 100%;">${gerne.name}</a>
                            </div>
                        </c:if>
                        <c:if test="${loop.index %2 !=0}">
                            <div class="five wide column">
                                <a class="ui big basic grey button" style="width: 100%;">${gerne.name}</a>
                            </div>
                        </c:if>

                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
    <svg class="svg-1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 10" preserveAspectRatio="none">
        <line x1="100%" y1="0%" x2="0%" y2="100%" stroke="#B8C4C2" style="stroke-width: 0.1;"></line>
        <polygon points="100 0 100 10 0 10" />
    </svg>


        <jsp:include page="footer.jsp"></jsp:include>
</div>

    <script src="/js/lib/fast-avg-color.js"></script>


    <script>
        $.get('https://api.quotable.io/random',(data)=>{
            document.querySelector("h1").innerText='"'+data.content+'"'
            document.querySelector("h4").innerText='"'+data.author+'"'
        })

        let gamesMini = document.querySelectorAll(".game-mini-box")
        gamesMini.forEach(game=>{
            game.style.left = Math.floor(Math.random() * 100+10 ) +'%'
            game.style.top = Math.floor(Math.random() * 150 ) +'%'
            game.style.transform = 'rotateZ('+Math.random() * 360+'deg)'
        })
        dynamicBackgroundCard()

        function dynamicBackgroundCard() {

            let gameBoxCard = document.querySelectorAll('.gameBoxCard')

            gameBoxCard.forEach(gameCard=>{

                let posterImage = gameCard.querySelector('.posterImage')

                let fac = new FastAverageColor();

                fac.getColorAsync(posterImage.src)
                    .then(function(color) {
                        gameCard.setAttribute("style",
                            'background: linear-gradient(45deg, #222222,'+ color.hex +') !important;')

                    }).catch(function(e) {
                    console.log(e);
                });
            })
        }

    </script>

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

</body>


</html>