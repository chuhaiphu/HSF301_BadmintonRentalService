
function toggleDropdown() {
    var banner = document.getElementById('dropdownBanner');
    if (banner.style.display === 'none' || banner.style.display === '') {
        banner.style.display = 'block';
    } else {
        banner.style.display = 'none';
    }
}

window.onclick = function(event) {
    if (!event.target.matches('.profile-image')) {
        var dropdowns = document.getElementsByClassName('dropdown-banner');
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.style.display === 'block') {
                openDropdown.style.display = 'none';
            }
        }
    }
}
