function getMargin() {
    var contentMargin = document.getElementById("middle").offsetWidth;
    var logo = document.getElementById("naglowek");
    logo.style.width = contentMargin + "px";
}