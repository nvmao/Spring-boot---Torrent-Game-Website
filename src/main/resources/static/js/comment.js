


function reply(commentId) {
    let replyHolder = document.getElementById("reply-holder_"+commentId)
    let replyInput = document.getElementById("reply-input_"+commentId)
    let userId = document.getElementById("user-id")
    let csrf = document.getElementById("reply-form_"+commentId).querySelector(".csrf")

    if(csrf == null){
        csrf = document.getElementById("reply-form_"+commentId).lastChild.querySelector("input")
    }

    if(replyInput.value.trim().length === 0){
        return
    }

    let data={
        content: replyInput.value,
        commentId: commentId,
        userId: userId.value
    }

    $.ajax({
        url: url+`/api/reply/${commentId}`,
        type: 'POST',
        data : JSON.stringify(data),
        headers: {
            'X-CSRF-Token': csrf.value
        },
        contentType : 'application/json',
        success: function (data) {
            sendCommentNotification()
            replyInput.value = ''
            addReply(data,replyHolder)
        },
        error: function (data) {   }
    })

}

function addReply(reply,replyHolder) {

    let replyHtml = ` <div class="comment">
                                                            <a class="avatar">
                                                                <img src="${reply.user.avatar}">
                                                            </a>
                                                            <div class="content">
                                                                <a class="author">${reply.user.userName}</a>
                                                                <div class="metadata">
                                                                    <span class="date">${reply.createdAt}</span>
                                                                </div>
                                                                <div class="text">
                                                                        ${reply.content}
                                                                </div>
                                                            </div>
                                                        </div>`

    replyHolder.insertAdjacentHTML("beforebegin",replyHtml)
}

function comment() {
    let commentHolder = document.getElementById("comment-holder")
    let commentInput = document.getElementById("comment-input")
    let gameId = document.getElementById("game-id")
    let userId = document.getElementById("user-id")
    let csrf = document.getElementById("comment-form").lastChild.querySelector("input")

    if(commentInput.value.trim().length ==0){
        return
    }

    let data={
        content: commentInput.value,
        gameId: gameId.value,
        userId: userId.value
    }

    $.ajax({
        url: url+`/api/comments/${gameId.value}`,
        type: 'POST',
        data : JSON.stringify(data),
        headers: {
            'X-CSRF-Token': csrf.value
        },
        contentType : 'application/json',
        success: function (data) {
            sendCommentNotification()
            commentInput.value = ''
            addComment(data,commentHolder,csrf.value)
        },
        error: function (data) {   }
    })

}

function addComment(comment,commentHolder,csrf) {
    let commentHtml = `<div class="comment">
                                <a class="avatar">
                                    <img src="${comment.user.avatar}">
                                </a>
                                <div class="content" >
                                    <a class="author">${comment.user.userName}</a>
                                    <div class="metadata">
                                        <span class="date">${comment.createdAt}</span>
                                    </div>
                                    <div class="text">
                                            ${comment.content}
                                    </div>
                                    <div class="actions">
                                        <div class="ui accordion">
                                            <div class="title">
                                                <i class="dropdown icon"></i>
                                                    0 reply
                                            </div>
                                            <div class="content">
                                                <div class="comments">
                                                    
                                                     <form:form id="reply-form_${comment.id}" onsubmit="return false" method="POST">
                                                        <div class="field">
                                                            <textarea id="reply-input_${comment.id}" name="replyContent" rows="4" cols="80"></textarea>
                                                        </div>
                                                        <input type="hidden" value="${comment.id}" name="commentId">
                                                        <button onclick="reply('${comment.id}')" class="ui blue labeled submit icon button">
                                                            <i class="icon edit"></i> Add Reply
                                                        </button>
                                                        <div>
                                                            <input class="csrf" value="${csrf}" hidden>
                                                        </div>
                                                    </form:form>

                                                    <h3 class="ui dividing header" id="reply-holder_${comment.id}"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>`


    commentHolder.insertAdjacentHTML("beforebegin",commentHtml)
    $('.ui.accordion')
        .accordion()
    ;
}