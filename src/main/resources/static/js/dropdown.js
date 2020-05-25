
setTimeout(()=>{
    let select = document.querySelectorAll('.default.text')[1]
    let gameId = document.getElementById("gameId").value

    $.get(url+ '/api/genres/'+gameId,(data)=>{

        data.forEach(g=>{

           $('.ui .fluid ').dropdown('add value',g.name)

            let selectHtml = `<a class="ui label transition visible"
                            data-value="${g.name}" style="display: inline-block !important;">${g.name}<i class="delete icon"></i></a>`
            select.insertAdjacentHTML('beforebegin',selectHtml)
        })
    })

},200)