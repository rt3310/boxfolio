const editor = document.getElementById('editor');
const btnBold = document.getElementById('tool-bold');
const btnItalic = document.getElementById('tool-italic');
const btnUnderline = document.getElementById('tool-under');
const btnStrike = document.getElementById('tool-cancel');
const btnOrderedList = document.getElementById('tool-orderlist');
const btnUnorderedList = document.getElementById('tool-unorderlist');

btnBold.addEventListener('click', function() {
    setStyle('bold');
});

btnItalic.addEventListener('click', function() {
    setStyle('italic');
});

btnUnderline.addEventListener('click', function() {
    setStyle('underline');
});

btnStrike.addEventListener('click', function() {
    setStyle('strikeThrough');
});

btnOrderedList.addEventListener('click', function() {
    setStyle('insertOrderedList');
});

btnUnorderedList.addEventListener('click', function() {
    setStyle('insertUnorderedList');
});

function setStyle(style) {
    document.execCommand(style);
    focusEditor();
}

function focusEditor() {
    editor.focus({preventScroll: true});
}

const btnImage = document.getElementById('tool-image');
const imageSelector = document.getElementById('image-select');

btnImage.addEventListener('click', function() {
    imageSelector.click();
});

imageSelector.addEventListener('change', function (e) {
    const files = e.target.files;
    if (!!files) {
        insertImageDate(files[0]);
    }
});

function insertImageDate(file) {
    const reader = new FileReader();
    reader.addEventListener('load', function (e) {
        focusEditor();
        document.execCommand('insertImage', false, `${reader.result}`);
    });
    reader.readAsDataURL(file);
}