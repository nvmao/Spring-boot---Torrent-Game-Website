
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
                window.scrollTo(0, 0);
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
        let htmlString = `<div class="eight wide tablet four wide computer column gameBox">
                        <div class="ui card gameBoxCard" >
                            <div class="ui slide masked reveal image">
                                <img src="${game.posterPhoto}" class="visible content posterImage">
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

    dynamicBackgroundCard()


    // let boxes =   $('.gameBox')
    // boxes
    //     .transition({
    //
    //         animation : 'jiggle',
    //         duration  : 800,
    //         interval  : 200
    //     })
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

function selectActivePage() {
    pageButtons.forEach(button=>{
        button.classList.remove("active")
        if(button.textContent == currentPage){
            button.classList.add("active")
        }
    })
}
