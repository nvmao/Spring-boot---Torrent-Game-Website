<html>

<head>

    <title>Play Game</title>
    <link rel="stylesheet" type="text/css" href="/css/semantic.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="icon" href="/uploads/img/face.png" >
    <link rel="stylesheet" type="text/css" href="/css/Semantic-UI-Alert.css">

    <script src="/js/sockjs.js"></script>
    <script src="/js/stomp.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/semantic.js"></script>

</head>
<body>


<div class="ui container">
    <h1 class="ui  header" style="color: #999999">Playing Color Guessing Game</h1>
    <a class="ui red basic button">Give Up</a>

    <div class="ui section divider hidden"></div>

    <div class="ui segment " style="background: transparent">
        <h2 id="time" class="ui massive header" style="text-align: center;color: white">120</h2>
    </div>

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

    <div class="ui section divider hidden"></div>

    <div class="ui segment center aligned">
        <p class="ui blue header">What color is this <span class="ui teal" id="what-color">RGB(0,0,0)</span> ?</p>
    </div>

    <div class="ui segment center aligned" id="board" hidden>
        <div class="ui three column grid">
            <div class="column" >
                <div class="color-box" style="background: black"></div>
            </div>
            <div class="column" >
                <div class="color-box" style="background: black"></div>
            </div>
            <div class="column" >
                <div class="color-box" style="background: black"></div>
            </div>
        </div>
    </div>


</div>


<link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>

<script src="/js/fast-avg-color.js"></script>
<script src="/js/play-game.js"></script>
</body>

</html>