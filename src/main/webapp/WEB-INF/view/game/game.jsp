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
    <link rel="stylesheet" type="text/css" href="/css/Semantic-UI-Alert.css">
    <link rel="icon" href="/img/icon.png" >

    <script src="/js/lib/sockjs.js"></script>
    <script src="/js/lib/stomp.js"></script>
    <script src="/js/lib/jquery.js"></script>
    <script src="/js/lib/semantic.js"></script>
    <script src="/js/lib/Semantic-UI-Alert.js"></script>

</head>
<body  >


<jsp:include page="../header.jsp"></jsp:include>
<div class="parallax" style="background-image: url('${game.posterPhoto}');"></div>

<div id="wrapper" >


    <div class="ui hidden section divider"></div>
    <div class="ui container">



        <div class="ui grid">

            <div class="row">
                <div class="ten wide column">

                    <div class="ui hidden divider"></div>

                    <div id="slider">
                        <div class="left">
                            <i class="ui big left arrow icon"></i>
                        </div>
                        <div class="right">
                            <i class="ui big right arrow icon"></i>
                        </div>
                        <ul class="slides">
                            <c:forEach var="detailImage" items="${game.photos}">
                                <li class="slide"> <img src="${detailImage.link}"> </li>
                            </c:forEach>

                        </ul>
                    </div>

                    <div class="ui ignored divider"></div>

                </div>
                <div class="one wide column">

                </div>
                <div class="five wide column">
                    <c:if test="${user.userName.equals('admin')}">
                        <a class="ui black button" href="${pageContext.request.contextPath}/games/${game.id}/edit" >Edit</a>
                        <a class="ui red button" onclick="showDeleteGame()" >Delete</a>
                    </c:if>
                    <div class="ui hidden section divider"></div>
                    <h1 class="ui big header">
                        <div class="ui center aligned container">
                            ${game.name}
                        </div>
                    </h1>
                    <div class="ui hidden section divider"></div>
                    <div class="ui raised card" style="width: auto;background: transparent">
                        <div class="content">
                            <div class=" ui huge header">
                                Download
                            </div>
                            <div class="description">
                                <p>

                                <a href="${game.downloadLink}" target="_blank" rel="noopener noreferrer" onclick="downloadGame(${game.id})"  class="ui large green basic button" style="margin-right: 10px">
                                    torrent
                                </a>
                                <a class="ui tiny yellow basic button disabled"  style="margin-right: 10px">
                                    direct
                                </a>
                                <a class="ui tiny blue basic button disabled" style="margin-right: 10px">
                                    drive
                                </a>
                                </p>

                            </div>
                        </div>
                        <div class="extra content">
                            <div class="left floated author" id="downloadCountLabel">
                                <i class=" icon download"></i> ${game.downloadCount}
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="ui inverted section divider"></div>

            <div class="row">
                <div class="ten wide column">
                    <div class="ui big header">Description</div>
                    <div class="ui inverted divider"></div>
                    <p>
                        ${game.description}
                    </p>
                </div>
                <div class="six wide column">
                    <div class="ui big header">Game details</div>
                    <div class="ui inverted divider"></div>

                    <div class="ui middle aligned selection list">
                        <div class="item">
                            <div class="content">
                                <div class="header">Genre</div>
                            </div>
                            <p>
                                <c:forEach var="g" items="${game.gernes}" varStatus="loop">
                                    <c:if test="${loop.index == end-1}">
                                        ${g.name}
                                    </c:if>
                                    <c:if test="${loop.index != end-1}">
                                        #${g.name},
                                    </c:if>
                                </c:forEach>
                            </p>
                        </div>
                        <div class="item">
                            <div class="content">
                                <div class="header">Release date</div>
                            </div>
                            <p>2020-02-05</p>
                        </div>
                        <div class="item">
                            <div class="content">
                                <div class="header">Language</div>
                            </div>
                            <p>English</p>
                        </div>
                        <div class="item">
                            <div class="content">
                                <div class="header">Cracked by</div>
                            </div>
                            <p>NO DRM</p>
                        </div>
                        <div class="item">
                            <div class="content">
                                <div class="header">Publisher</div>
                            </div>
                            <p>${game.publisher.name}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="ten wide column">
                    <div class="ui big header">System requirement</div>
                    <div class="ui inverted divider"></div>
                    <div class="ui two column grid">
                        <div class=" column">
                            Minimum
                            <div class="ui inverted divider"></div>
                            <p>Windows 7</p>
                            <p>Intel CPU Core i5-2500K 3.3 GHz</p>
                            <p>6 GB RAM</p>
                            <p>Nvidia GPU GeForce GTX 660 / AMD GPU Radeon HD 7870</p>
                            <p>35 GB of available space</p>
                        </div>
                        <div class=" column">
                            Recommended
                            <div class="ui inverted divider"></div>
                            <p>Windows 7 / 8 (64bit)</p>
                            <p>Intel CPU Core i7 3770 3.4 GHz / AMD CPU AMD FX-8350 4 GHz</p>
                            <p>8 GB RAM</p>
                            <p>Nvidia GPU GeForce GTX 770 / AMD GPU Radeon R9 290</p>
                            <p>35 GB of available space</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row ">
                <div class="twelve wide column">
                    <div class="ui comments">
                        <h3 class="ui dividing header">Comments</h3>

                        <form:form class="ui form" id="comment-form" onsubmit="return false" action="${pageContext.request.contextPath}/games/${game.id}/comment" method="POST">
                            <input id="game-id" value="${game.id}" hidden>
                            <input id="user-id" value="${user.id}" hidden>

                            <div class="inline fields">
                                <div class="field">
                                    <textarea id="comment-input" name="commentContent" rows="3" cols="55"></textarea>
                                </div>
                                <div class="field">
                                    <button onclick="comment()" class="ui blue small labeled submit icon button">
                                        <i class="icon edit"></i> Add Comment
                                    </button>
                                </div>

                            </div>

                        </form:form>
                        <div id="comment-holder" class="ui divider  hidden"></div>

                        <c:forEach var="comment" items="${game.comments}">
                            <div class="comment">
                                <a class="avatar">
                                    <img src="${comment.user.avatar}">
                                </a>
                                <div class="content" >
                                    <a class="author user-comment">${comment.user.userName}</a>
                                    <div class="metadata">
                                        <span class="date">${comment.createdAt}</span>
                                    </div>
                                    <div class="text">
                                            ${comment.content}
                                    </div>
                                    <div class="actions">
                                        <div class="ui accordion">
                                            <div class="title">
                                                <i class="dropdown icon"></i>
                                                    ${comment.replies.size()} reply
                                            </div>
                                            <div class="content">
                                                <div class="comments">
                                                    <form:form id="reply-form_${comment.id}" onsubmit="return false" method="POST">
                                                        <div class="field">
                                                            <textarea id="reply-input_${comment.id}" name="replyContent" rows="4" cols="80"></textarea>
                                                        </div>
                                                        <input type="hidden" value="${comment.id}" name="commentId">
                                                        <button onclick="reply('${comment.id}')" class="ui blue labeled submit icon button">
                                                            <i class="icon edit"></i> Add Reply
                                                        </button>
                                                    </form:form>

                                                    <h3 class="ui dividing header" id="reply-holder_${comment.id}"></h3>

                                                    <c:forEach var="reply" items="${comment.replies}">
                                                        <div class="comment">
                                                            <a class="avatar">
                                                                <img src="${reply.user.avatar}">
                                                            </a>
                                                            <div class="content">
                                                                <a class="user-comment">${reply.user.userName}</a>
                                                                <div class="metadata">
                                                                    <span class="date">${reply.createdAt}</span>
                                                                </div>
                                                                <div class="text">
                                                                        ${reply.content}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </c:forEach>


                    </div>
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

                <div id="friend-popup2" class="ui popup right center hidden" >
                        <%--                    <h4 class="ui tiny blue header">Everybody is friend</h4>--%>
                    <div class="emoji-container" style="width: 200px;position: static" id="friendList">


                    </div>
                </div>

                <a  class=" item">
                    <img class="ui avatar image" src="${user.avatar}">
                        ${user.userName}
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

    </div>


    <div  class="ui delete basic modal">
        <div class="ui icon header">
            <i class="archive icon"></i>
            Delete The game
        </div>
        <div class="content">
            <p>The comments of this game also get delete?</p>
        </div>
        <div class="actions">
            <div class="ui red basic cancel inverted button">
                <i class="remove icon"></i>
                No
            </div>
            <a href="${pageContext.request.contextPath}/games/${game.id}/delete" class="ui green ok inverted button">
                <i class="checkmark icon"></i>
                Yes
            </a>
        </div>
    </div>

    <jsp:include page="../login.jsp"></jsp:include>
    <jsp:include page="../footer.jsp"></jsp:include>
</div>


<script src="/js/lib/fast-avg-color.js"></script>
<script src="/js/dynamicBackground.js"></script>
<script src="/js/comment.js"></script>
<script src="/js/slider.js"></script>
<script>

    function showDeleteGame() {
        $('.ui.delete.basic.modal')
            .modal('show')
        ;
    }

    function downloadGame(gameId){
        let req = url+'/api/games/download/'+gameId
        console.log(req)
        let downloadCountLabel = document.getElementById("downloadCountLabel")
        $.get(req,function (res) {
            downloadCountLabel.innerHTML =`<i class=" icon download"></i>`+res
        })
    }

    $('.ui.accordion')
        .accordion()
    ;

    $('.shape').shape();

    function flipUp(){
        $('.shape')
            .shape('set next side', '.second.side')
            .shape('flip up')
        ;
    }
    function flipDown(){
        $('.shape').shape('flip down');
    }
    function flipLeft(){
        $('.shape').shape('flip left');
    }
    function flipRight(){
        $('.shape').shape('flip right');
    }
    function flipOver(){
        $('.shape').shape('flip over');
    }
    function flipBack(){
        $('.shape').shape('flip back');
    }

    $('.ui.dropdown').dropdown()


    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>
</body>

</html>