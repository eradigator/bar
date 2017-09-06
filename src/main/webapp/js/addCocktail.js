function addComponent() {

    var form = document.forms.addCocktail;
    var select = form.elements.component;
    var br = document.createElement('br');
    var amount = document.getElementById("amount");
    var div = document.getElementById("outputPlace");

    for (var i = 0; i < select.options.length; i++) {
        var option = select.options[i];
        if (option.selected) {
            var selectedComponentId = option.value;
            var selectedComponentText = select.options[select.options.selectedIndex].text;
        }
    }

    var selectedComponentAmount = amount.value;

    var output = document.createElement("output");
    output.value = selectedComponentText + ":" + selectedComponentAmount;
    div.appendChild(output);
    div.appendChild(br);

    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", "ingredient");
    input.setAttribute("value", selectedComponentId);
    form.appendChild(input);

    var input1 = document.createElement("input");
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "amountOfIngredient");
    input1.setAttribute("value", selectedComponentAmount);
    form.appendChild(input1);

    select.remove(select.selectedIndex);
    amount.value = "0";
}