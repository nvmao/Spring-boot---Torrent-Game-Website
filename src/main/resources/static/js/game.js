
let CONTEXT_PATH = $('#contextPathHolder').attr('data-contextPath');
let request = new XMLHttpRequest();
let gameContainer = document.getElementById('gameContainer')


let currentPage
let currentSort
let currentOrder
let currentGenre
let currentDisplay




init()

function init(){
    let curl = new URL(window.location.href);
    let searchParams = new URLSearchParams(curl.search);

    currentPage = (searchParams.get('page') == null) ? 1: searchParams.get('page')
    currentSort = (searchParams.get('sort') == null) ? 'love': searchParams.get('sort')
    currentOrder = (searchParams.get('order') == null) ? 1: searchParams.get('order')
    currentGenre = (searchParams.get('genre') == null) ? 'all': searchParams.get('genre')
    currentDisplay = (searchParams.get('order') == null) ? 0: searchParams.get('display')

    loadGame(currentPage,currentSort,currentOrder,currentGenre,currentDisplay)

    addEventPageButton()
}

function addEventPageButton() {
    let pageButtons = document.querySelectorAll('.pageButton')
    pageButtons.forEach(button=>{
        button.addEventListener("click",function () {

            button.classList.add("loading")


            setTimeout(function() {
                currentPage = (parseInt(button.textContent))

                selectActivePage()

                button.classList.remove("loading")
                reloadHtmlGame()

            },2000) // simulate loading

        })
    })
    selectActivePage()

}

function loadGame(page,sort,order,genre,display) {
    request.open("GET",`${CONTEXT_PATH}/api/games?page=${page}&sort=${sort}&order=${order}&genre=${genre}&display=${display}`)
    request.onload = function () {
        let games = JSON.parse(request.responseText);

        renderHtml(games)
    }
    request.send()


}

function reloadHtmlGame() {
    gameContainer.innerHTML=''
    loadGame(currentPage,currentSort,currentOrder,currentGenre,currentDisplay)
    window.scrollTo(0, 0);
    window.history.pushState({}, null,
        `/games?page=${currentPage}&sort=${currentSort}&order=${currentOrder}&genre=${currentGenre}&display=${currentDisplay}`);
}

function renderHtml(games) {

    console.log("games: "+games.length)
    document.getElementById("button-page-holder").innerHTML=''
    let pageCount = parseInt( games.length/28) +1
    for(let i = 0; i< pageCount;i++){
        let pagebtnHtml = `<a  class="ui tiny secondary inverted circular button pageButton">
                                ${i+1}
                            </a>`

        document.getElementById("button-page-holder").insertAdjacentHTML("beforeend",pagebtnHtml)

    }
    addEventPageButton()


    if(currentDisplay == 0){
        games.forEach(game => {

            let gernesHtml = ''
            for(let i = 0; i < game.gernes.length;i++){
                if(i == game.gernes.length -1){
                    gernesHtml += '#'+game.gernes[i].name
                }
                else{
                    gernesHtml += '#'+game.gernes[i].name +', '

                }
            }

            let htmlString = `<div class="eight wide tablet four wide computer column gameBox">
                        <div class="ui card gameBoxCard" >
                            <div class="ui slide left masked reveal image">
                                <img src="${game.posterPhoto}" class="visible content posterImage">
                                <img src="${game.hoverPhoto}" class="hidden content">
                            </div>
                            <div class="content">
                                <a href="${CONTEXT_PATH}/games/${game.id}" class="ui small header">${game.name}</a>
                                <div class="meta">
                                    <span class="date">${game.publisher.name}</span>
                                </div>
                                <div class="meta">
                                    <span class="date" >
                                        ${gernesHtml}
                                    </span>
                                </div>
                            </div>
                            <div class="extra content">
                            <span class="left floated like">
                                <a class="ui mini circular label" id="like_${game.id}" onclick="loveGame('${game.id}','${game.loveCount}')" >
                                    <i class="like icon" ></i>${game.loveCount}
                                </a>
                                <a class="ui mini blue circular label">
                                    <i class="comment icon"></i>${game.commentCount}
                                </a>
                            </span>
                                <span class="right floated star">
                               <a class="ui mini grey circular label">
                                    <i class="download icon"></i>${game.downloadCount}
                                </a>
                            </span>
                            </div>
                        </div>
                    </div>`
            gameContainer.insertAdjacentHTML('beforeend',htmlString)
            isLoveGame(game.id,game.loveCount)
        })
        dynamicBackgroundCard()
    }
    if(currentDisplay == 1){
        games.forEach(game => {
            let htmlString = `
                        <div class="sixteen wide column gameBox">
                            <div class="ui fluid card gameBoxCard" style="background: transparent">
                          
                            <div class="content">
                                <div class="left floated"> 
                                    <a class="ui header" href="${CONTEXT_PATH}/games/${game.id}"> ${game.name}</a>
                                </div>
                                <div class="right floated meta"> ${game.publisher.name}</div>
                            </div>  
                            
                          <div class="content">
                                        <div class="ui slide masked reveal image" style="max-width: 20%;min-width: 200px;float: left;margin-right: 15px" >
                                            <img src="${game.posterPhoto}" class="visible content posterImage"  >
                                            <img src="${game.hoverPhoto}" class="hidden content"  >
                                        </div>
                                    <div style="text-align: left;padding-right: 2%;color: #111111">${game.description.substring(0,800)}...</div>
                          </div>
                          <div class="extra content">
                            <div class="left floated">
                                <span class="left floated like">
                                <a class="ui mini circular label" id="like_${game.id}" onclick="loveGame('${game.id}','${game.loveCount}')" >
                                    <i class="like icon" ></i>${game.loveCount}
                                </a>
                                <a class="ui mini blue circular label">
                                    <i class="comment icon"></i>${game.commentCount}
                                </a>
                                <a class="ui mini grey circular label" >
                                    <i class="download icon"></i>${game.downloadCount}
                                </a>
                            </span>
                            </div>
                            <div class="right floated">
                                <a class="ui basic black small button" href="${CONTEXT_PATH}/games/${game.id}" >download</a>
                            </div>
                          </div>
                        </div >
                        </div>
    `
            gameContainer.insertAdjacentHTML('beforeend',htmlString)
            isLoveGame(game.id,game.loveCount)
        })
        dynamicBackgroundCardList()
    }


}

function dynamicBackgroundCard() {

    let gameBoxCard = document.querySelectorAll('.gameBoxCard')

    gameBoxCard.forEach(gameCard=>{

        let posterImage = gameCard.querySelector('.posterImage')

        let fac = new FastAverageColor();

        fac.getColorAsync(posterImage.src)
            .then(function(color) {

                gameCard.setAttribute("style",
                    `background: linear-gradient(45deg, #222222,${color.hex});`)

            }).catch(function(e) {
            console.log(e);
        });
    })
}

function dynamicBackgroundCardList() {

    let gameBoxCard = document.querySelectorAll('.gameBoxCard')

    gameBoxCard.forEach(gameCard=>{

        let posterImage = gameCard.querySelector('.posterImage')

        let fac = new FastAverageColor();

        fac.getColorAsync(posterImage.src)
            .then(function(color) {

                gameCard.setAttribute("style",
                    `background: linear-gradient(45deg, #222222,${color.hex})`)

            }).catch(function(e) {
            console.log(e);
        });
    })
}

function selectActivePage() {
    let pageButtons = document.querySelectorAll('.pageButton')
    pageButtons.forEach(button=>{
        button.classList.remove("active")
        if(parseInt(button.textContent) == currentPage){
            button.classList.add("active")
        }
    })
}

function loveGame(gameId,loveCount) {
    $.get(url+`/api/games/${gameId}/love`,function (res) {
        isLoveGame(gameId,res)
    })
}

function isLoveGame(gameId,loveCount) {
    $.get(url+`/api/games/${gameId}/is-love`,function (res) {
        let loveLabel = document.getElementById("like_"+gameId)

        if(res === true){
            loveLabel.innerHTML= `<i class="active like icon" ></i>${loveCount}`
        }
        else {
            loveLabel.innerHTML= `<i class="like icon" ></i>${loveCount}`
        }
    })
}

