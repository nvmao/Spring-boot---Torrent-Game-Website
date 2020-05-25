


$('.ui.search')
    .search({
        apiSettings: {
            url:  `${url}/api/games?q={query}`
        },
        fields: {
            results : 'items',
            title   : 'name',
            description: 'downloadCount',
            url     : 'url'
        },
        minCharacters : 1
    })
;