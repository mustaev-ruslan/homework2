function restGet(url, callback) {
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: function(result) {
            callback(result)
        },
    });
}

function restDelete(url, callback) {
    $.ajax({
        url: url,
        type: "DELETE",
        success: function() {
            callback()
        },
    });
}

function restPost(url, data, callback) {
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function() {
            callback()
        },
    });
}

function restPut(url, data, callback) {
    $.ajax({
        url: url,
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function() {
            callback()
        },
    });
}