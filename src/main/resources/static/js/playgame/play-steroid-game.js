
let url = $('#contextPathHolder').attr('data-contextPath');
let stompClient
let gameStart = false
let playerColor
let enemyColor

function init(){

    let curl = new URL(window.location.href);
    let searchParams = new URLSearchParams(curl.search);

    let playerName = searchParams.get("from")
    let enemyName = searchParams.get("to")

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


function connectToPlayGame(userName) {
    let socket = new SockJS(url+'/playgame')
    stompClient = Stomp.over(socket)
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

        })
    })
}


let playerVehicle
let enemyVehicle

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

    playerVehicle.color = color(playerColor[0],playerColor[1],playerColor[2])
    enemyVehicle.color = color(enemyColor[0],enemyColor[1],enemyColor[2])

    background(0,0,25,20)

    if(mouseIsPressed){
        playerVehicle.seekTo(createVector(mouseX,mouseY))

    }

    playerVehicle.update()
    enemyVehicle.draw()


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