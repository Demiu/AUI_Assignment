import { gatewayUrl } from "./config.js";
import { getParameterByName } from "./util.js";

const id = getParameterByName('id'); // required
const ownerId = getParameterByName('owner-id'); // optional

window.addEventListener('load', () => {
    setupOwnerIdInput();

    const form = document.getElementById('companyForm');
    form.addEventListener('submit', event => updateCompany(event));

    fetchAndDisplayCompany(); 
});

function setupOwnerIdInput() {
    const ownerIdInput = document.getElementById('ownerId');
    if (ownerId) {
        ownerIdInput.value = ownerId;
    } else {
        ownerIdInput.removeAttribute('disabled');
    }
}

function fetchAndDisplayCompany() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCompany(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", `${gatewayUrl}/api/companies/${id}`, true);
    xhttp.send();
}

function displayCompany(object) {
    for (const [key, value] of Object.entries(object)) {
        let input = document.getElementById(key);
        if (input) {
            input.value = value;
        }
    }
}

function updateCompany(event) {
    event.preventDefault();
    const request = {
        'name': document.getElementById('name').value,
        'sharePrice': parseInt(document.getElementById('sharePrice').value),
    };

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            fetchAndDisplayCompany();
            displayUpdateResult(this.status);
        }
    };
    xhttp.open("PUT", `${gatewayUrl}/api/companies/${id}`, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function displayUpdateResult(status) {
    const display = document.getElementById('resultDisplay');
    display.textContent = (status == 202) 
        ? `Succesfully updated company#${id}` 
        : `Updating company#${id} failed (${status})`;
}
