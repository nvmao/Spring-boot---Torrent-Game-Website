





$('.ui.form')
    .form({
        fields: {

            name: {
                identifier: 'name',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a name'
                    },

                ]
            },
            downloadLink: {
                identifier: 'downloadLink',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a link'
                    },

                ]
            },
            pPhoto: {
                identifier: 'pPhoto',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please choose an image'
                    },

                ]
            },
            hPhoto: {
                identifier: 'hPhoto',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please choose an image'
                    },

                ]
            },
            publisher_id: {
                identifier: 'publisher_id',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please choose at least a publisher'
                    },

                ]
            },
            genre_tags: {
                identifier: 'genre_tags',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please choose at least a genre tag'
                    },

                ]
            },

        }
    })
;