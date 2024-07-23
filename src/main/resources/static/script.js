// добавление документа
function toggleVisibility(id) {
    let element = document.getElementById(id);
    console.log(element);
    if (element.style.display === 'none') {
        element.style.display = 'block';
    } else {
        element.style.display = 'none';
    }
}

function cancelMyForm(idForm) {
    document.getElementById(idForm).reset();
    window.location.href = "/tasks";
}

//Проверка роли
function checkRole(dataempl) {
    const role = dataempl.role;
    console.log(role);

    if (role === 'ROLE_ADM') {
        console.log('проверка роли администратор');
        const header = document.getElementById('headerMenu');
        console.log(header);
        header.innerHTML = `
            <div><a href="/tasks">Список задач</a></div>
            <div><a href="/my-tasks">Мои задачи</a></div>
            <div><a href="/employees">Сотрудники</a></div>
            <div><a href="#">Заказчики</a></div>
            <div><a href="#">Соискатели</a></div>
        `;

        const editEmployee = document.getElementById('editBtn');
        editEmployee.innerHTML = `
            <div id="editBtn">
                <button id="createTask" onclick="toggleVisibility(document.getElementById('editEmployee').id)">
                    Редактировать
                </button>
            </div>
`;

    } else if (role === 'ROLE_MGR') {
        console.log('проверка роли менеджер');
        const header = document.getElementById('headerMenu');
        console.log(header);
        header.innerHTML = `
                    <div><a href="#">Договора</a></div>
                    <div><a href="#">Заказчики</a></div>
                    <div><a href="#">Соискатели</a></div>
                    <div><a href="/tasks">Список задач</a></div>
                    <div><a href="/employees">Сотрудники</a></div>
            `;

    } else if (role === 'ROLE_USR') {
        console.log('проверка роли пользователя');
        const header = document.getElementById('headerMenu');
        console.log(header);
        header.innerHTML = `
                    <div><a href="/tasks">Список задач</a></div>
                    <div><a href="/my-tasks">Мои задачи</a></div>
                    <div><a href="/employees">Сотрудники</a></div>
            `;

    }
}

// Функция для выполнения запроса и получения данных о зарег пользователе
async function fetchDataAuthEmployee() {
    return fetch('http://localhost:8080/api/v1/currents/current-employee-id')
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка HTTP, статус ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // В этом блоке вы получаете данные и можете сделать с ними что-то нужное
            console.log('Данные получены:', data);
            authEmployee = data; // сохранение данных в глобальную переменную
            profileHeader(authEmployee);
            checkRole(authEmployee);
        })
        .catch(error => {
            // Обработка ошибок
            console.error('Произошла ошибка:', error);
        });

}

async function profileHeader(dataempl) {
    const profileHeader = document.getElementById('profileHeader');
    console.log(dataempl.name);
    console.log(dataempl.surname);

    // Очищаем список от старых элементов
    profileHeader.innerHTML = `<h4>${dataempl.name} ${dataempl.surname}</h4>`;

    // Добавляем новые элементы в список
    // const option = document.createElement('option');
    // option.value = item.id; // Предполагается, что каждый элемент имеет поле id
    // option.textContent = `${dataempl.name} ${dataempl.surname}`; // Предполагается, что каждый элемент имеет поле name
    // profileHeader.appendChild(option);

}

fetchDataAuthEmployee();
