context = '/book';

function booksPage() {
    restGet(context, books => renderBooksPage(books));
}

function bookShowPage(bookId) {
    restGet(`${context}/${bookId}`, book => renderBookShowPage(book));
}

function bookDelete(bookId) {
    restDelete(`${context}/${bookId}`, () => booksPage());
}

function bookAddPage() {
    renderBookAddPage();
}

function bookAdd(bookDto) {
    restPost(context, bookDto, () => booksPage());
}

function bookUpdatePage(bookId) {
    restGet(`${context}/${bookId}`, bookDto => renderBookUpdatePage(bookDto));
}

function bookUpdate(bookDto) {
    restPut(context, bookDto, () => booksPage());
}