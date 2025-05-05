// URL base de la API
const API_URL = 'http://localhost:8080/users';

// Referencias a elementos del DOM
const userForm = document.getElementById('userForm');
const userIdInput = document.getElementById('userId');
const nameInput = document.getElementById('name');
const emailInput = document.getElementById('email');
const submitBtn = document.getElementById('submitBtn');
const cancelBtn = document.getElementById('cancelBtn');
const userTableBody = document.getElementById('userTableBody');
const messageDiv = document.getElementById('message');

// Cargar usuarios al iniciar la página
document.addEventListener('DOMContentLoaded', loadUsers);

// Configurar eventos
userForm.addEventListener('submit', handleFormSubmit);
cancelBtn.addEventListener('click', resetForm);

// Función para mostrar mensajes
function showMessage(text, isError = false) {
    messageDiv.textContent = text;
    messageDiv.className = isError ? 'error' : 'success';
    setTimeout(() => {
        messageDiv.textContent = '';
        messageDiv.className = '';
    }, 3000);
}

// Función para cargar todos los usuarios
async function loadUsers() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Error al cargar usuarios');
        }
        const users = await response.json();
        renderUsers(users);
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para renderizar usuarios en la tabla
function renderUsers(users) {
    userTableBody.innerHTML = '';

    if (users.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="4" style="text-align: center;">No hay usuarios registrados</td>';
        userTableBody.appendChild(row);
        return;
    }

    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td class="actions">
                        <button class="edit" onclick="editUser(${user.id})">Editar</button>
                        <button class="delete" onclick="deleteUser(${user.id})">Eliminar</button>
                    </td>
                `;
        userTableBody.appendChild(row);
    });
}

// Función para manejar el envío del formulario (crear o actualizar)
async function handleFormSubmit(event) {
    event.preventDefault();

    const userId = userIdInput.value;
    const userData = {
        name: nameInput.value,
        email: emailInput.value
    };

    try {
        let response;

        if (userId) {
            // Actualizar usuario existente
            response = await fetch(`${API_URL}/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                throw new Error('Error al actualizar usuario');
            }

            showMessage('Usuario actualizado correctamente');
        } else {
            // Crear nuevo usuario
            response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                throw new Error('Error al crear usuario');
            }

            showMessage('Usuario creado correctamente');
        }

        resetForm();
        loadUsers();
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para preparar la edición de un usuario
async function editUser(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) {
            throw new Error('Error al obtener datos del usuario');
        }

        const user = await response.json();

        // Rellenar el formulario con los datos del usuario
        userIdInput.value = user.id;
        nameInput.value = user.name;
        emailInput.value = user.email;

        // Cambiar el texto del botón y mostrar el botón de cancelar
        submitBtn.textContent = 'Actualizar Usuario';
        cancelBtn.style.display = 'inline-block';

        // Hacer scroll al formulario
        userForm.scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para eliminar un usuario
async function deleteUser(id) {
    if (!confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Error al eliminar usuario');
        }

        showMessage('Usuario eliminado correctamente');
        loadUsers();
    } catch (error) {
        showMessage(error.message, true);
    }
}

// Función para resetear el formulario
function resetForm() {
    userForm.reset();
    userIdInput.value = '';
    submitBtn.textContent = 'Crear Usuario';
    cancelBtn.style.display = 'none';
}