// addToCart.js

// Lắng nghe sự kiện nhấp chuột trên các nút thêm vào giỏ hàng
document.addEventListener("DOMContentLoaded", function () {
    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
    
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();  // Ngừng sự kiện mặc định (nếu có)
            
            const productId = button.getAttribute('data-product-id');  // Lấy ID sản phẩm từ thuộc tính data
            
            if (productId) {
                // Gửi yêu cầu POST đến server để thêm sản phẩm vào giỏ hàng
                fetch('/addToCart', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: `productId=${encodeURIComponent(productId)}`
                })
                .then(response => {
                    if (response.ok) {
                        // Nếu thành công, thông báo cho người dùng và cập nhật giỏ hàng
                        alert('Sản phẩm đã được thêm vào giỏ hàng!');
                        updateCartUI();
                    } else {
                        alert('Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.');
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi thêm sản phẩm:', error);
                    alert('Lỗi kết nối với server.');
                });
            } else {
                alert('Sản phẩm không hợp lệ.');
            }
        });
    });
});

// Hàm cập nhật giỏ hàng sau khi thêm sản phẩm (tuỳ chỉnh theo nhu cầu)
function updateCartUI() {
    // Lấy thông tin giỏ hàng và cập nhật giao diện người dùng
    fetch('/cart')
        .then(response => response.json())
        .then(data => {
            const cartCount = document.getElementById('cart-count');
            cartCount.textContent = data.items.length; // Cập nhật số lượng sản phẩm trong giỏ
        })
        .catch(error => {
            console.error('Lỗi khi cập nhật giỏ hàng:', error);
        });
}
// Hàm cập nhật giỏ hàng sau khi thêm sản phẩm (tuỳ chỉnh theo nhu cầu)
function updateCartUI() {
    // Lấy thông tin giỏ hàng và cập nhật giao diện người dùng
    fetch('/cart')
        .then(response => response.json())
        .then(data => {
            const cartCount = document.getElementById('cart-count');
            cartCount.textContent = data.items.length; // Cập nhật số lượng sản phẩm trong giỏ
        })
        .catch(error => {
            console.error('Lỗi khi cập nhật giỏ hàng:', error);
        });
}