function getCities(countriesAndCities) {

    let selectCitiesDiv = document.getElementById("select-boxes");
    selectCitiesDiv.removeChild(document.getElementById("city"));

    const selectCountries = document.getElementById("country");
    document.getElementById("city").innerText = selectCountries.selectedIndex;

    let citiesSelect = document.createElement("select");
    citiesSelect.id = "city";
    citiesSelect.name = "cityId";
    selectCitiesDiv.appendChild(citiesSelect);
    let citiesOption = document.getElementById("city");

    if (selectCountries.selectedIndex > 0) {
        let cityNumber = countriesAndCities[selectCountries.selectedIndex - 1].cities.length;
        if (cityNumber > 0) {

            for (let i = 0; i < cityNumber; i++) {
                let optionItem = document.createElement("option");
                optionItem.value = countriesAndCities[selectCountries.selectedIndex - 1].cities[i].id;
                optionItem.text = countriesAndCities[selectCountries.selectedIndex - 1].cities[i].nameEng;
                citiesOption.appendChild(optionItem);
            }

        } else if (cityNumber == 0) {

            let optionItem = document.createElement("option");
            optionItem.value = 0;
            optionItem.text = 'no cities';
            citiesOption.appendChild(optionItem);

        }
    } else {
        let optionItem = document.createElement("option");
        optionItem.value = 0;
        optionItem.text = 'pick city';
        citiesOption.appendChild(optionItem);
    }
}

