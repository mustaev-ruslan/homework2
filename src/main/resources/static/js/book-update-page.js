function renderBookUpdatePage(book) {
    let bookDto = bookToBookDto(book);
    $('#page').empty();
    $('#page').append(`
                    <form id="update-book-form">
                                        
                        <div class="row">
                        <label for="id">Id:</label>
                        <input id="id" name="id" type="text" readonly="readonly"" value="${bookDto.id}"/>
                        </div>
                    
                        <div class="row">
                            <label for="name">Name:</label>
                            <input id="name" name="name" type="text" value="${bookDto.name}"/>
                        </div>
                    
                        <div class="row">
                            <label for="authors">Authors:</label>
                            <input id="authors" name="authorsListString" type="text" value="${bookDto.authorsListString}"/>
                        </div>
                    
                        <div class="row">
                            <label for="genres">Genres:</label>
                            <input id="genres" name="genresListString" type="text" value="${bookDto.genresListString}"/>
                        </div>
                    
                    </form>
                    
                    <button id="book-update-button">Обновить</button>
                    <button onclick="booksPage()">К списку книг</button>

            `);

    $('#book-update-button').click(function () {
        bookUpdate(objectifyForm($('#update-book-form').serializeArray()))
    })
}

function bookToBookDto(book) {
    let bookDto = {};
    bookDto.id = book.id;
    bookDto.name = book.name;
    bookDto.authorsListString = book.authors.map(author => author.name).join(",");
    bookDto.genresListString = book.genres.map(genre => genre.name).join(",");
    return bookDto;
}