

let img = $('.parallax').css('background-image')
img = img.replace(/(url\(|\)|")/g, '')

let fac = new FastAverageColor()

fac.getColorAsync(img)
    .then(function(color) {

        console.log('Average color', color);
        // animation: gradient ${30}s ease-in-out infinite
        let body = document.getElementsByTagName("body")[0]
        body.setAttribute("style",
            `background: linear-gradient(110deg, ${color.hex},#333333, ${color.hex});
                    background-size: 800% 800%;
                    `)

    })
    .catch(function(e) {
        console.log(e);
    });