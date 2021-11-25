import { gatewayUrl } from "./config.js";
import { getParameterByName } from "./util.js";

const ownerId = getParameterByName('owner-id'); // optional

window.addEventListener('load', () => {
    setupOwnerIdInput();

    const form = document.getElementById('newCompanyForm');
    form.addEventListener('submit', event => createCompany(event));
});

function setupOwnerIdInput() {
    const ownerIdInput = document.getElementById('ownerId');
    if (ownerId) {
        ownerIdInput.value = ownerId;
    } else {
        ownerIdInput.removeAttribute('disabled');
    }
}

function createCompany(event) {
    event.preventDefault();
    const request = {
        'name': document.getElementById('name').value,
        'ownerPlayerId': parseInt(document.getElementById('ownerId').value),
        'sharePrice': parseInt(document.getElementById('sharePrice').value),
        'remainingShares': parseInt(document.getElementById('shares').value)
    };

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            displayResult(this.status, request.name)
        }
    };
    xhttp.open("POST", `${gatewayUrl}/api/companies`, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function displayResult(status, name) {
    const display = document.getElementById('resultDisplay');
    display.textContent = (status == 201) ? `Succesfully added ${name}` : `Adding ${name} failed (${status})`;
}
