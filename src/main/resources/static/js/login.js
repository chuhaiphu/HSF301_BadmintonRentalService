document.addEventListener('DOMContentLoaded', function() {
    var loginStatusElement = document.getElementById('loginStatus');
    var registerStatusElement = document.getElementById('registerStatus');

    if (loginStatusElement) {
        var loginStatus = loginStatusElement.value;
        if (loginStatus === 'failure') {
            Swal.fire({
                icon: 'error',
                title: 'Login Failed',
                text: 'Invalid email or password. Please try again.',
            });
        }
    }

    if (registerStatusElement) {
        var registerStatus = registerStatusElement.value;
        if (registerStatus === 'success') {
            Swal.fire({
                icon: 'success',
                title: 'Registration Successful',
                text: 'You can now log in with your new account.',
            });
        } else if (registerStatus === 'failure') {
            Swal.fire({
                icon: 'error',
                title: 'Registration Failed',
                text: 'Please try again.',
            });
        }
    }
});