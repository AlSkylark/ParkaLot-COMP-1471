function populateDropdowns(selector, options, dataKey) {
    document.querySelectorAll(selector).forEach((select) => {
        const currentValue = select.dataset[dataKey];
        options.forEach((opt) => {
            const option = document.createElement("option");
            option.value = opt.id;
            option.textContent = opt.label;
            if (String(option.value) === String(currentValue)) {
                option.selected = true;
            }
            select.appendChild(option);
        });
    });
}

function handleDiscountInput(filledInput, otherInputId) {
    const otherInput = document.getElementById(otherInputId);
    if (!otherInput) return;

    if (filledInput.value !== "") {
        otherInput.value = "";
        otherInput.disabled = true;
    } else {
        otherInput.disabled = false;
    }
}
