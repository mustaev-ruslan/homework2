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
            </table>
            
            <button onclick="booksPage()">К списку книг</button>

            `);
}