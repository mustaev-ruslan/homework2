function booksPage() {
    $.get('/book').done(books => renderBooksPage(books))
}

function bookShowPage(bookId) {
    $.get('/book/' + bookId).done(book => renderBookShowPage(book));
}