
let sortMenu = document.querySelectorAll(".sort-menu")
let orderButton = document.querySelector(".order-button")
let gridButton = document.querySelector(".grid-button")
let listButton = document.querySelector(".list-button")

let sortType = ["new","comment","download","love"]

initSort()

function initSort() {

    loadOrder()

    selectDisplay()

    orderButton.addEventListener("click",()=>{
        selectOrder()
    })



    for(let i = 0 ; i < sortMenu.length;i++){
        let item = sortMenu[i]
        item.addEventListener("click",()=>{
            selectItem(item)
            currentSort = sortType[i]
            reloadHtmlGame()
        })


        if(sortType[i] == currentSort){
            selectItem(item)
        }

    }

}

function selectOrder() {
    currentOrder *= -1;

    loadOrder()
}

function selectDisplay() {
    loadDisplay()

    gridButton.addEventListener("click",()=>{
        currentDisplay = 0
        loadDisplay()
        reloadHtmlGame()
    })
    listButton.addEventListener("click",()=>{
        currentDisplay = 1
        loadDisplay()
        reloadHtmlGame()
    })

}

function loadDisplay() {
    if(currentDisplay == 0){
        gridButton.setAttribute("style","margin-top: 15px;")
        listButton.setAttribute("style","margin-top: 15px;box-shadow: 0px 0px 0px 1px #999999 inset !important;color: #999999 !important;")
    }
    if(currentDisplay == 1){
        listButton.setAttribute("style","margin-top: 15px;")
        gridButton.setAttribute("style","margin-top: 15px;box-shadow: 0px 0px 0px 1px #999999 inset !important;color: #999999 !important;")
    }
}

function loadOrder() {
    if(currentOrder == 1){
        orderButton.classList.add("olive")
        orderButton.classList.remove("orange")
        orderButton.innerHTML = '<i class="sort amount down icon"></i>'
    }
    else if(currentOrder == -1){
        orderButton.classList.remove("olive")
        orderButton.classList.add("orange")
        orderButton.innerHTML = '<i class="sort amount up icon"></i>'
    }
    reloadHtmlGame()
}

function selectItem(selectedItem) {
    sortMenu.forEach(item=>{
        if(item===selectedItem){
            item.classList.add("active")
        }
        else{
            item.classList.remove("active")
        }
    })
}