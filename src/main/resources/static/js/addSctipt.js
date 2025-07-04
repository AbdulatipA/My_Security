document.addEventListener("DOMContentLoaded", function () {
    const technologyInputs = document.querySelector(".technology-inputs");
    const newTechnologyInput = document.getElementById("newTechnology");
    const addTechnologyBtn = document.getElementById("addTechnologyBtn");

    // Функция для добавления новой технологии
    function addTechnology(technology) {
        if (!technology.trim()) return;

        // Создаем элемент тега
        const tag = document.createElement("div");
        tag.classList.add("technology-tag");
        tag.textContent = technology;

        // Добавляем кнопку для удаления
        const removeBtn = document.createElement("span");
        removeBtn.classList.add("remove-btn");
        removeBtn.textContent = "x";
        tag.appendChild(removeBtn);

        // Обработчик события для удаления технологии
        removeBtn.addEventListener("click", function () {
            technologyInputs.removeChild(tag);
        });

        // Добавляем тег в контейнер
        technologyInputs.appendChild(tag);

        // Очищаем поле ввода
        newTechnologyInput.value = "";
    }

    // Обработчик клика на кнопку "Добавить"
    addTechnologyBtn.addEventListener("click", function () {
        const technology = newTechnologyInput.value.trim();
        addTechnology(technology);
    });

    // Обработчик нажатия Enter в поле ввода
    newTechnologyInput.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            const technology = newTechnologyInput.value.trim();
            addTechnology(technology);
        }
    });
});