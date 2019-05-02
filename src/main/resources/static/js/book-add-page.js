function renderBookAddPage() {
    $('#page').empty();
    $('#page').append(`
                    <form id="add-book-form">
                    
                        <div class="row">
                            <label for="name">Name:</label>
                            <input id="name" name="name" type="text"/>
                        </div>
                   
                  
                    </form>
                    
                    <button onclick="bookAdd(objectifyForm($('#add-book-form').serializeArray()))"">Add</button>
                    <button onclick="booksPage()">К списку книг</button>

            `);
}