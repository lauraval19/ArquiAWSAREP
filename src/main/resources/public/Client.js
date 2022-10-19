

function loadbases() {
    let campo = document.getElementById("campo");
     const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
               document.getElementById("getrespmsg").innerHTML = this.responseText;
        }
        xhttp.open("GET", "/fachada?cad="+campo.value);
        xhttp.send();
    }
