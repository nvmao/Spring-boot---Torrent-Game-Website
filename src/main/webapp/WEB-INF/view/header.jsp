<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="ui fixed borderless  inverted menu">
        <a href="${pageContext.request.contextPath}/games" class="header item">
<%--            <img class="logo" src="http://placeimg.com/30/25/any">--%>
        </a>
        <a href="${pageContext.request.contextPath}/games" class="item"><i class="home icon"></i>Home</a>
        <a href="${pageContext.request.contextPath}/games" class="item"> Game</a>
        <div class="ui simple dropdown item">
            Dropdown <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item" href="#">Link Item</a>
                <a class="item" href="#">Link Item</a>
                <div class="divider"></div>
                <div class="header">Header Item</div>
                <div class="item">
                    <i class="dropdown icon"></i>
                    Sub Menu
                    <div class="menu">
                        <a class="item" href="#">Link Item</a>
                        <a class="item" href="#">Link Item</a>
                    </div>
                </div>
                <a class="item" href="#">Link Item</a>
            </div>
        </div>
        <div class="right menu">

            <div class="item">
                <div class="ui inverted transparent icon input">
                    <input type="text" placeholder="Search...">
                    <i class="search icon"></i>
                </div>
            </div>

            <c:if test="${user != null}">
                <div id="noPadding" class="ui dropdown item">
                    <div class="header" >
                        <img class="ui avatar image" src="${user.avatar}">
                            ${user.userName}
                    </div>
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <a href="${pageContext.request.contextPath}/users/profile" class="item">
                            Profile
                        </a>

                        <c:if test="${user.authorities[0].authority=='ROLE_ADMIN'}">
                            <a href="${pageContext.request.contextPath}/games/add" class="item">
                                Add Game
                            </a>
                        </c:if>
                        <div class="item">

                            <form:form action="${pageContext.request.contextPath}/success-logout"  method="POST">
                                <button class="fluid ui button" type="submit" >Logout</button>
                            </form:form>
                        </div>
                    </div>
                </div>

                <div onclick="fetchAllUsers()" id="friendHolder" class="ui dropdown item " >
                    <i class="ui users icon"></i>
                    <span>Friends</span>
                    <div id="friendList" class="ui  middle aligned animated list menu">
                        <div class="item">
                            heelo
                        </div>

                    </div>
                </div>
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
                    </a>
                </div>
            </c:if>

        </div>
</div>



<div id="chat">
    <div id="boxChat_admin" class="box-chat"  style="right: 5%" hidden>

        <div class="ui black inverted segment box-chat-header" style="padding: 10px">

            <div class="box-chat-exit-btn " onclick="closeChatBox('admin')">
                <i class="large close icon"></i>
            </div>

            <div class="ui horizontal list">
                <div class="item">
                    <img class="ui avatar image" src="/uploads/img/cat.jpg">
                    <div class="content">
                        <div class="ui small header" style="color:white">Chat with admin</div>
                    </div>
                </div>
            </div>


        </div>

        <div class="ui segment box-chat-content" style="padding: 10px">

            <div class="box-chat-response">
                <div class="ui comments">
                    <div class="comment">
                        <div class="content">
                            <a class="ui image avatar">
                                <img src="/uploads/img/cat.jpg">
                            </a>
                            <div class="metadata">
                                <span class="date">2 days ago</span>
                            </div>
                            <div class="text" style="background-color: teal;border-radius: 25px;padding: 8px">
                                The CSS border-radius property defines the radius of an element's corners.
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">

                    </div>
                    <div class="twelve wide column">
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">1 days ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px">
                                        Elements are then positioned using the top, bottom, left, and right properties.
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="box-chat-response">
                <div class="ui comments">
                    <div class="comment">
                        <div class="content">
                            <a class="ui image avatar">
                                <img src="/uploads/img/cat.jpg">
                            </a>
                            <div class="metadata">
                                <span class="date">2 days ago</span>
                            </div>
                            <div class="text" style="background-color: teal;border-radius: 25px;padding: 8px">
                                The CSS border-radius property defines the radius of an element's corners.
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">

                    </div>
                    <div class="twelve wide column">
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">1 days ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px">
                                        Elements are then positioned using the top, bottom, left, and right properties.
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="box-chat-response">
                <div class="ui comments">
                    <div class="comment">
                        <div class="content">
                            <a class="ui image avatar">
                                <img src="/uploads/img/cat.jpg">
                            </a>
                            <div class="metadata">
                                <span class="date">2 days ago</span>
                            </div>
                            <div class="text" style="background-color: teal;border-radius: 25px;padding: 8px">
                                The CSS border-radius property defines the radius of an element's corners.
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="box-chat-send">
                <div class="ui grid">
                    <div class="four wide column">
                    </div>
                    <div class="twelve wide column">
                        <div class="ui comments">
                            <div class="comment">
                                <div class="content">
                                    <div class="ui grid">
                                        <div class="eight wide column">
                                        </div>
                                        <div class="eight wide column">
                                            <div class="metadata">
                                                <span class="date">1 days ago</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text" style="background-color: deepskyblue;border-radius: 25px;padding: 8px">
                                        Elements are then positioned using the top, bottom, left, and right properties.
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

        <div  class="ui inverted segment box-chat-bottom" style="padding: 10px">
            <div class="ui search">
                <div class="ui icon send">
                    <input class="prompt" type="text" placeholder="text..." style="height: 40px;padding: 10px;">
                    <i class="large send icon"></i>
                </div>
            </div>
        </div>

    </div>
</div>



<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>

<script src="/js/myChat.js"></script>
<script>


    $('.ui.sticky')
        .sticky({
            context: 'html'
        })
    ;


    function showLogin(){
        $('.ui.tiny.modal').modal('show')
    }

    $('.ui.dropdown').dropdown()

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>