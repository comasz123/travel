
    function update(name) {
    var select = document.getElementById('country');
    var option = select.options[select.selectedIndex];

    document.getElementById('value').value = option.value;
    document.getElementById('text').value = name;

}

