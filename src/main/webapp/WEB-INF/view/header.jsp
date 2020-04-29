<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="ui tiny borderless inverted segment">
    <div class="ui inverted secondary pointing menu">

        <a href="${pageContext.request.contextPath}/games" class="active item">
            Home
        </a>
        <a class="item">
            Games
        </a>
        <div class="ui dropdown item">
            Gerne <i class="dropdown icon"></i>
            <div class="menu">
                <a class="item">Action</a>
                <a class="item">Adventure</a>
                <a class="item">Indie</a>
            </div>
        </div>

        <a class="item">
            About
        </a>

        <div class="right menu">
            <div class="item">
                <div class="ui inverted transparent icon input">
                    <input type="text" placeholder="Search...">
                    <i class="search icon"></i>
                </div>
            </div>

            <c:if test="${user != null}">
                <div class="ui dropdown item">
                    <div class="text">
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
                        <form:form action="${pageContext.request.contextPath}/success-logout" class="item" method="POST">
                            <input class="ui  button" type="submit" value="Logout">
                        </form:form>
                    </div>
                </div>
            </c:if>


            <c:if test="${user == null}">
                <a class="item" href="${pageContext.request.contextPath}/users/signup">
                    Sign Up
                </a>
                <a onclick="showLogin()" class="teal item teal active">
                    Login
                </a>
            </c:if>

        </div>

    </div>
</div>

<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
<script>

    function showLogin(){
        $('.ui.tiny.modal').modal('show')
    }

    $('.ui.dropdown').dropdown()

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
    }

</script>