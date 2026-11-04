# Bài tập 1 - Thiết kế tài nguyên theo Naming Convention

## 1. Mục tiêu

Thiết kế các Endpoint RESTful cho tài nguyên bác sĩ trong hệ thống quản lý bệnh viện, sử dụng đúng HTTP Method, danh từ số nhiều và không dùng động từ trong URL

## 2. Danh sách API

### 1. Lấy danh sách toàn bộ bác sĩ

`GET /doctors`

Dùng phương thức GET để lấy danh sách tài nguyên bác sĩ

### 2. Lấy thông tin chi tiết một bác sĩ theo ID

`GET /doctors/{id}`

Dùng ID trên URL để xác định bác sĩ cần lấy thông tin

### 3. Thêm mới một bác sĩ

`POST /doctors`

Dùng phương thức POST để tạo mới một tài nguyên bác sĩ

### 4. Cập nhật thông tin bác sĩ

`PUT /doctors/{id}`

Dùng phương thức PUT để cập nhật thông tin bác sĩ theo ID

### 5. Xóa bác sĩ khỏi hệ thống

`DELETE /doctors/{id}`

Dùng phương thức DELETE để xóa bác sĩ theo ID

## 3. Kết luận

Các Endpoint đều sử dụng danh từ số nhiều `doctors`, không chứa động từ như `get`, `create`, `update` hoặc `delete` trong URL, hành động được thể hiện thông qua HTTP Method
