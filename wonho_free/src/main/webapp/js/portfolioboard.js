const addPofolImage = document.getElementById("add-pofol-image");
const addPofolText = document.getElementById("add-pofol-text");
const addPofolCode = document.getElementById("add-pofol-code");
const addPofolVideo = document.getElementById("add-pofol-video");
const rootWrap = document.querySelector(".root-wrap");

const header = document.querySelector("header");
const footer = document.querySelector("footer");
const section = document.getElementById("pofol-section");

let columnCount = 0;

addPofolImage.addEventListener('click', function() {
    const newColumn = document.createElement("div");
    newColumn.className = "pofol-board";

    const inputImage = document.createElement("input");
    inputImage.type = "file";
    inputImage.accept = "image/*";
    inputImage.addEventListener('change', function() {
        let file = inputImage.files[0];

        let newImage = document.createElement("img");
        newImage.setAttribute("class", 'image');

        newImage.src = URL.createObjectURL(file);

        newImage.style.width = "auto";
        newImage.style.height = "50%";
        newImage.style.objectFit = "contain";

        inputImage.style.display = "none";
        newColumn.appendChild(newImage);
    });
    newColumn.appendChild(inputImage);

    const container = document.getElementById('pofol-container');
    container.appendChild(newColumn);
    columnCount += 1;

    rootWrap.style.height += container.style.height;
    newColumn.focus({preventScroll: true});
});

addPofolText.addEventListener('click', function() {
    const newColumn = document.createElement("div");
    newColumn.className = "pofol-board";

    newColumn.contentEditable = "true";

    const container = document.getElementById('pofol-container');
    container.appendChild(newColumn);
    columnCount += 1;

    rootWrap.style.height += container.style.height;
    newColumn.focus({preventScroll: true});
});

addPofolCode.addEventListener('click', function() {
    const newColumn = document.createElement("div");
    newColumn.className = "pofol-board";

    newColumn.contentEditable = "true";

    const container = document.getElementById('pofol-container');
    container.appendChild(newColumn);
    columnCount += 1;

    rootWrap.style.height += container.style.height;
    newColumn.focus({preventScroll: true});
});

addPofolVideo.addEventListener('click', function() {
    const newColumn = document.createElement("div");
    newColumn.className = "pofol-board";

    const inputImage = document.createElement("input");
    inputImage.type = "file";
    inputImage.accept = "avi/*";
    inputImage.addEventListener('change', function() {
        let file = inputImage.files[0];

        let newImage = document.createElement("video");
        newImage.setAttribute("class", 'video');

        newImage.src = URL.createObjectURL(file);

        newImage.style.width = "auto";
        newImage.style.height = "50%";
        newImage.style.objectFit = "contain";

        inputImage.style.display = "none";
        newColumn.appendChild(newImage);
    });
    newColumn.appendChild(inputImage);

    const container = document.getElementById('pofol-container');
    container.appendChild(newColumn);
    columnCount += 1;

    rootWrap.style.height += container.style.height;
    newColumn.focus({preventScroll: true});
});

function loadFile(input) {
    let file = input.files[0];

    let newImage = document.createElement("img");
    newImage.setAttribute("class", 'image');

    newImage.src = URL.createObjectURL(file);

    newImage.style.width = "auto";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    let container = document.getElementById('editor');
    container.appendChild(newImage);
    focusEditor();
}

const saveEdit = document.getElementById('save-edit');

saveEdit.addEventListener('click', function() {
    const urlToSendForBoard = "http://localhost:8080/wonho_free/PortfolioServlet?cmd=uploadPortfolio";

    const title = document.getElementById('pofol-title');
    var content = document.getElementById('pofol-container');
    const titleText = title.value;
    const contentText = content.innerHTML.replace('contenteditable="true"', '');

    const params = {'title':titleText, 'content':contentText};
    sendPost(urlToSendForBoard, params);
});

// 데이터 post 전송
function sendPost(url, params) {
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', url);
    document.characterSet = "utf-8";
    
    for (var key in params) {
        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', params[key]);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();
}