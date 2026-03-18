
## 🚀 Hướng dẫn cài đặt và chạy bài

### 1. Chuẩn bị môi trường
- Cài đặt **JDK 17** trở lên.
- Cài đặt **Node.js** (khuyên dùng bản LTS).
- Cài đặt **XAMPP** (để chạy MySQL).

### 2. Cài đặt Cơ sở dữ liệu
1. Mở **XAMPP Control Panel** ,cấu hình lại port 8080: 
- Mở XAMPP Control Panel.
- Ở dòng Apache, nhấn nút Config -> Chọn Apache (httpd.conf).
- Nhấn Ctrl + F tìm chữ 8080.
- Đổi tất cả chỗ nào là 8080 thành 8082 (Ví dụ: Listen 8082 và ServerName localhost:8082).
- Save file lại rồi nhấn Stop Apache, sau đó Start lại.

2. khởi động **MySQL**.
   ```
4. **Cấu hình VNPAY**: (Nếu bạn dùng tài khoản Sandbox riêng, hãy cập nhật tại `com.example.WebTra_Springboot.config.VnPayConfig`).

### 3. Chạy dự án

**Lưu ý**: Chỉ cần thực hiện 2 bước dưới đây là bài đã hoạt động bình thường và có thể demo đầy đủ các chức năng.

#### Bước 1: Chạy Backend
Mở terminal tại thư mục gốc:
```bash
./mvnw spring-boot:run
```
*Backend sẽ chạy tại: [http://localhost:8080](http://localhost:8080)*

#### Bước 2: Chạy Frontend
1. Mở terminal mới, di chuyển vào thư mục `frontend`:
```bash
cd frontend
npm install
npm run dev
```
*Frontend sẽ chạy tại: [http://localhost:5173](http://localhost:5173)*

---

## 🛠 Bước 3
### Thiết lập ngrok (Để nhận thông báo IPN từ VNPAY)
Mặc dù dùng `localhost` bài vẫn chạy thanh toán bình thường,
1. Mở terminal mới chạy lệnh: `./ngrok http 8080`


## 📝 Tài khoản thử nghiệm ( Tự đăng kí)
- **Admin**: `admin@example.com` / `admin123` (Tự đăng kí)
- **User**: `user@example.com` / `user123` (Tự đăng kí)

---

## ⚡ Một số lưu ý khi chạy
- Nếu gặp lỗi **Port 8080 already in use**, hãy kiểm tra xem có ứng dụng nào (như XAMPP Apache) đang chiếm cổng này không và đổi cổng hoặc tắt ứng dụng đó.
- Khi thanh toán VNPAY trong môi trường Sandbox, hãy sử dụng **Thẻ ATM nội địa (NCB)** với thông tin:
  - Số thẻ: `9704198526191432198`
  - Tên chủ thẻ: `NGUYEN VAN A`
  - Ngày phát hành: `07/15`
  - Mã OTP: `123456`

---

*Chúc bạn có những giây phút trải nghiệm tuyệt vời cùng Trà Thơm!*