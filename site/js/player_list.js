import { clearElementChildren, createLinkCell, createButtonCell, createTextCell } from './util.js';
import { gatewayUrl } from './config.js'

window.addEventListener('load', () => { fetchAndDisplayPlayers(); } )

function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            populatePlayersTable(JSON.parse(this.responseText));
        }
    };
    xhttp.open('GET', `${gatewayUrl}/api/players`, true);
    xhttp.send();
}

function populatePlayersTable(playersResponse) {
    let tableBody = document.getElementById('playersTableBody');
    clearElementChildren(tableBody);
    playersResponse.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    });
}

function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.id));
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createLinkCell('view', `player_info.html?id=${player.id}`));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player)));
    return tr;
}

function deletePlayer(player) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    xhttp.open("DELETE", `${gatewayUrl}/api/players/${player.id}`, true);
    xhttp.send();
}
