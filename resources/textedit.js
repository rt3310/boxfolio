const editor = document.getElementById('editor');
const btnBold = document.getElementById('tool-bold');
const btnItalic = document.getElementById('tool-italic');
const btnUnderline = document.getElementById('tool-under');
const btnStrike = document.getElementById('tool-cancel');
const btnOrderedList = document.getElementById('tool-orderlist');
const btnUnorderedList = document.getElementById('tool-unorderlist');
const btnImage = document.getElementById('tool-image');
const imageSelector = document.getElementById('image-select');
const btnFull = document.getElementById('tool-full');
const btnLeft = document.getElementById('tool-left');
const btnCenter = document.getElementById('tool-center');
const btnRight = document.getElementById('tool-right');
const btnUndo = document.getElementById('tool-undo');
const btnRedo = document.getElementById('tool-redo');

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

btnUndo.addEventListener('click', function() {
    setStyle('undo');
})

btnRedo.addEventListener('click', function() {
    setStyle('redo');
});

btnFull.addEventListener('click', function() {
    setStyle('justifyFull')
})

btnLeft.addEventListener('click', function() {
    setStyle('justifyLeft')
})

btnCenter.addEventListener('click', function() {
    setStyle('justifyCenter')
})

btnRight.addEventListener('click', function() {
    setStyle('justifyRight')
})

btnImage.addEventListener('click', function() {
    imageSelector.click();
});

imageSelector.addEventListener('change', function (e) {
    const files = e.target.files;
    if (!!files) {
        insertImageDate(files[0]);
    }
});

editor.addEventListener('keydown', function() {
    checkStyle();
});

editor.addEventListener('mousedown', function() {
    checkStyle();
});

function insertImageDate(file) {
    const reader = new FileReader();
    reader.addEventListener('load', function (e) {
        focusEditor();
        document.execCommand('insertImage', false, `${reader.result}`);
    });
    reader.readAsDataURL(file);
}

function focusEditor() {
    editor.focus({preventScroll: true});
}

function setStyle(style) {
    document.execCommand(style);
    focusEditor();
    checkStyle();
}

function checkStyle() {
    if (isStyle('bold')) {
        btnBold.classList.add('active');
    } else {
        btnBold.classList.remove('active');
    }
    if (isStyle('italic')) {
        btnItalic.classList.add('active');
    } else {
        btnItalic.classList.remove('active');
    }
    if (isStyle('underline')) {
        btnUnderline.classList.add('active');
    } else {
        btnUnderline.classList.remove('active');
    }
    if (isStyle('strikeThrough')) {
        btnStrike.classList.add('active');
    } else {
        btnStrike.classList.remove('active');
    }
    if (isStyle('insertOrderedList')) {
        btnOrderedList.classList.add('active');
    } else {
        btnOrderedList.classList.remove('active');
    }
    if (isStyle('insertUnorderedList')) {
        btnUnorderedList.classList.add('active');
    } else {
        btnUnorderedList.classList.remove('active');
    }
    if (isStyle('justifyFull')) {
        btnFull.classList.add('active');
    } else {
        btnFull.classList.remove('active');
    }
    if (isStyle('justifyLeft')) {
        btnLeft.classList.add('active');
    } else {
        btnLeft.classList.remove('active');
    }
    if (isStyle('justifyCenter')) {
        btnCenter.classList.add('active');
    } else {
        btnCenter.classList.remove('active');
    }
    if (isStyle('justifyRight')) {
        btnRight.classList.add('active');
    } else {
        btnRight.classList.remove('active');
    }
}

function isStyle(style) {
    return document.queryCommandState(style);
}