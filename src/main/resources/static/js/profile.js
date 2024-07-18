function validateInfoForm() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var dob = document.getElementById("dob").value;
    var gender = document.getElementById("gender").value;

    if (firstName.trim() === "" || lastName.trim() === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'First name and last name are required!',
        });
        return false;
    }

    if (dob === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please select your date of birth!',
        });
        return false;
    }

    if (gender === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please select your gender!',
        });
        return false;
    }

    return true;
}

function validatePasswordForm() {
    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("newPassword").value;
    var confirmNewPassword = document.getElementById("confirmNewPassword").value;

    if (oldPassword === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please enter your old password!',
        });
        return false;
    }

    if (newPassword === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please enter a new password!',
        });
        return false;
    }

    if (newPassword !== confirmNewPassword) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'New passwords do not match!',
        });
        return false;
    }

    return true;
}