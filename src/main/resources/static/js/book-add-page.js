function renderBookAddPage() {
    $('#page').empty();
    $('#page').append(`
                    <form id="add-book-form">
                    
                        <div class="row">
                            <label for="name">Name:</label>
                            <input id="name" name="name" type="text"/>
                        </div>
                    
                        <div class="row">
                            <label for="authors">Authors:</label>
                            <input id="authors" name="authorsListString" type="text"/>
                        </div>
                    
                        <div class="row">
                            <label for="genres">Genres:</label>
                            <input id="genres" name="genresListString" type="text"/>
                        </div>
                  
                    </form>
                    
                    <button id="book-add-button">Add</button>
                    <button onclick="booksPage()">К списку книг</button>

            `);

    $('#book-add-button').click(function () {
        bookAdd(objectifyForm($('#add-book-form').serializeArray()));
    })
}