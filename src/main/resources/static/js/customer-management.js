function openAddCustomerModal() {
  document.getElementById('addCustomerModal').classList.remove('hidden');
}

function closeAddCustomerModal() {
  document.getElementById('addCustomerModal').classList.add('hidden');
}

function openUpdateCustomerModal(element) {
  var customerId = element.getAttribute('data-customer-id');
  var customerName = element.getAttribute('data-customer-name');
  var mobile = element.getAttribute('data-mobile');
  var birthday = element.getAttribute('data-birthday');
  var identityCard = element.getAttribute('data-identity-card');
  var licenceNumber = element.getAttribute('data-licence-number');
  var licenceDate = element.getAttribute('data-licence-date');

  document.getElementById('customerId').value = customerId;
  document.getElementById('customerNameUpdate').value = customerName;
  document.getElementById('mobileUpdate').value = mobile;
  document.getElementById('birthdayUpdate').value = birthday;
  document.getElementById('identityCardUpdate').value = identityCard;
  document.getElementById('licenceNumberUpdate').value = licenceNumber;
  document.getElementById('licenceDateUpdate').value = licenceDate;
  document.getElementById('updateCustomerModal').classList.remove('hidden');
}

function closeUpdateCustomerModal() {
  document.getElementById('updateCustomerModal').classList.add('hidden');
}

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


function confirmDelete(customerId) {
  Swal.fire({
    title: 'Are you sure?',
    text: 'You are about to delete this book. This action cannot be undone.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'Cancel'
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = `/admin/deleteCustomer/${customerId}`;
    }
  });
  return false;
}

document.getElementById('searchInput').addEventListener('input', function() {
  this.form.submit();
});

function openViewCustomerModal(element) {
  var customerId = element.getAttribute('data-customer-id');
  var username = element.getAttribute('data-username');
  var email = element.getAttribute('data-email');
  var firstName = element.getAttribute('data-first-name');
  var lastName = element.getAttribute('data-last-name');
  var gender = element.getAttribute('data-gender');
  var dob = element.getAttribute('data-dob');
  var status = element.getAttribute('data-status');

  document.getElementById('usernameView').textContent = username;
  document.getElementById('emailView').textContent = email;
  document.getElementById('firstNameView').textContent = firstName;
  document.getElementById('lastNameView').textContent = lastName;
  document.getElementById('genderView').textContent = gender === 'true' ? 'Male' : 'Female';
  document.getElementById('dobView').textContent = dob;
  document.getElementById('statusView').textContent = status === 'true' ? 'Active' : 'Inactive';

  document.getElementById('viewCustomerModal').classList.remove('hidden');
}

function closeViewCustomerModal() {
  document.getElementById('viewCustomerModal').classList.add('hidden');
}



