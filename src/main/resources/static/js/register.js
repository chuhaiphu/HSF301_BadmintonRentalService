function validateForm() {
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var dob = document.getElementById("dob").value;

    if (username === "" || email === "" || password === "" || firstName === "" || lastName === "" || dob === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please fill in all required fields!',
        });
        return false;
    }

    if (password !== confirmPassword) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Passwords do not match!',
        });
        return false;
    }

    return true;
}