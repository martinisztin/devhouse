function openClose() {
    let x = document.getElementById("nav");

    if(x.className.includes("responsive")) {
        x.className = x.className.replace(" responsive", "");
        return;
    }

    if (x.className.includes("topnav")) {
        x.className += " responsive";
    }

}