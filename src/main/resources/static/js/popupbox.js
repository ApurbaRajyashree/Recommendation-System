function showPopup() {
    document.getElementById("popup").style.display = "block";
}

function hidePopup() {
    document.getElementById("popup").style.display = "none";
}
function confirmLogout() {
    window.location.href = "/";
    hidePopup();
}
