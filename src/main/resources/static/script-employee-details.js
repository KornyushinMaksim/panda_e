
let employeeData;

console.log(employeeData);


// Функция для выполнения запроса и получения данных о конкретном заказчике
async function fetchDataCustomerById(emplId) {
    return fetch(`http://localhost:8080/api/v1/employees/get-employee-by-id/${emplId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка HTTP, статус ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // В этом блоке вы получаете данные и можете сделать с ними что-то нужное
            console.log('Данные получены:', data.nameOrg);
            employeeData = data.nameOrg; // сохранение данных в глобальную переменную
            console.log(employeeData);
        })
        .catch(error => {
            // Обработка ошибок
            console.error('Произошла ошибка:', error);
        });
}

async function editEmployee() {

    document.getElementById('editEmployeeForm', function (event) {
        event.preventDefault(); // Предотвращаем отправку формы по умолчанию                                    console.log('1234');

        // Собираем данные формы в объект FormData
        const id = document.getElementById('id').value;
        const name = document.getElementById('name').value;
        const surname = document.getElementById('surname').value;
        const employmentDate = document.getElementById('employmentDate').value;
        const jobTitle = document.getElementById('jobTitle').value;
        const department = document.getElementById('department').value;
        const role = document.getElementById('role').value;

        // Данные о задаче, которые нужно отправить на сервер
        const emplData = {
            id: id,
            name: name,
            surname: surname,
            employmentDate: employmentDate,
            jobTitle: jobTitle,
            department, department,
            role: role
        }
        emplData.then(data => {
            console.log(data)
            const urlEmployeData = 'http://localhost:8080/api/v1/employees/update-employee';

            // Формирование POST запроса с использованием Fetch API
            fetch(urlEmployeData, {
                method: 'PUT',
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
            window.location.href = "/employees";

        });

    this.reset();
};

async function getEmployeeById(emplId) {
    fetch(`http://localhost:8080/api/v1/employees/get-employee-by-id/${emplId}`)
        .then(response => response.json())
        .then(employee => {
            console.log(employee);
            employeeData = employee;
            console.log(employeeData);

            const editEmployeeForm = document.getElementById('editEmployeeFormInput');
            editEmployeeForm.innerHTML = `
        <div class="form-group">
            <div>
                <p>ID: </p>${employee.id}    
            </div><br>
            <div>
                <p>Имя: </p>${employee.name} <p>Фамилия: </p>${employee.surname}
            </div><br>
            <div>
               <label for="employmentDate"><p>Дата приема: </p></label>
               <input type="date" id="employmentDate" name="employmentDate" value="${employee.employmentDate}">
            </div><br>
            <div>
                <label for="jobTitle"><p>Должность: </p></label>
                <input type="text" id="jobTitle" name="jobTitle" value="${employee.jobTitle}">
                <label for="department"><p>Отдел: </p></label>
                <input type="text" id="department" name="department" value="${employee.department}">
            </div><br>
            <div>
                <label for="role"><p>Роль: </p></label>
                <input type="text" id="role" name="role" value="${employee.role}">
            </div><br>
            <div>
                <label for="login"><p>Логин: </p></label>
                <input type="email" id="login" name="login" value="${employee.authentication.login}">
                <label for="password"><p>Пароль: </p></label>
                <input type="text" id="password" name="password" value="${employee.authentication.password}">
            </div><br>

        </div>
        `;
        })
        .catch(error => console.error('Ошибка получения данных о задаче:', error));

};

document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const employeeId = urlParams.get('id');

    fetchDataCustomerById(employeeId);
    getEmployeeById(employeeId);


    // Здесь можно выполнить запрос на сервер для получения подробной информации о задаче по её ID
    // Пример запроса
    fetch(`http://localhost:8080/api/v1/employees/get-employee-by-id/${employeeId}`)
        .then(response => response.json())
        .then(employee => {
            console.log(employee);
            employeeData = employee;
            console.log(employeeData);

            const employeeDetailsDiv = document.getElementById('employeeDetails');
            employeeDetailsDiv.innerHTML = `
                <h1> ${employee.name} ${employee.surname}</h1><br><br>
                <td><p>ID:</p> ${employee.id}</td><br><br>
                <td><p>Имя:</p> ${employee.name}</td><br><br>
                <td><p>Фамилия:</p> ${employee.surname}</td><br><br>
                <td><p>Дата приема:</p> ${employee.employmentDate}</td><br><br>
                <td><p>Должность:</p> ${employee.jobTitle}</td><br><br>
                <td><p>Отдел:</p> ${employee.department}</td><br><br>
                <td><p>Роль:</p> ${employee.role}</td><br><br>
            `;
        })
        .catch(error => console.error('Ошибка получения данных о задаче:', error));
});

async function getAllInfoEmployee(emplId) {
    const urlEmployee = `http://localhost:8080/api/v1/employees/get-employee-by-id/${emplId}`;
    const urlAuth = `http://localhost:8080/api/v1/auhts/get-auth-by-employee-id/${emplId}`;

    // Выполняем GET запрос с использованием fetch
    try {
        const responseEmployee = await axios.get(urlEmployee);
        const responseAuth = await axios.get(urlAuth);

        const editEmployeeForm = document.getElementById('editEmployeeFormInput');
        editEmployeeForm.innerHTML = `
            <div class="form-group">
                <div>
                    <p>ID: </p>${responseEmployee.data.id}    
                </div><br>
                <div>
                    <p>Имя: </p>${responseEmployee.data.name} <p>Фамилия: </p>${responseEmployee.data.surname}
                </div><br>
                <div>
                   <label for="employmentDate"><p>Дата приема: </p></label>
                   <input type="date" id="employmentDate" name="employmentDate" value="${responseEmployee.data.employmentDate}">
                </div><br>
                <div>
                    <label for="jobTitle"><p>Должность: </p></label>
                    <input type="text" id="jobTitle" name="jobTitle" value="${responseEmployee.data.jobTitle}">
                    <label for="department"><p>Отдел: </p></label>
                    <input type="text" id="department" name="department" value="${responseEmployee.data.department}">
                </div><br>
                <div>
                    <label for="role"><p>Роль: </p></label>
                    <input type="text" id="role" name="role" value="${responseEmployee.data.role}">
                </div><br>
                <div>
                    <label for="login"><p>Логин: </p></label>
                    <input type="email" id="login" name="login" value="${responseAuth.data.login}">
                    <label for="password"><p>Пароль: </p></label>
                    <input type="text" id="password" name="password" value="${responseAuth.data.password}">
                </div><br>
            </div>
        `;


        // console.log(`globalData ${authEmployee.name} ${authEmployee.surname}`);
        // console.log(customerName);
        // const taskData = {
        //     fileId: {
        //         id: responseFile.data.id,
        //         nameFile: responseFile.data.nameFile,
        //         size: responseFile.data.size,
        //         typeFile: responseFile.data.typeFile,
        //         dateOfChange: responseFile.data.dateOfChange,
        //         pathToStorage: responseFile.data.pathToStorage,
        //         isActive: responseFile.data.isActive,
        //         employee: {

        //         }
        //     },
        //     nameTask: nameTask,
        //     authorTask: authorTask,
        //     customerName: customerName,
        //     executors: [{
        //         id: responseEmployee.data.id,
        //         name: responseEmployee.data.name,
        //         surname: responseEmployee.data.surname,
        //         employmentDate: responseEmployee.data.employmentDate,
        //         jobTitle: responseEmployee.data.jobTitle,
        //         department: responseEmployee.data.department,
        //         authentication: responseEmployee.data.authentication,
        //         humanId: responseEmployee.data.humanId,
        //         role: responseEmployee.data.role
        //     }],
        //     deadLine: deadLine,
        //     numbering: '',
        // };

        // return taskData;
    }
    catch (error) {
        console.error('Ошибка при загрузке данных:', error);
    }
}