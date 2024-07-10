function openAddCarRentalModal() {
    document.getElementById('addCarRentalModal').classList.remove('hidden');
}

function closeAddCarRentalModal() {
    document.getElementById('addCarRentalModal').classList.add('hidden');
}

function openUpdateCarRentalModal(element) {
    var rentalId = element.getAttribute('data-rental-id');
    var customerId = element.getAttribute('data-customer-id');
    var carId = element.getAttribute('data-car-id');
    var pickupDate = element.getAttribute('data-pickup-date');
    var returnDate = element.getAttribute('data-return-date');
    var rentPrice = element.getAttribute('data-rent-price');
    var status = element.getAttribute('data-status');
    var paymentMethod = element.getAttribute('data-payment-method');

    document.getElementById('rentalId').value = rentalId;
    document.getElementById('customerUpdate').value = customerId;
    document.getElementById('carUpdate').value = carId;
    document.getElementById('pickupDateUpdate').value = pickupDate;
    document.getElementById('returnDateUpdate').value = returnDate;
    document.getElementById('rentPriceUpdate').value = rentPrice;
    document.getElementById('statusUpdate').value = status;
    document.getElementById('paymentMethodUpdate').value = paymentMethod;
    document.getElementById('updateCarRentalModal').classList.remove('hidden');
}

function closeUpdateCarRentalModal() {
    document.getElementById('updateCarRentalModal').classList.add('hidden');
}

function validateUpdateCarRentalForm() {
    // Add your form validation logic here
    return true;
}
function openGenerateReportModal() {
    document.getElementById('generateReportModal').classList.remove('hidden');
}

function closeGenerateReportModal() {
    document.getElementById('generateReportModal').classList.add('hidden');
}

// function confirmDeleteCarRental(rentalId) {
//     Swal.fire({
//         title: 'Are you sure?',
//         text: "You won't be able to revert this!",
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         confirmButtonText: 'Yes, delete it!'
//     }).then((result) => {
//         if (result.isConfirmed) {
//             // If confirmed, redirect to delete URL
//             window.location.href = '/admin/deleteCarRental/' + rentalId;
//         }
//     });
//     return false; // Prevent the default link behavior
// }
