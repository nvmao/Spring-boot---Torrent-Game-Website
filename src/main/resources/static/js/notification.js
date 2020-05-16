
let stompClientNotif

connectToLoginUser()

function connectToLoginUser() {
    $.get(url+'/socket/loginUser',(response)=>{
        loginUser = response
        connectToNotification(loginUser.userName)
        countUnreadNotification()
        dynamicBackgroundFriendandNotf()
    })
}

function connectToNotification(userName) {
    let socket = new SockJS(url+'/notification')
    stompClientNotif = Stomp.over(socket)
    stompClientNotif.connect({},function (frame) {
        console.log('connected to: '+frame)
        stompClientNotif.subscribe('/topic/notification/' + userName,function (response) {

            let data = JSON.parse(response.body)

            let text = new String(data.content).split(",") ;


            $.uiAlert({
                textHead: 'Notification',
                text: text[1],
                bgcolor: '#19c3aa',
                textcolor: '#fff',
                position: 'top-left', // top And bottom ||  left / center / right
                icon: 'comment',
                link:'/games/'+text[0],
                time: 3
            });

            countUnreadNotification()

        })
    })
}

function sendNotificationSocket(gameId,userId) {
    stompClientChat.send('/app/notification/comment-game/'+gameId,{},userId)
}

function  sendCommentNotification() {

    let gameId = document.getElementById("game-id")
    let userId = document.getElementById("user-id")

    sendNotificationSocket(gameId.value,userId.value)

}


function countUnreadNotification() {

    let bellCount = document.querySelectorAll('.bell-count')

    bellCount.forEach(bell=>{
        $.get(url +`/api/notifications/${loginUser.userName}/countUnread`,(res)=>{

            console.log("res: "+res)

            if(res != 0 ){
                bell.innerText = res
            }
            else{
                bell.innerText=''
            }
        })
    })



}

function fetchNotification(page,loadmore) {

    let notificationList = document.getElementById('notification-list')
    if(loadmore==0){
        notificationList.innerHTML=''
    }
    $.get(url +`/api/notifications/${loginUser.userName}?page=${page}`,(notifications)=>{

        notifications.forEach((notif)=>{
            let notificationHtml = `<a href="${url+'/games/'+notif.content.split(',')[0]}" id="friend_" class="ui grey image horizontal label"
                                    style="display: block;padding: 10px;margin: 0 0 5px 0">
                                    <div class="ui grid">
                                        <div class="sixteen wide column" style="padding-left: 5px !important;margin-left: 0 !important;">
                                                <img src="${notif.fromUser.avatar}" >
                                            ${notif.content.split(',')[1]}
                                        </div>
                                    </div>
                                </a>
                            `

            notificationList.insertAdjacentHTML("beforeend",notificationHtml)

        })

        $.get(url+`/api/notifications/${loginUser.userName}/count`,(res)=> {

            if(parseInt(page) < parseInt((res/20+1))) {

                notificationList.insertAdjacentHTML("beforeend",`<a id="not_${page}" 
                        onclick="loadMoreNotification('${page}')" class="ui tiny secondary blue button">load more</a>`)

            }
        })

    })

}

function loadMoreNotification(page) {
    let loadmoreBtn = document.getElementById("not_"+page)
    loadmoreBtn.classList.add("loading")
    setTimeout(()=>{
        loadmoreBtn.remove()
        fetchNotification(parseInt(page)+1,'1')
    },1000)

}


function dynamicBackgroundFriendandNotf() {

    let friendList = document.querySelector("#friend-popup")
    let notificationList = document.querySelector('#notification-popup')


    let fac = new FastAverageColor();

    fac.getColorAsync(loginUser.avatar)
        .then(function(color) {

            friendList.setAttribute("style",
                `width:200px;padding: 10px;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)
            notificationList.setAttribute("style",
                `width:200px;padding: 10px;background: linear-gradient(145deg, #222222,${color.hex}) !important;`)

        }).catch(function(e) {
    });
}





