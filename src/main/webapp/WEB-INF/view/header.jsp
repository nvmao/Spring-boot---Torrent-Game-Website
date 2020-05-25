<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="ui grid">

    <div class="computer only row">
        <div class="column">
            <div class="ui fixed  borderless big stackable  inverted menu" style="padding-top: 5px;opacity: 95%">
<%--                <div class="header item" onclick="showMenu()"><i class="ui large bars icon"></i></div>--%>
                <%--            <img class="logo" src="http://placeimg.com/30/25/any">--%>

                <div class="ui container">
                    <a href="${pageContext.request.contextPath}/" class="item"><i class="home icon"></i>Home</a>
                    <a href="${pageContext.request.contextPath}/games" class="item"> Game</a>
                    <div class="item">
                        <i class="search icon"></i>

                        <div class="ui search inverted transparent icon input">

                            <input class="prompt" type="text" placeholder="Search...">
                        </div>
                    </div>

                    <div class="right menu">



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
                            </a>

                            <a  class="item " onclick="fetchNotification('1','0')" id="notificationHolder" >
                                <i class="ui bell icon"></i>
                                <div class="floating mini ui teal label bell-count"  style="top:12%;left:80%"></div>
                            </a>


                            <div id="notification-popup" class="ui popup bottom hidden">
                                    <%--                    <h4 class="ui tiny blue header">Everybody is friend</h4>--%>
                                <div class="emoji-container" style="width: 200px" id="notification-list">

                                </div>
                            </div>

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
                                </d>
                            </div>
                        </c:if>

                    </div>
                </div>




                <a href="${pageContext.request.contextPath}/" class="logo">
                    <img class="ui small image" src="/img/icon.png">
                </a>
                
            </div>
        </div>
    </div>

    <div class="tablet mobile only row">
        <div class="column">
            <div class="ui fixed  borderless big stackable  inverted menu" style="padding-top: 6px;">
                <a id="mobile_item" class="item"><i class="ui large bars icon" onclick="toggleSidebar()"></i></a>
<%--                <a href="${pageContext.servletContext.contextPath}/" class="logo">--%>
<%--                    <img class="ui small image" src="/img/icon.png">--%>
<%--                </a>--%>
            </div>

        </div>
    </div>

</div>


<%--<iframe--%>
<%--        allow="microphone;"--%>
<%--        width="350"--%>
<%--        height="430"--%>
<%--        src="https://console.dialogflow.com/api-client/demo/embedded/d3af6036-9043-4e71-ba5b-5e6018d80a0e">--%>
<%--</iframe>--%>


<div id="chat">

    <form:form enctype="multipart/form-data" method="post" name="form-info" id="form-info"  >

    </form:form>


</div>





<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>

<script src="/js/myChat.js"></script>
<script src="/js/notification.js"></script>
<script src="/js/search.js"></script>


<script>



    $('.ui.sidebar').sidebar({
        context: $('.ui.pushable.segment'),
        transition: 'overlay'
    }).sidebar('attach events', '#mobile_item');

    $('#friendHolder')
        .popup({
            popup : $('#friend-popup'),
            on    : 'click'
        })
    $('#notificationHolder')
        .popup({
            popup : $('#notification-popup'),
            on    : 'click'
        })



    $('.ui.sticky')
        .sticky({
            context: 'html'
        })
    ;

    function showMenu() {
        let menu = document.getElementById("hidden-menu")
        if(menu.hidden){
            menu.hidden=false
        }
        else{
            menu.hidden=true
        }
    }

    function showLogin(){
        $('.ui.tiny.modal').modal('show')
    }

    $('.ui.dropdown').dropdown()

    function toggleSidebar() {
        $('.ui.sidebar').sidebar('toggle')
            $('#friendHolder2')
                .popup({
                    popup : $('#friend-popup2'),
                    on    : 'click'
                })
        $('#friendHolder')
            .popup({
                popup : $('#friend-popup'),
                on    : 'click'
            })

        $('#notificationHolder')
            .popup({
                popup : $('#notification-popup'),
                on    : 'click'
            })
        $('#notificationHolder2')
            .popup({
                popup : $('#notification-popup2'),
                on    : 'click'
            })


            let body = document.querySelector("body")
            let mobile_menu = document.querySelector("#mobile-menu")

            if(!mobile_menu.classList.contains("visible")){
                body.classList.add("pushable")
                mobile_menu.setAttribute("style","visibility: visible;")
            }

            body.addEventListener("click",()=>{
                setTimeout(()=>{
                    if(!mobile_menu.classList.contains("visible")){
                        body.className=''
                        mobile_menu.setAttribute("style","visibility: hidden;")
                    }
                },400)

            })
    }

</script>