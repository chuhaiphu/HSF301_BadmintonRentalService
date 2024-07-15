function deleteOrder(element) {
    var orderId = element.getAttribute('data-order-id');
    if (confirm('Are you sure you want to delete this order?')) {
        window.location.href = '/admin/deleteOrder/' + orderId;
    }
}