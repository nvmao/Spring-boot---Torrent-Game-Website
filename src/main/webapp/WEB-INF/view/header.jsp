<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<div class="ui fixed  borderless big  inverted menu">
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

                <a onclick="fetchAllUsers()" id="friendHolder" class="item " >
                    <i class="ui users icon"></i>
                    <span>Friends</span>


<%--                    <div id="friendList" class="ui  middle aligned animated list menu">--%>
                </a>

                <div id="friend-popup" class="ui popup bottom hidden">
<%--                    <h4 class="ui tiny blue header">Everybody is friend</h4>--%>
                    <div class="emoji-container" style="width: 200px" id="friendList">


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

    <form:form enctype="multipart/form-data" method="post" name="form-info" id="form-info"  >

    </form:form>


</div>




<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>

<script src="/js/myChat.js"></script>
<script>

    $('#friendHolder')
        .popup({
            popup : $('#friend-popup'),
            on    : 'click'
        })


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