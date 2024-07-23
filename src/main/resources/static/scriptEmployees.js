// Глобальная переменная, в которую будут сохранены данные
let authEmployee;

//Получение списка сотрудников
document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/api/v1/employees/get-all-employees')
        .then(response => response.json())
        .then(data => {
            const employees = data;

            const tableBody = document.getElementById('employeesTableBody');

            employees.forEach(employee => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td><a href="employee-details?id=${employee.id}">${employee.name}</a></td>
                    <td><a href="employee-details?id=${employee.id}">${employee.surname}</a></td>
                    <td><a href="employee-details?id=${employee.id}">${employee.jobTitle}</a></td>
                    <td><a href="employee-details?id=${employee.id}">${employee.department}</a></td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
});

//Загрузка файла на сервер
function uploadFile() {
    alert('Получаем файл из input');
    // Получаем файл из input
    const fileInput = document.getElementById('file');
    alert(fileInput);
    console.log(fileInput);
    const file = fileInput.files[0];
    const employeeId = authEmployee.id;

    if (!file) {
        alert('Выберите файл для загрузки.');
        return;
    }

    // Создаем объект FormData
    const formData = new FormData();
    formData.append('file', file);
    formData.append('employeeId', employeeId);

    // Настройка запроса
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/api/v1/files/upload-file', true);

    // Отправляем данные формы
    xhr.send(formData);

    // Обработка ответа от сервера
    xhr.onload = function () {
        if (xhr.status === 200) {
            alert('Файл успешно загружен на сервер.');
        } else {
            alert('Произошла ошибка при загрузке файла.');
        }
    };

    // Обработка ошибок при отправке запроса
    xhr.onerror = function () {
        alert('Произошла ошибка при отправке запроса на сервер.');
    };
}


// Загрузка списка сотрудников с сервера
async function loadDataEmployees(url, dropdownId) {
    try {
        const response = await axios.get(url);
        const data = response.data;

        // Находим выпадающий список по его id
        const dropdown = document.getElementById(dropdownId);

        // Очищаем список от старых элементов
        dropdown.innerHTML = '<option value="">Выберите...</option>';

        // Добавляем новые элементы в список
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id; // Предполагается, что каждый элемент имеет поле id
            option.textContent = `${item.name} ${item.surname}`; // Предполагается, что каждый элемент имеет поле name
            dropdown.appendChild(option);
        });

    } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
}

// Загрузка списка файлов с сервера
async function loadDataFile(url, dropdownId) {
    try {
        const response = await axios.get(url);
        const data = response.data;

        // Находим выпадающий список по его id
        const dropdown = document.getElementById(dropdownId);

        // Очищаем список от старых элементов
        dropdown.innerHTML = '<option value="">Выберите...</option>';

        // Добавляем новые элементы в список
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id; // Предполагается, что каждый элемент имеет поле id
            option.textContent = item.nameFile; // Предполагается, что каждый элемент имеет поле name
            dropdown.appendChild(option);
        });

    } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
}

// Загрузка списка заказчиков с сервера
async function loadDataCustomer(url, dropdownId) {
    try {
        const response = await axios.get(url);
        const data = response.data;

        // Находим выпадающий список по его id
        const dropdown = document.getElementById(dropdownId);

        // Очищаем список от старых элементов
        dropdown.innerHTML = '<option value="">Выберите...</option>';

        // Добавляем новые элементы в список
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id; // Предполагается, что каждый элемент имеет поле id
            option.textContent = item.nameOrg; // Предполагается, что каждый элемент имеет поле name
            dropdown.appendChild(option);
        });

    } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
}

// Загрузка данных с сервера и заполнение выпадающих списков при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    loadDataEmployees('http://localhost:8080/api/v1/employees/get-all-employees', 'executors');
    loadDataFile('http://localhost:8080/api/v1/files/all-files', 'fileId');
    loadDataCustomer('http://localhost:8080/api/v1/customers/get-all-customers', 'customerId');
});

document.getElementById('taskForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Предотвращаем отправку формы по умолчанию                                    console.log('1234');

    // Собираем данные формы в объект FormData
    const nameTask = document.getElementById('nameTask').value;
    const customerId = document.getElementById('customerId').value;
    const executors = document.getElementById('executors').value;
    const fileId = document.getElementById('fileId').value;
    const deadLine = document.getElementById('deadLine').value;
    fetch('http://localhost:8080/api/v1/currents/current-employee-id')
        .then(response => response.json())
        .then(userDetails => {
            const authorTask = `${authEmployee.name} ${authEmployee.surname}`;
            console.log(userDetails);
            console.log(authorTask);

            // Данные о задаче, которые нужно отправить на сервер
            const taskData = getEmployee(executors, fileId, nameTask, authorTask, customerId, deadLine);
            taskData.then(data => {
                console.log(data)
                const urlTaskData = 'http://localhost:8080/api/v1/tasks/add-task';

                // Формирование POST запроса с использованием Fetch API
                fetch(urlTaskData, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json', // Указываем тип содержимого (JSON)
                    },
                    body: JSON.stringify(data) // Преобразуем объект в JSON строку для отправки
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json(); // Разбираем JSON ответ в JavaScript объект
                    })
                    .then(data => {
                        console.log('Успешный ответ:', data);
                    })
                    .catch(error => {
                        console.error('Ошибка при выполнении запроса:', error);
                    });
            });
        })
        .catch(error => {
            console.error('Ошибка при получении данных о пользователе:', error);
        });

    this.reset();
});

async function getEmployee(exec, fId, nameTask, authorTask, customerId, deadLine) {
    // Формируем URL для запроса сотрудника
    const urlEmployee = `http://localhost:8080/api/v1/employees/get-employee-by-id?id=${exec}`;
    const urlMyFileDB = `http://localhost:8080/api/v1/files/get-file-by-id?id=${fId}`;

    // Выполняем GET запрос с использованием fetch
    try {
        const responseEmployee = await axios.get(urlEmployee);
        const responseFile = await axios.get(urlMyFileDB);

        console.log(`globalData ${authEmployee.name} ${authEmployee.surname}`)
        const taskData = {
            fileId: {
                id: responseFile.data.id,
                nameFile: responseFile.data.nameFile,
                size: responseFile.data.size,
                typeFile: responseFile.data.typeFile,
                dateOfChange: responseFile.data.dateOfChange,
                pathToStorage: responseFile.data.pathToStorage,
            },
            nameTask: nameTask,
            authorTask: authorTask,
            customerId: customerId,
            executors: [{
                id: responseEmployee.data.id,
                name: responseEmployee.data.name,
                surname: responseEmployee.data.surname,
                employmentDate: responseEmployee.data.employmentDate,
                jobTitle: responseEmployee.data.jobTitle,
                department: responseEmployee.data.department,
                authentication: responseEmployee.data.authentication,
                humanId: responseEmployee.data.humanId,
                role: responseEmployee.data.role
            }],
            deadLine: deadLine,
        };

        return taskData;
    }
    catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
};
