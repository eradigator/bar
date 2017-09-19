function addComponent() {

    var form = document.forms.calculator;
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

    var ingredientInput = document.createElement("input");
    ingredientInput.setAttribute("type", "hidden");
    ingredientInput.setAttribute("name", "ingredient");
    ingredientInput.setAttribute("value", selectedComponentId);
    form.appendChild(ingredientInput);

    var amountInput = document.createElement("input");
    amountInput.setAttribute("type", "hidden");
    amountInput.setAttribute("name", "amountOfIngredient");
    amountInput.setAttribute("value", selectedComponentAmount);
    form.appendChild(amountInput);

    var ingredientNameInput = document.createElement("input");
    ingredientNameInput.setAttribute("type", "hidden");
    ingredientNameInput.setAttribute("name", "ingredientName");
    ingredientNameInput.setAttribute("value", selectedComponentText);
    form.appendChild(ingredientNameInput);

    select.remove(select.selectedIndex);
    amount.value = "0";
}