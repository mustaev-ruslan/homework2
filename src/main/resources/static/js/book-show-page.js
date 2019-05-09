function renderBookShowPage(book) {
    $('#page').empty();
    $('#page').append(`
            <h1>Информация о книге</h1>
            <table>
                <tr>
                    <td>Id</td>
                    <td>${book.id}</td>
                </tr>
                <tr>
                    <td>Название</td>
                    <td>${book.name}</td>
                </tr>
                <tr>
                    <td>Авторы</td>
                    <td>
                        <ul id="authors-list">`);

    book.authors.forEach(author => $('#authors-list').append(`<li>${author.name}</li>`));

    $('#page').append(`
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>Жанры</td>
                    <td>
                        <ul id="genres-list">`);

    book.genres.forEach(genre => $('#genres-list').append(`<li>${genre.name}</li>`));

    $('#page').append(`
                        </ul>
                    </td>
                </tr>
            </table>
            
            <button onclick="booksPage()">К списку книг</button>

            `);
}