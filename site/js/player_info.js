import { gatewayUrl } from "./config.js";
import { clearElementChildren, createButtonCell, createLinkCell, createTextCell, getParameterByName } from "./util.js";

const id = getParameterByName('id');

window.addEventListener('load', () => { 
    const form = document.getElementById('playerForm');
    form.addEventListener('submit', event => updatePlayer(event));
    const newCompanyLink = document.getElementById('newCompanyLink');
    newCompanyLink.setAttribute('href', `company_create.html?owner-id=${id}`)

    fetchAndDisplayPlayer(); 
    fetchAndDisplayCompanies();
});

function fetchAndDisplayPlayer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayer(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", `${gatewayUrl}/api/players/${id}`, true);
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
            displayUpdateResult(this.status);
        }
    };
    xhttp.open("PUT", `${gatewayUrl}/api/players/${id}`, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function displayUpdateResult(status) {
    const display = document.getElementById('resultDisplay');
    display.textContent = (status == 202) 
        ? `Succesfully updated player#${id}` 
        : `Updating player#${id} failed (${status})`;
}

function fetchAndDisplayCompanies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            populateCompaniesTable(JSON.parse(this.responseText));
        }
    };
    xhttp.open('GET', `${gatewayUrl}/api/players/${id}/companies`, true);
    xhttp.send();
}

function populateCompaniesTable(companiesResponse) {
    let tableBody = document.getElementById('companiesTableBody');
    clearElementChildren(tableBody);
    companiesResponse.companies.forEach(companies => {
        tableBody.appendChild(createTableRow(companies));
    });
}

function createTableRow(company) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(company.id));
    tr.appendChild(createTextCell(company.name));
    tr.appendChild(createLinkCell('view', `company_info.html?id=${company.id}`));
    tr.appendChild(createButtonCell('delete', () => deleteCompany(company)));
    return tr;
}

function deleteCompany(company) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCompanies();
        }
    };
    xhttp.open("DELETE", `${gatewayUrl}/api/players/${id}/companies/${company.id}`, true);
    xhttp.send();
}
