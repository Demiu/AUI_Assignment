import { gatewayUrl } from './config.js';

window.addEventListener('load', () => {
    const form = document.getElementById('newPlayerForm');
    form.addEventListener('submit', event => createPlayer(event));
});

function createPlayer(event) {
    event.preventDefault();
    const request = {
        'name': document.getElementById('name').value,
        'money': parseInt(document.getElementById('money').value)
    };

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            displayResult(this.status, request.name)
        }
    };
    xhttp.open("POST", `${gatewayUrl}/api/players`, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function displayResult(status, name) {
    const display = document.getElementById('resultDisplay');
    display.textContent = (status == 201) ? `Succesfully added ${name}` : `Adding ${name} failed (${status})`;
}
