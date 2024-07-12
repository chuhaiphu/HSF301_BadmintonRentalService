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
  var openTime = element.getAttribute('data-open-time');
  var closeTime = element.getAttribute('data-close-time');
  var status = element.getAttribute('data-status');

  document.getElementById('courtId').value = courtId;
  document.getElementById('courtNameUpdate').value = courtName;
  document.getElementById('addressUpdate').value = address;
  document.getElementById('descriptionUpdate').value = description;
  document.getElementById('openTimeUpdate').value = openTime;
  document.getElementById('closeTimeUpdate').value = closeTime;
  document.getElementById('statusUpdate').value = status;
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

function validateUpdateCourtForm() {
  // Add your form validation logic here
  return true;
}
