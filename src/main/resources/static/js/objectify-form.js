function objectifyForm(formArray) {//serialize data function

    let returnObject = {};
    for (let i = 0; i < formArray.length; i++){
        returnObject[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnObject;
}