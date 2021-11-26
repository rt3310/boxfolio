const addPofolBtn = document.getElementById("add-pofol-btn");

addPofolBtn.addEventListener('click', function() {
    addColumn();
});

let columnCount = 0;

function addColumn() {
    let newColumn = document.createElement("div");
    newColumn.className = "pofol-board";

    setColumnContent(newColumn);
    let container = document.getElementById('pofol-container');
    container.appendChild(newColumn);
    columnCount += 1;

    newColumn.focus({preventScroll: true});
}

function setColumnContent(column) {
    let imageBtn = document.createElement("button");
    let textBtn = document.createElement("button");
    
    imageBtn.className = "add-image-btn";
    imageBtn.innerHTML = '<i class="far fa-image"></i>';

    imageBtn.addEventListener('click', function() {
        imageBtn.style.display = "none";
        textBtn.style.display = "none";
        setColumnContent(column);
    });

    textBtn.className = "add-text-btn";
    textBtn.innerHTML = '<i class="far fa-comment"></i>'

    column.appendChild(imageBtn);
    column.appendChild(textBtn);
}

function setColumnImage(column) {
    let imageDiv = document.createElement("div");
    let imageInput = document.createElement("input");
    imageDiv.className = "image-container";

    imageInput.className = "image-input";
    imageInput.type = "file";
    imageInput.accept = "image/*";
    imageInput.onchange = "loadFile(this)";
    
    imageDiv.appendChild(imageInput);
    column.appendChild(imageDiv);
}

function loadFile(input) {
    let file = input.files[0];

    let newImage = document.createElement("img");
    newImage.setAttribute("class", 'image');

    newImage.src = URL.createObjectURL(file);

    newImage.style.width = "auto";
    newImage.style.height = "100%";
    newImage.style.objectFit = "contain";

    let container = document.getElementsByClassName('pofol-board')[columnCount];
    container.appendChild(newImage);
    focusEditor();
}