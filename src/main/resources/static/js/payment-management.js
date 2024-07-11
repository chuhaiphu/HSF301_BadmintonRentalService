function openAddPaymentModal() {
    document.getElementById('addPaymentModal').classList.remove('hidden');
}

function closeAddPaymentModal() {
    document.getElementById('addPaymentModal').classList.add('hidden');
}

function openUpdatePaymentModal(element) {
    var paymentId = element.getAttribute('data-payment-id');
    var customerId = element.getAttribute('data-customer-id');
    var courtId = element.getAttribute('data-court-id');
    var finalPrice = element.getAttribute('data-final-price');
    var payAt = element.getAttribute('data-pay-at');

    document.getElementById('paymentId').value = paymentId;
    document.getElementById('customerUpdate').value = customerId;
    document.getElementById('courtUpdate').value = courtId;
    document.getElementById('finalPriceUpdate').value = finalPrice;
    document.getElementById('payAtUpdate').value = payAt;
    document.getElementById('updatePaymentModal').classList.remove('hidden');
}

function closeUpdatePaymentModal() {
    document.getElementById('updatePaymentModal').classList.add('hidden');
}

function validatePaymentForm() {
    // Add your form validation logic here
    return true;
}

function confirmDeletePayment(paymentId) {
    if (confirm('Are you sure you want to delete this payment?')) {
        window.location.href = '/admin/deletePayment/' + paymentId;
    }
    return false;
}
