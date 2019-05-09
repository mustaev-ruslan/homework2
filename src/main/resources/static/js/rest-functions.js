function restGet(url, callback) {
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: callback,
    });
}

function restDelete(url, callback) {
    $.ajax({
        url: url,
        type: "DELETE",
        success: callback,
    });
}

function restPost(url, data, callback) {
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: callback,
    });
}

function restPut(url, data, callback) {
    $.ajax({
        url: url,
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: callback,
    });
}