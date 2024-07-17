function validateForm() {
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
