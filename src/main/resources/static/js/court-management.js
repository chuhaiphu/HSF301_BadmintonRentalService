function openAddCourtModal() {
  document.getElementById('addCourtModal').classList.remove('hidden');
}

function closeAddCourtModal() {
  document.getElementById('addCourtModal').classList.add('hidden');
}

function openUpdateCourtModal(element) {
  var courtId = element.getAttribute('data-court-id');
  var courtName = element.getAttribute('data-court-name');
  var address = element.getAttribute('data-address');
  var description = element.getAttribute('data-description');
  var status = element.getAttribute('data-status');
  var phone = element.getAttribute('data-phone');
  var price = element.getAttribute('data-price');

  document.getElementById('courtId').value = courtId;
  document.getElementById('courtNameUpdate').value = courtName;
  document.getElementById('addressUpdate').value = address;
  document.getElementById('descriptionUpdate').value = description;
  document.getElementById('statusUpdate').value = status;
  document.getElementById('phoneUpdate').value = phone;
  document.getElementById('priceUpdate').value = price;
  document.getElementById('updateCourtModal').classList.remove('hidden');
}

function closeUpdateCourtModal() {
  document.getElementById('updateCourtModal').classList.add('hidden');
}

function confirmDelete(courtId) {
  Swal.fire({
    title: 'Are you sure?',
    text: 'You are about to delete this court. This action cannot be undone.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'Cancel'
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = `/admin/deleteCourt/${courtId}`;
    }
  });
  return false;
}

function validateCourtForm() {
  const addButton = document.getElementById('addCourtButton');
  const addButtonText = document.getElementById('addButtonText');
  const addButtonLoader = document.getElementById('addButtonLoader');

  addButton.disabled = true;
  addButtonText.classList.add('hidden');
  addButtonLoader.classList.remove('hidden');

  // Simulate form submission delay (remove this in production)
  setTimeout(() => {
      closeAddCourtModal();
      addButton.disabled = false;
      addButtonText.classList.remove('hidden');
      addButtonLoader.classList.add('hidden');
  }, 2000);

  return true;
}

function validateCourtForm() {
  const courtName = document.getElementById("courtName").value;
  const address = document.getElementById("address").value;
  const description = document.getElementById("description").value;
  const phone = document.getElementById("phone").value;
  const price = document.getElementById("price").value;
  const status = document.getElementById("status").value;

  if (courtName === "" || address === "" || description === "" || phone === "" || price === "" || status === "") {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Please fill in all required fields!',
    });
    return false;
  }

  if (isNaN(price) || parseFloat(price) <= 0) {
    Swal.fire({
      icon: 'error',
      title: 'Invalid Price',
      text: 'Please enter a valid positive number for the price.',
    });
    return false;
  }

  if (!/^\d{10}$/.test(phone)) {
    Swal.fire({
      icon: 'error',
      title: 'Invalid Phone Number',
      text: 'Please enter a valid 10-digit phone number.',
    });
    return false;
  }

  return true;
}

function validateUpdateCourtForm() {
  const courtName = document.getElementById("courtNameUpdate").value;
  const address = document.getElementById("addressUpdate").value;
  const description = document.getElementById("descriptionUpdate").value;
  const phone = document.getElementById("phoneUpdate").value;
  const price = document.getElementById("priceUpdate").value;
  const status = document.getElementById("statusUpdate").value;

  if (courtName === "" || address === "" || description === "" || phone === "" || price === "" || status === "") {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Please fill in all required fields!',
    });
    return false;
  }

  if (isNaN(price) || parseFloat(price) <= 0) {
    Swal.fire({
      icon: 'error',
      title: 'Invalid Price',
      text: 'Please enter a valid positive number for the price.',
    });
    return false;
  }

  if (!/^\d{10}$/.test(phone)) {
    Swal.fire({
      icon: 'error',
      title: 'Invalid Phone Number',
      text: 'Please enter a valid 10-digit phone number.',
    });
    return false;
  }

  return true;
}


function showSlotDetails(courtId) {
  document.getElementById('slotDetailsModal').classList.remove('hidden');
}

function closeSlotDetailsModal() {
  document.getElementById('slotDetailsModal').classList.add('hidden');
}