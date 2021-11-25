import { gatewayUrl } from "./config.js";
import { getParameterByName } from "./util.js";

window.addEventListener('load', () => { 
    fetchAndDisplayPlayer(); 

    const form = document.getElementById('playerForm');
    form.addEventListener('submit', event => updatePlayer(event));
});

function fetchAndDisplayPlayer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayer(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", `${gatewayUrl}/api/players/${getParameterByName('id')}`, true);
    xhttp.send();
}

function displayPlayer(object) {
    for (const [key, value] of Object.entries(object)) {
        let input = document.getElementById(key);
        if (input) {
            input.value = value;
        }
    }
}

function updatePlayer(event) {
    event.preventDefault();
    const request = {
        'name': document.getElementById('name').value,
    };

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            fetchAndDisplayPlayer();
            displayRequestResult(this.status);
        }
    };
    xhttp.open("PUT", `${gatewayUrl}/api/players/${getParameterByName('id')}`, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function displayRequestResult(status) {
    const id = getParameterByName('id');
    const display = document.getElementById('resultDisplay');
    display.textContent = (status == 202) 
        ? `Succesfully updated player#${id}` 
        : `Updating player#${id} failed (${status})`;
}
