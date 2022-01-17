'use strict';

const baseUrl = 'http://localhost:9889';

window.addEventListener('load', function () {
    const registrationForm = document.getElementById('registration-form');

    registrationForm.addEventListener('submit', onRegisterHandler);
    function onRegisterHandler(e) {
        e.preventDefault();
        const form = e.target;
        const data = new FormData(form);
        createUser(data);
    }

    async function createUser(userFormData) {
        const userJSON = JSON.stringify(Object.fromEntries(userFormData))
        const settings = {
            method: 'POST',
            cache: 'no-cache',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            },
            body: userJSON
        }

        const response = await fetch(baseUrl + '/registration', settings).then(res => res.json());
        console.log(response.id);
        window.location.href = baseUrl + '/index';
    }
})




