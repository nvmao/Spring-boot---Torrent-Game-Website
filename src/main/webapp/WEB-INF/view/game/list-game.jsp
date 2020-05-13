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
    <link rel="icon" href="/uploads/img/face.png" >

    <script src="/js/sockjs.js"></script>
    <script src="/js/stomp.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/semantic.js"></script>



    <style>

    </style>

</head>
<body >

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
                            <div class="ui mini yellow secondary  pointing four item menu" style="background: transparent;">
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

    <jsp:include page="../footer.jsp"></jsp:include>
</div>

<script src="/js/game.js"></script>
<script src="/js/sortGame.js"></script>
<script src="/js/fast-avg-color.js"></script>
<script src="/js/listGameDynamicBackground.js"></script>

</body>

</html>