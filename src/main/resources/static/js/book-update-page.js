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
                    
                    </form>
                    
                    <button onclick="bookUpdate(objectifyForm($('#update-book-form').serializeArray()))"">Обновить</button>
                    <button onclick="booksPage()">К списку книг</button>

            `);
}

function bookToBookDto(book) {
    let bookDto = {};
    bookDto.id = book.id;
    bookDto.name = book.name;
    return bookDto;
}