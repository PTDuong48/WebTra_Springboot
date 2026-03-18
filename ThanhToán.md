Hướng dẫn Kiểm tra (Test) Thanh toán VNPAY Sandbox
Dự án của bạn hiện đã được tích hợp cổng thanh toán VNPAY hoàn chỉnh. Dưới đây là cách bạn thực hiện một giao dịch giả lập từ đầu đến cuối.

🚀 Bước 1: Khởi động hệ thống

    Đảm bảo Backend (Spring Boot) đang chạy trên cổng 8080.

    Đảm bảo Frontend (React) đang chạy trên cổng 5173.

    BẮT BUỘC: Đảm bảo ngrok vẫn đang chạy lệnh ./ngrok http 8080 và địa chỉ https://subfoliar-arian-slouchily.ngrok-free.dev vẫn đang hoạt động.

🛒 Bước 2: Thực hiện mua hàng

    Truy cập vào trang sản phẩm, thêm một vài loại trà vào giỏ hàng.

    Đi tới trang Giỏ hàng -> Thanh toán.

    Tại trang Checkout, bạn sẽ thấy phương thức duy nhất hiện tại là Thanh toán qua VNPAY.

    Điền đầy đủ thông tin nhận hàng -> Nhấn ĐẶT HÀNG QUA VNPAY.

💳 Bước 3: Thanh toán tại cổng VNPAY (Giả lập)

    Sau khi nhấn nút, bạn sẽ được tự động chuyển hướng sang trang thanh toán của VNPAY Sandbox. Hãy sử dụng thông tin thẻ sau:

    Ngân hàng: NCB (Đã được chọn tự động)

    Số thẻ: 9704198526191432198

    Tên chủ thẻ: NGUYEN VAN A

    Ngày phát hành: 07/15

    Mật khẩu OTP: 123456 (Nhập sau khi nhấn xác nhận thẻ)

✅ Bước 4: Kiểm tra kết quả

    Sau khi nhập OTP thành công, VNPAY sẽ hiện thông báo thành công và tự động chuyển bạn quay lại trang Kết quả thanh toán (/payment-result) của bạn.

    Bạn sẽ thấy giao diện thông báo "Thanh toán thành công" màu xanh rất đẹp.

    Quan trọng: Hãy kiểm tra Terminal đang chạy Backend. Bạn sẽ thấy dòng chữ: Thanh toán thành công IPN cho đơn hàng: [Mã số]. Điều này chứng tỏ ngrok đã nhận tín hiệu "ngầm" từ VNPAY và cập nhật vào hệ thống của bạn thành công.

    Mời bạn thử nghiệm giao dịch đầu tiên! Nếu có bất kỳ lỗi nào hiện ra, hãy copy thông báo lỗi trong Terminal gửi cho mình nhé!