$(document).ready(function () {
    $('.btn-deleted-u').click(function () {
        // Get the product ID from the button's data-id attribute
        const productId = $(this).data('id');
        console.log("Product ID:", productId);

        // Send AJAX POST request to the backend to delete the product
        $.ajax({
            url: '/mainProduct', // Your endpoint for deletion
            type: 'POST',
            data: { masanpham: productId }, // Send product ID as 'masanpham'
            success: function (response) {
                alert("Product deleted successfully!");
                console.log(response);
                location.reload(); // Reload the page to reflect the changes
            },
            error: function (xhr, status, error) {
                alert("Error deleting product: " + error);
            }
        });
    });
});
