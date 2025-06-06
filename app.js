const selectCountry = document.querySelectorAll("select");
let btn = document.querySelector("button");
let fromText = document.querySelector(".from_text");
let toText = document.querySelector(".to_text");
let textarea = document.querySelectorAll("to_text");

for (let select of selectCountry) {
  for (country in countries) {
    let newOption = document.createElement("option");
    newOption.innerText = countries[country];
    newOption.value = country;

    if (select.name === "from" && country === "en-GB") {
      newOption.selected = "selected";
    } else if (select.name === "to" && country === "te-IN") {
      newOption.selected = "selected";
    }
    select.append(newOption);
  }
}

btn.addEventListener("click", async () => {
  if (fromText.value) {
    let text = fromText.value;
    let translateFrom = selectCountry[0].value;
    let translateTo = selectCountry[1].value;

    let URL = `https://api.mymemory.translated.net/get?q=${text}!&langpair=${translateFrom}|${translateTo}`;

    let response = await fetch(URL);
    let data = await response.json();
    toText.value = data.responseData.translatedText;
    console.log(data.responseData.translatedText);
  }
});
