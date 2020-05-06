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
<body >


<jsp:include page="../header.jsp"></jsp:include>
<div class="parallax" style="background-image: url('${game.posterPhoto}');"></div>

<div id="wrapper">
    <div class="ui hidden section divider"></div>
    <div class="ui container">

        <div class="ui grid">

            <div class="row">
                <div class="ten wide column">

                    <div class="ui hidden divider"></div>

                    <div class="ui cube shape">
                        <div class="sides">
                            <c:forEach var="photo" items="${game.photos}" varStatus="loop">
                                <c:if test="${loop.index == 0}">
                                    <div class="active side">
                                        <div class="content">
                                            <img src="${photo.link}" class="ui image fluid">
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${loop.index != 0}">
                                    <div class="side">
                                        <div class="content">
                                            <img src="${photo.link}" class="ui image fluid">
                                        </div>
                                    </div>
                                </c:if>

                            </c:forEach>

                        </div>
                    </div>

                    <div class="ui ignored divider"></div>
                    <div class="ui ignored icon direction buttons">
                        <div class="ui button" data-animation="flip" onclick="flipLeft()" title="Flip Left" data-direction="left"><i class="left long arrow icon"></i></div>
                        <div class="ui button" data-animation="flip" onclick="flipUp()" title="Flip Up" data-direction="up"><i class="up long arrow icon"></i></div>
                        <div class="ui icon button" data-animation="flip" onclick="flipDown()" title="Flip Down" data-direction="down"><i class="down long arrow icon"></i></div>
                        <div class="ui icon button" data-animation="flip" onclick="flipRight()" title="Flip Right" data-direction="right"><i class="right long arrow icon"></i></div>
                        <div class="ui button" title="Flip Over" onclick="flipOver()" data-animation="flip" data-direction="over"><i class="retweet icon"></i></div>
                        <div class="ui button" title="Flip Back" onclick="flipBack()" data-animation="flip" data-direction="back"><i class="flipped retweet icon"></i></div>
                    </div>
                </div>
                <div class="one wide column">
                    <div class="ui vertical divider">
                        <->
                    </div>
                </div>
                <div class="five wide column">
                    <a class="ui black button" href="${pageContext.request.contextPath}/games/${game.id}/edit" >Edit</a>
                    <div class="ui hidden section divider"></div>
                    <h1 class="ui big header">
                        <div class="ui center aligned container">
                            ${game.name}
                        </div>
                    </h1>
                    <div class="ui hidden section divider"></div>
                    <div class="ui raised card" style="width: 400px;">
                        <div class="content">
                            <div class=" ui huge header">
                                Download
                            </div>
                            <div class="description">
                                <p>
                                <div class="ui tiny secondary basic button">
                                    direct
                                </div>
                                <div class="ui tiny secondary basic button">
                                    torrent
                                </div>
                                <div class="ui tiny secondary basic button">
                                    drive
                                </div>
                                </p>

                            </div>
                        </div>
                        <div class="extra content">
                            <div class="left floated author">
                                <i class=" icon download"></i> 123
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
                            <p>Action, Adventure, Role-play</p>
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

            <div class="row">
                <div class="twelve wide column">
                    <div class="ui comments">
                        <h3 class="ui dividing header">Comments</h3>

                        <c:forEach var="comment" items="${game.comments}">
                            <div class="comment">
                                <a class="avatar">
                                    <img src="${comment.user.avatar}">
                                </a>
                                <div class="content" >
                                    <a class="author">${comment.user.userName}</a>
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
                                                    <c:forEach var="reply" items="${comment.replies}">
                                                        <div class="comment">
                                                            <a class="avatar">
                                                                <img src="${reply.user.avatar}">
                                                            </a>
                                                            <div class="content">
                                                                <a class="author">${reply.user.userName}</a>
                                                                <div class="metadata">
                                                                    <span class="date">${reply.createdAt}</span>
                                                                </div>
                                                                <div class="text">
                                                                        ${reply.content}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    <h3 class="ui dividing header"></h3>
                                                    <form:form action="${pageContext.request.contextPath}/games/${game.id}/comment/reply" method="POST">
                                                        <div class="field">
                                                            <textarea name="replyContent" rows="4" cols="80"></textarea>
                                                        </div>
                                                        <input type="hidden" value="${comment.id}" name="commentId">
                                                        <button class="ui blue labeled submit icon button">
                                                            <i class="icon edit"></i> Add Reply
                                                        </button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </c:forEach>

                        <h3 class="ui dividing header"></h3>
                        <form:form action="${pageContext.request.contextPath}/games/${game.id}/comment" method="POST">
                            <div class="field">
                                <textarea name="commentContent" rows="4" cols="80"></textarea>
                            </div>
                            <button class="ui blue labeled submit icon button">
                                <i class="icon edit"></i> Add Comment
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <jsp:include page="../login.jsp"></jsp:include>
    <jsp:include page="../footer.jsp"></jsp:include>
</div>


<script src="/js/fast-avg-color.js"></script>
<script src="/js/dynamicBackground.js"></script>
<script>

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