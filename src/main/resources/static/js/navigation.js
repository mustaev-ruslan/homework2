context = '/book';

function booksPage() {
    $.get(context).done(books => renderBooksPage(books))
}

function bookShowPage(bookId) {
    $.get(`${context}/${bookId}`).done(book => renderBookShowPage(book));
}

function bookDelete(bookId) {
    $.delete(`${context}/${bookId}`).done(() => booksPage());
}