
let url = $('#contextPathHolder').attr('data-contextPath');
let stompClient
let gameStart = false
let playerColor
let enemyColor
let playerName
let enemyName

function init(){

    let curl = new URL(window.location.href);
    let searchParams = new URLSearchParams(curl.search);

    playerName = searchParams.get("from")
    enemyName = searchParams.get("to")

    connectToPlayGame(playerName)

    fetchPlayers(playerName,enemyName)

}

function fetchPlayers(playerName,enemyName) {
    let playerDom = document.querySelectorAll(".player-box")[0]

    $.get(url+'/socket/loginUser',(response)=>{

        if(response==''){
            window.location=url+"/login"
        }

        player = response

        let playerHtml = `<img src="${player.avatar}" class="ui avatar image">
                    <span style="font-size: 20px;color:white">${player.userName} (you)</span>
                    <h1 class="ui header center aligned" id="player-score"
                    style="font-size: 60px;margin-top: 0">0</h1>    
                        `

        playerDom.innerHTML = playerHtml

        setTimeout(()=>{
            sendReadySocket(enemyName)
        },2000)
    })

}

function fetchEnemy(enemy) {

    let enemyDom = document.querySelectorAll(".player-box")[1]

    let playerHtml = `<img src="${enemy.avatar}" class="ui avatar image">
                <span style="font-size: 20px;color:white">${enemy.userName} (enemy)</span>
                <h1 class="ui header center aligned" id="enemy-score"
                style="font-size: 60px;margin-top: 0">0</h1>    
                    `

    enemyDom.innerHTML = playerHtml

    gameStart = true
}

function sendReadySocket(enemyName) {
    stompClient.send('/app/playgame2/ready/'+enemyName,{},JSON.stringify({
        user:player,
        message:'ready'
    }))
}


function fetchPlayerScore(score) {
    let playerScoreDome = document.getElementById("player-score")
    playerScoreDome.innerText=`${score}`
}

function fetchEnemyScore(score) {
    let enemyScoreDom = document.getElementById("enemy-score")
    enemyScoreDom.innerText=`${score}`
}


function connectToPlayGame(userName) {
    let socket = new SockJS(url+'/playgame')
    stompClient = Stomp.over(socket)
    stompClient.debug = null

    stompClient.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClient.subscribe('/topic/playgame2/' + userName,function (response) {
            let data = JSON.parse(response.body)
            if(data.message=='ready'){
                enemy = data.user
                fetchEnemy(enemy)
                dynamicBackground()
            }
            else if(data.message=='position'){
                enemyVehicle.getPosFromServer(
                    createVector(data.pos.x,data.pos.y),
                    createVector(data.vel.x,data.vel.y),
                    createVector(data.target.x,data.target.y)
                    )
            }
            else if(data.message=='coin'){

                fetchPlayerScore(data.playerScores[`${playerName}`])
                fetchEnemyScore(data.playerScores[`${enemyName}`])

                if(data.playerScores[`${playerName}`] >= 5){
                    playerVehicle.maxForce = 0.8
                }

                coins = []
                data.coins.forEach(c=>{
                    coins.push(new Coin(createVector(c.position.x,c.position.y),color(244,244,10),20))
                })
            }
            else if(data.message == 'danger'){

                dangers = []
                data.circles.forEach(c=>{
                    dangers.push(new Coin(createVector(c.pos.x,c.pos.y),color(254,12,12),70))
                })
            }
            else if(data.message=='win'){
                document.querySelector('h1').innerText=data.whoWin +' Win The Game'
                gameStart = false
            }

        })
    })
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////game
let playerVehicle
let enemyVehicle
let coins = []
let dangers = []

function setup() {

    init()
    enemyVehicle = new Vehicle(createVector(900,900))
    playerVehicle = new Vehicle(createVector(120,120))


    createCanvas(740, 480);

}



function draw() {
    if(!gameStart){
        return
    }
    background(0,0,25,20)


    if(playerColor != null && enemyColor != null){
        playerVehicle.color = color(playerColor[0],playerColor[1],playerColor[2])
        enemyVehicle.color = color(enemyColor[0],enemyColor[1],enemyColor[2])
    }



    if(mouseIsPressed){
        playerVehicle.seekTo(createVector(mouseX,mouseY))

    }

    playerVehicle.update()
    enemyVehicle.draw()

    for(let i =0;i<coins.length;i++){
        coins[i].draw()
    }
    for(let i =0;i<dangers.length;i++){
        dangers[i].draw()
    }


}





















function dynamicBackground() {
    let enemyDom = document.querySelectorAll(".player-box")[1]
    let playerDom = document.querySelectorAll(".player-box")[0]

    let fac = new FastAverageColor();

    fac.getColorAsync(enemy.avatar)
        .then(function(color) {

            enemyColor = color.value

            enemyDom.setAttribute("style",
                `;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)


        }).catch(function(e) {
    });
    fac.getColorAsync(player.avatar)
        .then(function(color) {

            playerColor = color.value

            playerDom.setAttribute("style",
                `;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)


        }).catch(function(e) {
    });
}