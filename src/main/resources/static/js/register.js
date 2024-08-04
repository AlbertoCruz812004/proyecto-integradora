const registerUser = async (request, passwordCorrect) => {
    try {
        const response = await fetch('http://localhost:8080/auth/sign-up', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        });

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error: ', error);
    }
}

document.getElementById('user-creation-form').addEventListener('submit', e => {
    e.preventDefault();
    const email = document.getElementById('in-email').value;
    const pass = document.getElementById('in-pass').value;
    const passConfirm = document.getElementById('in-confirm-pass').value;

    const json = {
        username: email,
        password: pass,
        roleRequest: {
            roleListName: ['ADMIN']
        }
    };

    if (pass === passConfirm) {
        registerUser(json, true)
            .then(data => console.log('Usuario registrado correctamente: ', data))
            .catch(error => console.error('Error al registrar el usuario: ', error));
    } else {
        throw new Error('La contraseña debe ser igual');
    }
});