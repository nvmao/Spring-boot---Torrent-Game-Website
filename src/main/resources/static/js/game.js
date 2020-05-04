
let CONTEXT_PATH = $('#contextPathHolder').attr('data-contextPath');
let request = new XMLHttpRequest();
let gameContainer = document.getElementById('gameContainer')
let pageButtons = document.querySelectorAll('.pageButton')
let currentPage = 1;

init()

function init(){
    loadGame(currentPage)
    selectActivePage()

    pageButtons.forEach(button=>{
        button.addEventListener("click",function () {

            button.classList.add("loading")

            setTimeout(function() {
                currentPage = button.textContent
                selectActivePage()
                gameContainer.innerHTML=''
                loadGame(currentPage)
                location.href = "#gameContainer"
                button.classList.remove("loading")
            },2000) // simulate loading

        })
    })
}

function loadGame(page) {
    request.open("GET",`${CONTEXT_PATH}/api/games?page=${page}`)
    request.onload = function () {
        let games = JSON.parse(request.responseText);
        renderHtml(games)
    }
    request.send()
}

function renderHtml(games) {
    games.forEach(game => {
        let htmlString = `<div class="sixteen wide mobile eight wide tablet four wide computer column">
                        <div class="ui card">
                            <div class="ui slide masked reveal image">
                                <img src="${game.posterPhoto}" class="visible content">
                                <img src="${game.hoverPhoto}" class="hidden content">
                            </div>
                            <div class="content">
                                <a href="${CONTEXT_PATH}/games/${game.id}" class="ui small header">${game.name}</a>
                                <div class="meta">
                                    <span class="date">${game.publisher.name}</span>
                                </div>
                            </div>
                            <div class="extra content">
                            <span class="left floated like">
                                <a class="ui circular label">
                                    <i class="like icon"></i>23
                                </a>
                                <a class="ui blue circular label">
                                    <i class="comment icon"></i>${game.commentCount}
                                </a>
                            </span>
                                <span class="right floated star">
                               <a class="ui grey circular label">
                                    <i class="download icon"></i>100
                                </a>
                            </span>
                            </div>
                        </div>
                    </div>`
        gameContainer.insertAdjacentHTML('beforeend',htmlString)
    })

}

function selectActivePage() {
    pageButtons.forEach(button=>{
        button.classList.remove("active")
        if(button.textContent == currentPage){
            button.classList.add("active")
        }
    })
}
