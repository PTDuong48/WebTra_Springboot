Dự án WebTra_Springboot - Database Schema

Tài liệu này mô tả chi tiết cấu trúc cơ sở dữ liệu (Database Schema) của dự án WebTra_Springboot, bao gồm các bảng, thuộc tính và mối quan hệ giữa chúng.

1. Sơ đồ thực thể quan hệ (ERD)

Hệ thống bao gồm các thực thể chính: User, Role, Category, Product, ProductWeight, ProductImage, BrewingGuide, Review, Order, OrderDetail, Blog, và BlogCategory.

2. Chi tiết các bảng
2.1. Bảng users (Thực thể User)

Lưu trữ thông tin người dùng và khách hàng.

id (Long, PK): Mã định danh duy nhất.

full_name (String, Not Null): Họ và tên.

email (String, Not Null, Unique): Địa chỉ email (dùng để đăng nhập).

password (String, Not Null): Mật khẩu đã mã hóa.

phone_number (String): Số điện thoại.

address (String): Địa chỉ giao hàng mặc định.

profile_image (String): Đường dẫn ảnh đại diện.

2.2. Bảng roles (Thực thể Role)

Lưu trữ các quyền trong hệ thống.

id (Long, PK): Mã định danh duy nhất.

name (String, Not Null, Unique): Tên quyền (ví dụ: ROLE_ADMIN, ROLE_USER).

2.3. Bảng users_roles (Bảng trung gian)

Thiết lập mối quan hệ Nhiều-Nhiều (Many-to-Many) giữa users và roles.

user_id (FK): Liên kết tới users(id).

role_id (FK): Liên kết tới roles(id).

2.4. Bảng categories (Thực thể Category)

Phân loại sản phẩm trà.

id (Long, PK): Mã định danh duy nhất.

name (String, Not Null): Tên danh mục.

description (String): Mô tả danh mục.

2.5. Bảng products (Thực thể Product)

Lưu trữ thông tin cơ bản của sản phẩm trà.

id (Long, PK): Mã định danh duy nhất.

name (String, Not Null): Tên sản phẩm.

description (Text): Mô tả chi tiết sản phẩm.

origin (String): Nguồn gốc của trà.

image_url (String): Ảnh đại diện của sản phẩm.

category_id (FK): Liên kết tới categories(id).

created_at (LocalDateTime): Ngày tạo sản phẩm.

updated_at (LocalDateTime): Ngày cập nhật sản phẩm.

2.6. Bảng product_weights (Thực thể ProductWeight)

Lưu các trọng lượng khác nhau của cùng một sản phẩm.

Ví dụ: 100g, 250g, 500g.

id (Long, PK): Mã định danh duy nhất.

product_id (FK): Liên kết tới products(id).

weight (Integer): Trọng lượng của sản phẩm (gram).

price (Double): Giá bán của trọng lượng này.

stock (Integer): Số lượng tồn kho.

created_at (LocalDateTime): Ngày tạo.

2.7. Bảng product_images (Thực thể ProductImage)

Lưu nhiều hình ảnh chi tiết của một sản phẩm.

id (Long, PK): Mã định danh duy nhất.

product_id (FK): Liên kết tới products(id).

image_url (String): Đường dẫn ảnh sản phẩm.

is_main (Boolean): Xác định ảnh chính của sản phẩm.

sort_order (Integer): Thứ tự hiển thị ảnh.

2.8. Bảng brewing_guides (Thực thể BrewingGuide)

Lưu hướng dẫn pha trà cho từng sản phẩm.

id (Long, PK): Mã định danh duy nhất.

product_id (FK): Liên kết tới products(id).

title (String): Tiêu đề hướng dẫn pha trà.

content (Text): Nội dung hướng dẫn pha trà.

2.9. Bảng reviews (Thực thể Review)

Lưu đánh giá của khách hàng về sản phẩm.

id (Long, PK): Mã định danh duy nhất.

product_id (FK): Liên kết tới products(id).

user_id (FK): Liên kết tới users(id).

rating (Integer): Số sao đánh giá (1–5).

comment (Text): Nội dung đánh giá.

created_at (LocalDateTime): Ngày tạo đánh giá.

2.10. Bảng orders (Thực thể Order)

Lưu trữ thông tin đơn hàng.

id (Long, PK): Mã định danh duy nhất.

user_id (FK): Liên kết tới users(id) (người đặt hàng).

order_date (LocalDateTime): Ngày giờ đặt hàng.

total_amount (Double): Tổng giá trị đơn hàng.

status (String): Trạng thái đơn hàng (Pending, Processing, Completed, Cancelled).

shipping_address (String): Địa chỉ giao hàng.

2.11. Bảng order_details (Thực thể OrderDetail)

Lưu trữ chi tiết các sản phẩm trong mỗi đơn hàng.

id (Long, PK): Mã định danh duy nhất.

order_id (FK): Liên kết tới orders(id).

product_id (FK): Liên kết tới products(id).

price (Double): Giá sản phẩm tại thời điểm mua.

quantity (Integer): Số lượng mua.

2.12. Bảng blog_categories (Thực thể BlogCategory)

Phân loại các bài viết.

id (Long, PK): Mã định danh duy nhất.

name (String, Not Null): Tên loại bài viết.

2.13. Bảng blogs (Thực thể Blog)

Lưu trữ các bài viết kiến thức và tin tức về trà.

id (Long, PK): Mã định danh duy nhất.

title (String, Not Null): Tiêu đề bài viết.

content (Text): Nội dung chi tiết bài viết.

summary (String): Tóm tắt bài viết.

image_url (String): Ảnh đại diện bài viết.

status (String, Not Null): Trạng thái (Published, Draft).

created_at (LocalDateTime): Ngày tạo.

category_id (FK): Liên kết tới blog_categories(id).

3. Các mối quan hệ chính

User <-> Role: Nhiều-Nhiều (Many-to-Many) thông qua bảng users_roles.

Category -> Product: Một-Nhiều (One-to-Many). Một danh mục có nhiều sản phẩm.

Product -> ProductWeight: Một-Nhiều (One-to-Many). Một sản phẩm có nhiều trọng lượng khác nhau.

Product -> ProductImage: Một-Nhiều (One-to-Many). Một sản phẩm có nhiều hình ảnh.

Product -> BrewingGuide: Một-Nhiều (One-to-Many). Một sản phẩm có thể có nhiều hướng dẫn pha trà.

Product -> Review: Một-Nhiều (One-to-Many). Một sản phẩm có nhiều đánh giá từ khách hàng.

User -> Order: Một-Nhiều (One-to-Many). Một người dùng có thể có nhiều đơn hàng.

Order -> OrderDetail: Một-Nhiều (One-to-Many). Một đơn hàng có nhiều chi tiết sản phẩm.

Product -> OrderDetail: Một-Nhiều (One-to-Many). Một sản phẩm có thể xuất hiện trong nhiều chi tiết đơn hàng.

BlogCategory -> Blog: Một-Nhiều (One-to-Many). Một danh mục blog có nhiều bài viết.