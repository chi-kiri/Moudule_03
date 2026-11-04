# Bài tập 2 - Chuẩn hóa mã lỗi HTTP

## 1. Mục tiêu

Xác định HTTP Status Code phù hợp cho từng tình huống trong hệ thống y tế và giải thích lý do lựa chọn

## 2. Các trường hợp

### TH1 - Bỏ trống tên bệnh nhân khi đặt lịch khám

HTTP Status: `400 Bad Request`

Lý do: Tên bệnh nhân là dữ liệu bắt buộc nhưng người dùng không gửi lên, đây là lỗi dữ liệu đầu vào từ phía Client nên sử dụng mã 400

### TH2 - Không tìm thấy hồ sơ bệnh án có ID 999

HTTP Status: `404 Not Found`

Lý do: Yêu cầu hợp lệ nhưng tài nguyên có ID 999 không tồn tại trong Database nên sử dụng mã 404

### TH3 - Database MySQL bị sập

HTTP Status: `500 Internal Server Error`

Lý do: Server không thể xử lý yêu cầu do lỗi xảy ra bên trong hệ thống khi kết nối hoặc truy vấn Database, vì vậy sử dụng mã 500

### TH4 - Tuổi bệnh nhân là -5

HTTP Status: `400 Bad Request`

Lý do: Giá trị tuổi -5 không hợp lệ về mặt logic, đây là dữ liệu đầu vào không hợp lệ từ phía Client nên sử dụng mã 400

## 3. Tổng kết

| Tình huống | HTTP Status |
|---|---|
| Thiếu tên bệnh nhân | 400 Bad Request |
| Không tìm thấy hồ sơ bệnh án | 404 Not Found |
| Database bị lỗi | 500 Internal Server Error |
| Tuổi không hợp lệ | 400 Bad Request |
