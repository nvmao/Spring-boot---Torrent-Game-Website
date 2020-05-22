<html>
<header>

    <title>Play Game</title>
    <link rel="stylesheet" type="text/css" href="/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="icon" href="/uploads/img/face.png" >
    <link rel="stylesheet" type="text/css" href="/css/Semantic-UI-Alert.css">

    <script src="/js/lib/sockjs.js"></script>
    <script src="/js/lib/stomp.js"></script>
    <script src="/js/lib/jquery.js"></script>
    <script src="/js/lib/semantic.js"></script>


</header>
<body>

<div class="ui segment " style="background: transparent">
    <div class="ui two column center grid " >
        <div class="column" >
            <div class="player-box" style="background: green">
            </div>
        </div>
        <div class="column" >
            <div class="player-box" style="background: red"></div>
        </div>

    </div>
</div>

<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>

<script src="/js/lib/p5.min.js"></script>
<script src="/js/lib/fast-avg-color.js"></script>
<script src="/js/playgame/play-steroid-game.js" ></script>
<script src="/js/playgame/Vehicle.js"></script>
</body>

</html>