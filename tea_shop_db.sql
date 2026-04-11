-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 11, 2026 lúc 03:34 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tea_shop_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blogs`
--

CREATE TABLE `blogs` (
  `id` bigint(20) NOT NULL,
  `content` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `status` enum('DRAFT','PUBLISHED') DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `blogs`
--

INSERT INTO `blogs` (`id`, `content`, `created_at`, `image_url`, `status`, `summary`, `title`, `category_id`, `updated_at`, `author_id`) VALUES
(1, 'Trà xanh từ lâu đã được xem là một trong những loại thức uống tốt nhất cho sức khỏe. Nhờ chứa nhiều chất chống oxy hóa, đặc biệt là catechin, trà xanh giúp bảo vệ cơ thể khỏi các gốc tự do gây hại.\n\nMột trong những lợi ích nổi bật của trà xanh là hỗ trợ giảm cân. Các hợp chất trong trà giúp tăng cường quá trình trao đổi chất, từ đó đốt cháy năng lượng hiệu quả hơn.\n\nNgoài ra, trà xanh còn giúp cải thiện chức năng não bộ. Hàm lượng caffeine thấp kết hợp với L-theanine giúp tăng sự tỉnh táo mà không gây căng thẳng như cà phê.\n\nBên cạnh đó, việc uống trà xanh đều đặn còn giúp giảm nguy cơ mắc các bệnh tim mạch và hỗ trợ kiểm soát lượng đường trong máu.\n\nTuy nhiên, bạn nên uống trà đúng cách và không nên uống khi đói để tránh ảnh hưởng đến dạ dày.', '2026-03-17 13:35:06.000000', '/uploads/cbee9a30-ca39-4265-b878-15295e97f12a_suckhoe.jpg', 'DRAFT', NULL, 'Lợi ích tuyệt vời của trà xanh đối với sức khỏe', 1, '2026-03-17 13:35:06.000000', NULL),
(2, 'Pha trà tưởng chừng đơn giản nhưng để có được một ấm trà ngon đúng điệu thì cần có kỹ thuật và sự tinh tế.\n\nĐầu tiên, bạn cần lựa chọn loại trà phù hợp và sử dụng nước sạch. Nhiệt độ nước đóng vai trò rất quan trọng, vì mỗi loại trà sẽ phù hợp với một mức nhiệt khác nhau.\n\nTrước khi pha, hãy tráng ấm trà bằng nước nóng để giữ nhiệt. Sau đó, cho lượng trà vừa đủ vào ấm và tiến hành tráng trà nhanh trong vài giây để đánh thức hương trà.\n\nTiếp theo, rót nước vào và ủ trong khoảng thời gian phù hợp (thường từ 20 giây đến vài phút tùy loại trà). Không nên ủ quá lâu vì sẽ làm trà bị đắng.\n\nCuối cùng, rót trà ra chén và thưởng thức. Một ấm trà ngon là sự kết hợp giữa nhiệt độ, thời gian và cảm nhận tinh tế của người pha.', '2026-03-17 13:35:50.000000', '/uploads/5dcce10e-f508-4fb1-949b-a6f2a3f3a7c7_huongdan.jpg', 'DRAFT', NULL, 'Cách pha trà ngon chuẩn vị – Bí quyết đơn giản tại nhà', 2, '2026-03-17 13:35:50.000000', NULL),
(3, 'Trà Thái Nguyên từ lâu đã nổi tiếng là một trong những loại trà ngon nhất Việt Nam. Vùng đất này có điều kiện khí hậu và thổ nhưỡng đặc biệt, tạo nên hương vị trà đặc trưng không nơi nào có được.\n\nNgười dân nơi đây đã gắn bó với nghề trồng trà qua nhiều thế hệ. Từng búp trà được hái bằng tay vào sáng sớm khi còn đọng sương, sau đó trải qua quá trình chế biến tỉ mỉ.\n\nKhông chỉ là một loại thức uống, trà còn là biểu tượng văn hóa của người Việt. Trong các dịp lễ, Tết hay tiếp khách, chén trà luôn là cầu nối thể hiện sự hiếu khách và trân trọng.\n\nNgày nay, trà Thái Nguyên không chỉ phục vụ trong nước mà còn được xuất khẩu ra nhiều quốc gia, góp phần đưa thương hiệu trà Việt vươn xa.', '2026-03-17 13:36:15.000000', '/uploads/502bc6b5-06f7-49fa-82e1-deea9e9090a4_cautruyentra.jpg', 'DRAFT', NULL, 'Hành trình của trà Thái Nguyên – Tinh hoa đất Việt', 3, '2026-03-17 13:36:15.000000', NULL),
(4, 'Trà đào cam sả là một trong những thức uống được yêu thích nhờ hương vị thơm ngon và khả năng giải nhiệt tuyệt vời.\n\nĐể làm món này, bạn cần chuẩn bị trà túi lọc hoặc trà đen, đào ngâm, cam tươi và sả.\n\nĐầu tiên, đun sôi nước và cho sả đã đập dập vào nấu khoảng 5 phút để lấy hương. Sau đó, cho trà vào hãm trong nước sả khoảng 3–5 phút rồi lọc bỏ bã.\n\nTiếp theo, thêm nước đào ngâm và một ít đường tùy khẩu vị. Khuấy đều và để nguội.\n\nCuối cùng, cho đá vào ly, thêm lát cam và miếng đào để trang trí. Rót trà vào và thưởng thức.\n\nThức uống này không chỉ ngon mà còn rất tốt cho sức khỏe nhờ các nguyên liệu tự nhiên.', NULL, '/uploads/0bded006-8ad9-44a4-ac93-845eb413c392_conghtuc1.jpg', 'DRAFT', NULL, 'Công thức trà đào cam sả mát lạnh giải nhiệt', 4, '2026-03-17 13:37:31.000000', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `blog_categories`
--

CREATE TABLE `blog_categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `blog_categories`
--

INSERT INTO `blog_categories` (`id`, `name`, `description`) VALUES
(1, 'Sức khỏe & Lợi ích', ''),
(2, 'Hướng dẫn pha trà ', ''),
(3, 'Câu truyện trà Việt', ''),
(4, 'Công thức trà ', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brewing_guides`
--

CREATE TABLE `brewing_guides` (
  `id` bigint(20) NOT NULL,
  `content` text DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brewing_guides`
--

INSERT INTO `brewing_guides` (`id`, `content`, `title`, `product_id`) VALUES
(1, 'Chuẩn bị:\n+ 5g trà\n+ 200ml nước\n+ Ấm trà / ly\n+ Nhiệt độ nước: 75–85°C\n\nCác bước thực hiện:\nBước 1: Làm nóng dụng cụ\n- Rót nước nóng vào ấm và chén\n- Lắc đều rồi đổ bỏ\n→ giúp giữ nhiệt tốt hơn khi pha\n\nBước 2: Cho trà vào ấm\n- Cho khoảng 5g trà vào ấm\n→ không nên cho quá nhiều vì dễ bị đắng\n\nBước 3: Tráng trà (đánh thức trà)\n- Rót một ít nước nóng (~80°C) vào\n- Lắc nhẹ 5 giây rồi đổ bỏ\n→ giúp trà sạch và dậy hương\n\nBước 4: Pha trà lần 1\n- Rót 200ml nước (80°C) vào ấm\n- Đậy nắp và ủ 20–30 giây\n\nBước 5: Rót trà ra chén\n- Rót hết nước trà ra để tránh bị đắng\n→ không để trà ngâm quá lâu\n\nBước 6: Pha các lần tiếp theo\n- Có thể pha 3–4 lần\n- Mỗi lần tăng thêm 10–15 giây', 'Hướng dẫn pha Trà xanh', 1),
(2, 'Chuẩn bị:\n+ 5g trà\n+ 200ml nước\n+ Ấm trà / ly\n+ Nhiệt độ nước: 75–85°C\n\nCác bước thực hiện:\nBước 1: Làm nóng dụng cụ\n- Rót nước nóng vào ấm và chén\n- Lắc đều rồi đổ bỏ\n→ giúp giữ nhiệt tốt hơn khi pha\n\nBước 2: Cho trà vào ấm\n- Cho khoảng 5g trà vào ấm\n→ không nên cho quá nhiều vì dễ bị đắng\n\nBước 3: Tráng trà (đánh thức trà)\n- Rót một ít nước nóng (~80°C) vào\n- Lắc nhẹ 5 giây rồi đổ bỏ\n→ giúp trà sạch và dậy hương\n\nBước 4: Pha trà lần 1\n- Rót 200ml nước (80°C) vào ấm\n- Đậy nắp và ủ 20–30 giây\n\nBước 5: Rót trà ra chén\n- Rót hết nước trà ra để tránh bị đắng\n→ không để trà ngâm quá lâu\n\nBước 6: Pha các lần tiếp theo\n- Có thể pha 3–4 lần\n- Mỗi lần tăng thêm 10–15 giây\n', 'Hướng dẫn pha Trà xanh', 3),
(3, 'Chuẩn bị:\n+ 5–7g trà\n+ 200ml nước\n+ Nhiệt độ: 85–95°C\n\nCác bước:\nBước 1: Làm nóng ấm\n- Tráng ấm và chén bằng nước nóng\n\nBước 2: Cho trà\n- Cho trà vào ấm (nhiều hơn trà xanh vì lá cuộn)\n\nBước 3: Tráng trà\n- Rót nước nóng vào và đổ ngay (5–10 giây)\n\nBước 4: Pha trà\n- Rót nước 90°C vào\n- Ủ 30–45 giây\n\nBước 5: Rót trà\n- Rót hết ra chén\n\nBước 6: Pha nhiều lần\n- Có thể pha 5–6 lần\n- Tăng thời gian mỗi lần\n', 'Hướng dẫn pha Trà Ô Long', 4),
(4, 'Chuẩn bị:\n+ 5–7g trà\n+ 200ml nước\n+ Nhiệt độ: 85–95°C\n\nCác bước:\nBước 1: Làm nóng ấm\n- Tráng ấm và chén bằng nước nóng\n\nBước 2: Cho trà\n- Cho trà vào ấm (nhiều hơn trà xanh vì lá cuộn)\n\nBước 3: Tráng trà\n- Rót nước nóng vào và đổ ngay (5–10 giây)\n\nBước 4: Pha trà\n- Rót nước 90°C vào\n- Ủ 30–45 giây\n\nBước 5: Rót trà\n- Rót hết ra chén\n\nBước 6: Pha nhiều lần\n- Có thể pha 5–6 lần\n- Tăng thời gian mỗi lần\n', 'Hướng dẫn pha Trà Ô Long', 5),
(5, 'Chuẩn bị:\n+ 5–7g trà\n+ 200ml nước\n+ Nhiệt độ: 100°C\n\nCác bước:\nBước 1: Đun nước sôi\n- Dùng nước 100°C\n\nBước 2: Cho trà\n- Không cần tráng trà\n\nBước 3: Pha trà\n- Rót nước sôi vào\n- Ủ 3–5 phút\n\nBước 4: Lọc và rót\n- Rót ra ly\n- Có thể lọc bã nếu cần\n\nBước 5: Thưởng thức\n- Có thể thêm:\n + Đường\n+ Sữa\n+ Chanh\n', 'Hướng dẫn pha Trà đen', 6),
(6, 'Chuẩn bị:\n+ 5–7g trà\n+ 200ml nước\n+ Nhiệt độ: 100°C\n\nCác bước:\nBước 1: Đun nước sôi\n- Dùng nước 100°C\n\nBước 2: Cho trà\n- Không cần tráng trà\n\nBước 3: Pha trà\n- Rót nước sôi vào\n- Ủ 3–5 phút\n\nBước 4: Lọc và rót\n- Rót ra ly\n- Có thể lọc bã nếu cần\n\nBước 5: Thưởng thức\n- Có thể thêm:\n + Đường\n+ Sữa\n+ Chanh\n', 'Hướng dẫn pha Trà đen', 7),
(7, 'Chuẩn bị:\n+ 4–5g trà\n+ 200ml nước\n+ Nhiệt độ: 75–85°C\n\nCác bước:\nBước 1: Làm nóng ấm\n -Tráng ấm\n\nBước 2: Cho trà\n- Cho trà vào ấm\n\nBước 3: Pha trà\n- Rót nước 80°C\n- Ủ 2–3 phút (lâu hơn trà xanh)\n\nBước 4: Rót trà\n- Rót ra chén\n\n', 'Hướng dẫn pha Trà trắng', 8),
(8, 'Chuẩn bị:\n+ 4–5g trà\n+ 200ml nước\n+ Nhiệt độ: 75–85°C\n\nCác bước:\nBước 1: Làm nóng ấm\n -Tráng ấm\n\nBước 2: Cho trà\n- Cho trà vào ấm\n\nBước 3: Pha trà\n- Rót nước 80°C\n- Ủ 2–3 phút (lâu hơn trà xanh)\n\nBước 4: Rót trà\n- Rót ra chén\n\n', 'Hướng dẫn pha Trà trắng', 9),
(9, 'Chuẩn bị:\n+ 3–5 bông hoa cúc\n+ 200ml nước\n\nCác bước:\nBước 1: Rửa nhẹ hoa\n- Tráng qua nước nóng nhanh\n\nBước 2: Hãm trà\n- Rót nước sôi vào\n\nBước 3: Ủ trà\n- Đợi 3–5 phút\n\nBước 4: Thưởng thức\n- Có thể thêm mật ong\n', 'Hướng dẫn pha Trà hoa cúc', 10),
(10, 'Chuẩn bị:\n+ 5g trà\n+ 200ml nước\n+ 85–90°C\n\nCác bước:\nBước 1: Tráng ấm\n- Làm nóng dụng cụ\n\nBước 2: Tráng trà nhanh\n- Rót nước rồi đổ ngay\n\nBước 3: Pha trà\n- Ủ 25–30 giây\n\nBước 4: Rót ra chén\n- Không ủ lâu → mất hương\n\n', 'Hướng dẫn pha Trà sen ', 12),
(11, 'Chuẩn bị:\n+ 5–10g atiso khô (hoa/lát)\n+ 300ml nước\n+ Nhiệt độ: 100°C\n+ Dụng cụ: ấm hoặc nồi nhỏ\n\nCác bước thực hiện:\nBước 1: Rửa nguyên liệu\n- Rửa nhanh atiso với nước sạch\n→ loại bỏ bụi bẩn\n\nBước 2: Đun nước\n- Đun sôi 300ml nước (100°C)\n\nBước 3: Hãm hoặc nấu trà\n- Có 2 cách:\n+ Cách 1 (hãm nhanh):\n- Cho atiso vào ấm\n- Rót nước sôi vào\n- Đậy nắp, ủ 5–10 phút\n+ Cách 2 (nấu):\n- Cho atiso vào nồi\n- Đun nhỏ lửa 10–15 phút\n→ nước đậm hơn, ngon hơn\n\nBước 4: Lọc trà\n- Lọc bỏ bã\n- Giữ lại nước trà\n\nBước 5: Thưởng thức\n- Có thể:\n + uống nóng\n + thêm đường/mật ong\n + uống lạnh\n________________________________________\nLưu ý:\n   - Không uống quá nhiều (dễ tụt huyết áp nhẹ)\n   - Phù hợp uống giải nhiệt, mát gan\n', 'Hướng dẫn pha Trà Atiso', 11),
(12, 'Chuẩn bị:\n+ 3–5 bông hoa hồng khô\n+ 200ml nước\n+ Nhiệt độ: 85–90°C\n\nCác bước thực hiện:\nBước 1: Tráng hoa\n- Rót nước nóng nhanh (3–5 giây) rồi đổ bỏ\n→ giúp sạch và giữ hương\n\nBước 2: Hãm trà\n- Rót 200ml nước nóng (90°C) vào\n\nBước 3: Ủ trà\n- Đậy nắp\n- Ủ trong 3–5 phút\n\nBước 4: Rót ra ly\n- Rót trà ra ly\n- Giữ nguyên hoa để trang trí (tùy)\n\nBước 5: Thưởng thức\n- Có thể thêm:\n   + mật ong\n   + táo đỏ\n   + kỷ tử\n________________________________________\nLưu ý:\n   - Không dùng nước 100°C → mất hương\n   - Trà nhẹ, phù hợp uống buổi tối\n\n', 'Hướng dẫn pha Trà Hoa Hồng', 13);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brewing_guide_products`
--

CREATE TABLE `brewing_guide_products` (
  `brewing_guide_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brewing_guide_products`
--

INSERT INTO `brewing_guide_products` (`brewing_guide_id`, `product_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `description`, `name`) VALUES
(1, NULL, 'Trà xanh'),
(2, NULL, 'Trà đen'),
(3, NULL, 'Trà ô long'),
(4, NULL, 'Trà trắng'),
(5, NULL, 'Trà thảo mộc'),
(6, NULL, 'Trà ướp hương');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `vnp_txn_ref` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `shipping_email` varchar(255) DEFAULT NULL,
  `shipping_fee` double DEFAULT NULL,
  `shipping_name` varchar(255) DEFAULT NULL,
  `shipping_phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `order_date`, `shipping_address`, `status`, `total_amount`, `user_id`, `vnp_txn_ref`, `payment_method`, `shipping_email`, `shipping_fee`, `shipping_name`, `shipping_phone`) VALUES
(1, '2026-03-18 05:28:15.000000', 'Khóm 6', 'Completed', 363000, 2, '05324065', NULL, NULL, NULL, NULL, NULL),
(2, '2026-03-18 05:31:43.000000', 'Khóm 6', 'Completed', 105000, 2, '15899881', NULL, NULL, NULL, NULL, NULL),
(3, '2026-03-18 06:02:12.000000', 'Khu phố 7, Thị trấn Hai Riêng, Huyện Sông Hinh, Tỉnh Phú Yên', 'Completed', 231000, 1, '76165102', 'vnpay', 'admin@example.com', 30000, 'Phạm Tùng Dương', '0123456789'),
(4, '2026-03-18 09:30:33.000000', 'Khu phố 7, Thị trấn Hai Riêng, Huyện Sông Hinh, Tỉnh Phú Yên', 'Completed', 589000, 1, '86054301', 'vnpay', 'admin@example.com', 55000, 'Phạm Tùng Dương', '0123456789');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(1, 333000, 1, 1, 11),
(2, 50000, 1, 2, 10),
(3, 201000, 1, 3, 12),
(4, 201000, 1, 4, 12),
(5, 333000, 1, 4, 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `description` text DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `description`, `image_url`, `name`, `price`, `stock`, `category_id`, `created_at`, `origin`, `updated_at`) VALUES
(1, 'Trà được hái từ búp non, hương cốm tự nhiên, vị chát nhẹ ban đầu và ngọt hậu sâu. Đây là dòng trà cao cấp, phù hợp thưởng trà truyền thống.', '/uploads/6e407f11-a8d8-442d-9947-9ccf4c291d43_NonTom1.jpg', 'Trà Xanh Nõn Tôm Thái Nguyên', 200000, 50, 1, NULL, 'Thái Nguyên, Việt Nam', '2026-03-17 07:29:16.000000'),
(3, 'Có hương thơm đậm hơn so với nõn tôm, vị chát rõ nhưng hậu ngọt kéo dài. Nước trà xanh sánh và đậm.', '/uploads/016887db-ab92-4dc7-bc33-133fb5dbe4c9_TanCuong2.jpg', 'Trà Xanh Tân Cương', 50000, 50, 1, '2026-03-17 09:01:01.000000', 'Trà Xanh Tân Cương', '2026-03-17 09:01:01.000000'),
(4, 'Bảo Lộc, Lâm Đồng', '/uploads/5ac5afb7-3ba8-45dd-a342-af78f045ee63_Olong2.jpg', 'Trà Ô Long Lâm Đồng', 185000, 50, 3, NULL, 'Trà Ô Long Lâm Đồng', '2026-03-17 09:04:15.000000'),
(5, 'Có thể thu hoạch quanh năm, hương hoa nhẹ, vị thanh và dịu.', '/uploads/24f206e5-79ba-4750-b90a-50fa284d3e7b_Tuquy1.jpg', 'Trà Ô Long Tứ Quý', 700000, 50, 3, '2026-03-17 09:04:06.000000', 'Trà Ô Long Tứ Quý', '2026-03-17 09:04:06.000000'),
(6, 'Vị đậm, màu đỏ hổ phách, thường dùng với sữa hoặc chanh.', '/uploads/58a95f65-d4b0-4a13-a594-9bd8afc4039b_Ceylon3.jpg', 'Trà Đen Ceylon', 555000, 50, 2, '2026-03-17 09:06:10.000000', 'Sri Lanka', '2026-03-17 09:06:10.000000'),
(7, 'Hương mạnh, vị đậm, rất phù hợp pha trà sữa.', '/uploads/6609670e-8cce-464f-a2c0-05733886c229_Asam2.jpg', 'Trà Đen Assam', 135000, 50, 2, '2026-03-17 09:07:33.000000', 'Ấn Độ', '2026-03-17 09:07:33.000000'),
(8, 'Hương nhẹ, vị thanh, rất ít chát, giàu chất chống oxy hóa.', '/uploads/5538a46a-1b95-4738-8dcb-ac1292cd7442_Bach3.jpg', 'Trà Trắng Bạch Hào', 80000, 50, 4, '2026-03-17 09:09:33.000000', 'Phúc Kiến, Trung Quốc', '2026-03-17 09:09:33.000000'),
(9, 'Chỉ dùng búp non, chất lượng cao, vị cực kỳ thanh và tinh tế.', '/uploads/d2c0ddd2-106f-4d8d-a608-e94d7e5c7e8d_Bac2.jpg', 'Trà Trắng Silver Needle', 32000, 50, 4, NULL, 'Trung Quốc', '2026-03-17 10:40:13.000000'),
(10, 'Giúp thư giãn, ngủ ngon, không chứa caffeine.', '/uploads/0c3f1dd2-48c1-4c95-9cf8-346fbafcfae5_Cuc1.jpg', 'Trà Hoa Cúc', 50000, 50, 5, '2026-03-17 09:13:19.000000', 'Việt Nam', '2026-03-17 09:13:19.000000'),
(11, 'Giúp mát gan, thanh lọc cơ thể, vị hơi chua nhẹ.', '/uploads/66a1e6b0-3800-4d82-8cff-23700878956f_Atiso2.jpg', 'Trà Hoa Atiso', 333000, 5, 5, NULL, 'Đà Lạt', '2026-03-17 09:30:39.000000'),
(12, 'Ướp từ hoa sen tự nhiên, hương thơm thanh khiết, sang trọng.', '/uploads/52def51b-f012-480d-abde-b40f70893d65_Tay1.jpg', 'Trà Sen Tây Hồ', 201000, 52, 6, '2026-03-17 09:18:34.000000', 'Hà Nội', '2026-03-17 09:18:34.000000'),
(13, 'Hương thơm dịu, giúp thư giãn và làm đẹp da.', '/uploads/a0328f6d-b3bc-4ba2-b80a-49a6451953d1_Hong1.jpg', 'Trà Hoa Hồng', 99900000, 0, 6, NULL, 'Trung Quốc', '2026-03-17 09:59:14.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_images`
--

CREATE TABLE `product_images` (
  `id` bigint(20) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_main` bit(1) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_images`
--

INSERT INTO `product_images` (`id`, `image_url`, `is_main`, `sort_order`, `product_id`) VALUES
(1, '/uploads/6e407f11-a8d8-442d-9947-9ccf4c291d43_NonTom1.jpg', b'1', 0, 1),
(2, '/uploads/b62ddbbf-0ea0-46c5-a057-1144faf1baed_NonTom2.jpg', b'0', 1, 1),
(3, '/uploads/e4dfbf06-fb88-4db4-8180-87886900fc68_NonTom3.jpg', b'0', 2, 1),
(7, '/uploads/342c6fe9-8d8d-4ec0-ad20-c49f568c05a7_TanCuong1.jpg', b'0', 0, 3),
(8, '/uploads/016887db-ab92-4dc7-bc33-133fb5dbe4c9_TanCuong2.jpg', b'1', 1, 3),
(9, '/uploads/cdebd326-8946-4e7a-a6e5-6581e4609a3a_TanCuong3.jpg', b'0', 2, 3),
(10, '/uploads/f15edbd2-ad79-45d9-8722-b478890f788a_Olong1.jpg', b'0', 0, 4),
(11, '/uploads/5ac5afb7-3ba8-45dd-a342-af78f045ee63_Olong2.jpg', b'1', 1, 4),
(12, '/uploads/a3497d2a-5e34-4274-80f1-e47c3f270f28_Olong3.jpg', b'0', 2, 4),
(13, '/uploads/24f206e5-79ba-4750-b90a-50fa284d3e7b_Tuquy1.jpg', b'1', 0, 5),
(14, '/uploads/4587c8eb-2ea9-466d-979c-b873859f231f_Tuquy2.jpg', b'0', 1, 5),
(15, '/uploads/720682d5-9090-48e1-83f4-d3358649da9a_Tuquy3.jpg', b'0', 2, 5),
(16, '/uploads/761832fb-cb86-4161-adaf-c934083f2aa5_Ceylon1.jpg', b'0', 0, 6),
(17, '/uploads/2bf0b7c6-91b6-46d6-ba5e-241d7f48d294_Ceylon2.jpg', b'0', 1, 6),
(18, '/uploads/58a95f65-d4b0-4a13-a594-9bd8afc4039b_Ceylon3.jpg', b'1', 2, 6),
(19, '/uploads/f14a05b9-b4b4-4cea-a705-4a211a6ea571_Asam1.jpg', b'0', 0, 7),
(20, '/uploads/6609670e-8cce-464f-a2c0-05733886c229_Asam2.jpg', b'1', 1, 7),
(21, '/uploads/5e091f8e-27ee-488b-8575-5a02a5d66041_Asam3.jpg', b'0', 2, 7),
(22, '/uploads/9c3b5fb0-4252-402f-b6c5-3905d4e653ce_Bach1.jpg', b'0', 0, 8),
(23, '/uploads/4c025b72-06cd-4cd9-bcc7-332d302869b7_Bach2.jpg', b'0', 1, 8),
(24, '/uploads/5538a46a-1b95-4738-8dcb-ac1292cd7442_Bach3.jpg', b'1', 2, 8),
(25, '/uploads/cf1dd3f6-1374-4090-b7e4-5a48c24f28c7_Bac1.jpg', b'0', 0, 9),
(26, '/uploads/d2c0ddd2-106f-4d8d-a608-e94d7e5c7e8d_Bac2.jpg', b'1', 1, 9),
(27, '/uploads/343e557c-f0d3-4a5d-94ab-136c6569eb04_Bac3.jpg', b'0', 2, 9),
(28, '/uploads/0c3f1dd2-48c1-4c95-9cf8-346fbafcfae5_Cuc1.jpg', b'1', 0, 10),
(29, '/uploads/73666881-7bd3-4f61-b3b3-5ab8fffa3b5f_Cuc2.jpg', b'0', 1, 10),
(30, '/uploads/146714f1-df2c-4550-8dca-4e5af39ebbb8_Cuc3.jpg', b'0', 2, 10),
(31, '/uploads/d3706822-05e6-4a4a-ba7b-7456c91a1fdb_Atiso1.jpg', b'0', 0, 11),
(32, '/uploads/66a1e6b0-3800-4d82-8cff-23700878956f_Atiso2.jpg', b'1', 1, 11),
(33, '/uploads/60f4b327-453e-4a43-87f2-ae74a7a2beca_Atiso3.jpg', b'0', 2, 11),
(34, '/uploads/52def51b-f012-480d-abde-b40f70893d65_Tay1.jpg', b'1', 0, 12),
(35, '/uploads/1d493729-ef47-4ddd-9521-d61718b96c70_Tay2.jpg', b'0', 1, 12),
(36, '/uploads/2c4b8c6d-603a-4cf6-8c0b-9e17c7d987d5_Tay3.jpg', b'0', 2, 12),
(37, '/uploads/a0328f6d-b3bc-4ba2-b80a-49a6451953d1_Hong1.jpg', b'1', 0, 13),
(38, '/uploads/e5af64f0-cd1b-4a49-892c-49ce6b454c68_Hong2.jpg', b'0', 1, 13),
(39, '/uploads/6dc2cdbf-f9ec-4610-bac5-dcb3699567ed_Hong3.jpg', b'0', 2, 13);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_weights`
--

CREATE TABLE `product_weights` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_weights`
--

INSERT INTO `product_weights` (`id`, `created_at`, `price`, `stock`, `weight`, `product_id`) VALUES
(1, NULL, 200000, 50, 100, 1),
(3, '2026-03-17 07:29:16.000000', 385000, 50, 200, 1),
(4, '2026-03-17 09:01:01.000000', 50000, 50, 100, 3),
(5, '2026-03-17 09:01:01.000000', 98000, 50, 200, 3),
(6, NULL, 185000, 50, 100, 4),
(7, NULL, 245000, 50, 200, 4),
(8, '2026-03-17 09:04:06.000000', 700000, 50, 100, 5),
(9, '2026-03-17 09:04:06.000000', 815000, 50, 200, 5),
(10, '2026-03-17 09:06:10.000000', 555000, 50, 100, 6),
(11, '2026-03-17 09:06:10.000000', 599999, 50, 200, 6),
(12, '2026-03-17 09:07:33.000000', 135000, 50, 100, 7),
(13, '2026-03-17 09:07:33.000000', 209000, 50, 200, 7),
(14, '2026-03-17 09:09:33.000000', 80000, 50, 100, 8),
(15, '2026-03-17 09:09:33.000000', 175000, 50, 200, 8),
(16, NULL, 32000, 50, 100, 9),
(17, NULL, 56000, 60, 200, 9),
(18, '2026-03-17 09:13:19.000000', 50000, 50, 100, 10),
(19, '2026-03-17 09:13:19.000000', 75000, 50, 200, 10),
(20, NULL, 333000, 5, 100, 11),
(21, NULL, 420000, 0, 200, 11),
(22, '2026-03-17 09:18:34.000000', 201000, 52, 100, 12),
(23, NULL, 99900000, 0, 200, 13);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `comment` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`id`, `comment`, `created_at`, `rating`, `product_id`, `user_id`) VALUES
(1, 'ngon', '2026-03-17 09:53:30.000000', 4, 12, 1),
(3, 'ooo', '2026-03-17 10:45:41.000000', 5, 12, 2),
(4, '1', '2026-03-17 10:50:40.000000', 5, 12, 1),
(5, '2', '2026-03-17 10:50:44.000000', 2, 12, 1),
(6, '3', '2026-03-17 10:50:47.000000', 3, 12, 1),
(7, '4', '2026-03-17 10:50:51.000000', 4, 12, 1),
(8, 'ppp', '2026-03-18 09:29:14.000000', 4, 12, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `address`, `email`, `full_name`, `password`, `profile_image`, `district`, `phone`, `province`, `ward`) VALUES
(1, 'Khu phố 7', 'admin@example.com', 'Phạm Tùng Dương', '147258', '', 'Huyện Sông Hinh', '0123456789', 'Tỉnh Phú Yên', 'Thị trấn Hai Riêng'),
(2, 'Khóm 6', 'user@example.com', 'Phạm Tùng Dương1', '123456', NULL, 'Huyện Lạng Giang', '147258369', 'Tỉnh Bắc Giang', 'Xã Xương Lâm');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf2ci0ovwtuw6nsmcbvl20ucxv` (`category_id`),
  ADD KEY `FKt8g0udj2fq40771g38t2t011n` (`author_id`);

--
-- Chỉ mục cho bảng `blog_categories`
--
ALTER TABLE `blog_categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `brewing_guides`
--
ALTER TABLE `brewing_guides`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5d940s1qw8jlk6xa2aip2280d` (`product_id`);

--
-- Chỉ mục cho bảng `brewing_guide_products`
--
ALTER TABLE `brewing_guide_products`
  ADD KEY `FKtdk6l5n2fxy9v24buf2nedwe6` (`product_id`),
  ADD KEY `FKhn0kk51w31sjhbuxn0bsn9q7k` (`brewing_guide_id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  ADD KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- Chỉ mục cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`);

--
-- Chỉ mục cho bảng `product_weights`
--
ALTER TABLE `product_weights`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4y445xj9o161pph2q98gsmi3u` (`product_id`);

--
-- Chỉ mục cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`),
  ADD KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Chỉ mục cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `blog_categories`
--
ALTER TABLE `blog_categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `brewing_guides`
--
ALTER TABLE `brewing_guides`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `product_weights`
--
ALTER TABLE `product_weights`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `blogs`
--
ALTER TABLE `blogs`
  ADD CONSTRAINT `FKf2ci0ovwtuw6nsmcbvl20ucxv` FOREIGN KEY (`category_id`) REFERENCES `blog_categories` (`id`),
  ADD CONSTRAINT `FKt8g0udj2fq40771g38t2t011n` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `brewing_guides`
--
ALTER TABLE `brewing_guides`
  ADD CONSTRAINT `FK5d940s1qw8jlk6xa2aip2280d` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `brewing_guide_products`
--
ALTER TABLE `brewing_guide_products`
  ADD CONSTRAINT `FKhn0kk51w31sjhbuxn0bsn9q7k` FOREIGN KEY (`brewing_guide_id`) REFERENCES `brewing_guides` (`id`),
  ADD CONSTRAINT `FKtdk6l5n2fxy9v24buf2nedwe6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `product_weights`
--
ALTER TABLE `product_weights`
  ADD CONSTRAINT `FK4y445xj9o161pph2q98gsmi3u` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
