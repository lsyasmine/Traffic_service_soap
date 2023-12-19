// Dans votre classe de configuration du serveur web
response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
response.setHeader("Access-Control-Allow-Headers", "Content-Type");

function getTotalDetails() {
    axios.post('http://0.0.0.0:8086/AccidentsReason', {
        CauseGetTotalDetails: {}
    }, {
        headers: {
            'Content-Type': 'text/xml',
        },
        mode: 'no-cors'
    })

        .then(response => {
            document.getElementById('result').innerHTML = response.data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('result').innerHTML = 'Error occurred.';
        });
}

function getAccidentDetailByImpliedBy() {
    var impliedBy = prompt('Enter ImpliedBy:');

    axios.post('http://localhost:8086/AccidentImpliedBy', {
        getAccidentDetailByImpliedBy: {
            ImpliedBy: impliedBy
        }
    }, {
        headers: {
            'Content-Type': 'text/xml',
        }
    })
        .then(response => {
            document.getElementById('result').innerHTML = response.data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('result').innerHTML = 'Error occurred.';
        });
}
// Add similar functions for other operations

