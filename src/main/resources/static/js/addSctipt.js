document.addEventListener("DOMContentLoaded", function () {
    const technologyList = []; // Хранение технологий
    const newTechnologyInput = document.getElementById("newTechnology");
    const addBtn = document.getElementById("addTechnologyBtn");
    const technologyTagsContainer = document.getElementById("technologyTagsContainer");
    const technologiesInputs = document.getElementById("technologiesInputs");

    function updateHiddenInputs() {
        technologiesInputs.innerHTML = ""; // Очистка предыдущих значений

        technologyList.forEach(tech => {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "technology"; // Это имя должно совпадать с параметром контроллера
            input.value = tech;
            technologiesInputs.appendChild(input);
        });
    }

    function renderTechnologyTags() {
        technologyTagsContainer.innerHTML = "";

        technologyList.forEach((tech, index) => {
            const tag = document.createElement("div");
            tag.className = "technology-tag";
            tag.textContent = tech;

            const removeBtn = document.createElement("span");
            removeBtn.classList.add("remove-btn");
            removeBtn.textContent = "×";
            removeBtn.onclick = () => {
                technologyList.splice(index, 1);
                updateHiddenInputs();
                renderTechnologyTags();
            };

            tag.appendChild(removeBtn);
            technologyTagsContainer.appendChild(tag);
        });
    }

    addBtn.addEventListener("click", function () {
        const value = newTechnologyInput.value.trim();

        if (value && !technologyList.includes(value)) {
            if (technologyList.length >= 5) {
                alert("Можно добавить максимум 5 технологий.");
                return;
            }

            technologyList.push(value);
            newTechnologyInput.value = "";
            updateHiddenInputs();
            renderTechnologyTags();
        }
    });
});