

let slider = document.querySelector('#slider')

let leftArrow = slider.querySelector('.left')
let rightArrow = slider.querySelector('.right')

let slideContainer = $('#slider').find('.slides')

let slides = slideContainer.find('.slide')

let currentSlide = 0
let width = 720

rightArrow.hidden=true

leftArrow.addEventListener("click",()=>{

    if(currentSlide < slides.length-1){
        currentSlide++;
        slideContainer.animate({'margin-left':'-='+width},800)
        rightArrow.hidden=false
        if(currentSlide === slides.length-1){
            leftArrow.hidden=true
        }
    }

})
  
rightArrow.addEventListener("click",()=>{

    if(currentSlide > 0){
        currentSlide--;
        slideContainer.animate({'margin-left':'+='+width},800)
        leftArrow.hidden=false
        if(currentSlide === 0){
            rightArrow.hidden=true
        }
    }

})

