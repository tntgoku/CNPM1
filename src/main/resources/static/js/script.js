

let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  
        slides[i].classList.remove("fade"); // Loại bỏ lớp "fade" nếu có
}
for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
    
}
slides[slideIndex-1].style.display = "flex";
for (i = 0; i < slideIndex - 1; i++) {
    slides[i].classList.add("fade");
  }
  dots[slideIndex-1].className += " active";
}

// fetch(`/details?masanpham=${masanpham}`)
//   .then(response => {
//     if (!response.ok) {
//       throw new Error('Không thể lấy thông tin sản phẩm. Vui lòng thử lại!');
//     }
//     return response.json();
//   })
//   .then(data => {
//     document.getElementById('product-name').innerText = data.name;
//     document.getElementById('product-price').innerText = `${data.price} VND`;
//     document.getElementById('product-description').innerText = data.description;
//   })
//   .catch(error => console.error('Lỗi khi lấy thông tin sản phẩm:', error));

//   function addToCart(element) {
//     const sku = element.getAttribute("data-sku"); // Lấy SKU của sản phẩm

//     if (!sku) {
//       alert("Lỗi: Không tìm thấy mã sản phẩm!");
//       return;
//     }

//     fetch('/cart/add', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify({ sku: sku }), // Gửi SKU sản phẩm
//     })
//       .then(response => {
//         if (!response.ok) {
//           throw new Error('Thêm vào giỏ hàng thất bại!');
//         }
//         return response.json();
//       })
//       .then(data => {
//         if (data.success) {
//           alert("Sản phẩm đã được thêm vào giỏ hàng!");
//         } else {
//           alert(data.message || "Thêm vào giỏ hàng thất bại!");
//         }
//       })
//       .catch(error => console.error("Lỗi:", error));
//   }
//   app.post('/addToCart', (req, res) => {
//     console.log("Dữ liệu nhận được:", req.body); // Log dữ liệu nhận được
//     const { sku } = req.body;
  
//     if (!sku) {
//       return res.status(400).json({ success: false, message: "SKU không hợp lệ!" });
//     }
  
//     // Thực hiện logic thêm vào giỏ hàng
//     // Ví dụ: kiểm tra trong cơ sở dữ liệu
//     try {
//       // Giả sử thêm vào cơ sở dữ liệu thành công
//       res.json({ success: true, message: "Thêm vào giỏ hàng thành công!" });
//     } catch (error) {
//       console.error("Lỗi server:", error); // Log chi tiết lỗi
//       res.status(500).json({ success: false, message: "Lỗi server!" });
//     }
//   });
  
