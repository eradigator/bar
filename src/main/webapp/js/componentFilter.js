function showSelected(typeId) {

    var form = document.forms.addCocktail;
    var options = form.elements.component.options;

    for (var i = 0; i < options.length; i++) {
        options[i].style.display = 'none';
    }

    options = document.getElementsByClassName(typeId);
    for (i = 0; i < options.length; i++) {
        options[0].selected = 'selected';
        options[i].style.display = 'block';
    }
}

showSelected(1);