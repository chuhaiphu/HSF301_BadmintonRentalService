function openAddCarModal() {
  document.getElementById('addCarModal').classList.remove('hidden');
}

function closeAddCarModal() {
  document.getElementById('addCarModal').classList.add('hidden');
}

function openUpdateCarModal(element) {
  var carId = element.getAttribute('data-car-id');
  var carName = element.getAttribute('data-car-name');
  var carModelYear = element.getAttribute('data-car-model-year');
  var color = element.getAttribute('data-color');
  var capacity = element.getAttribute('data-capacity');
  var importDate = element.getAttribute('data-import-date');
  var rentPrice = element.getAttribute('data-rent-price');
  var carProducer = element.getAttribute('data-car-producer');
  var status = element.getAttribute('data-status');

  document.getElementById('carId').value = carId;
  document.getElementById('carNameUpdate').value = carName;
  document.getElementById('carModelYearUpdate').value = carModelYear;
  document.getElementById('colorUpdate').value = color;
  document.getElementById('capacityUpdate').value = capacity;
  document.getElementById('importDateUpdate').value = importDate;
  document.getElementById('rentPriceUpdate').value = rentPrice;
  document.getElementById('carProducerUpdate').value = carProducer;
  document.getElementById('statusUpdate').value = status;
  document.getElementById('updateCarModal').classList.remove('hidden');
}

function closeUpdateCarModal() {
  document.getElementById('updateCarModal').classList.add('hidden');
}
function confirmDelete(carId) {
  Swal.fire({
    title: 'Are you sure?',
    text: 'You are about to delete this car. This action cannot be undone.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'Cancel'
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = `/admin/deleteCar/${carId}`;
    }
  });
  return false;
}

function validateUpdateCarForm() {
  // Add your form validation logic here
  return true;
}
