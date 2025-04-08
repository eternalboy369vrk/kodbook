/**
 * 
 */
document.querySelectorAll('.menuToggle').forEach(toggle => {
    toggle.addEventListener('change', function() {
        const menu = this.nextElementSibling.nextElementSibling; // Select the corresponding menu
        if (this.checked) {
            menu.style.display = 'block';
        } else {
            menu.style.display = 'none';
        }
    });
});