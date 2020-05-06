

let img = $('.parallax').css('background-image');
img = img.replace(/(url\(|\)|")/g, '');

let fac = new FastAverageColor();

fac.getColorAsync(img)
    .then(function(color) {

        console.log('Average color', color);

        let body = document.getElementsByTagName("body")[0]
        body.setAttribute("style",
            `background: linear-gradient(48deg, #333333,${color.hex},${color.hex}, ${color.hex});
                    background-size: 800% 800%;
                    animation: gradient ${30}s ease-in-out infinite`)

    })
    .catch(function(e) {
        console.log(e);
    });