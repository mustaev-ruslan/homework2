function renderBooksPage(books) {
    $('#page').empty();
    $('#page').append(`
                <button onclick="bookAddPage()">Add book</button>
                <table>
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody id="books-table">
                    </tbody>
                </table>
            `);
    books.forEach(function (book) {
        $('#books-table').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>
                            <button onclick="bookShowPage(${book.id})">Show book</button>
                            <button onclick="bookUpdatePage(${book.id})">Update book</button>
                            <button onclick="bookDelete(${book.id})">Delete book</button>
                        </td>
                    </tr>
                `)
    });
}