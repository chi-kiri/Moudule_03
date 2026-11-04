# Bài tập 3 - Xử lý tài nguyên phức tạp

## 1. Mục tiêu

Thiết kế các Endpoint RESTful thể hiện quan hệ Parent-Child giữa các tài nguyên trong hệ thống quản lý bệnh viện

## 2. Danh sách API

### 1. Lấy tất cả lịch hẹn của một bác sĩ cụ thể

`GET /doctors/{doctorId}/appointments`

Trong quan hệ này `doctors` là tài nguyên cha, `appointments` là tài nguyên con, Endpoint trên lấy toàn bộ lịch hẹn thuộc về một bác sĩ cụ thể

### 2. Lấy một đơn thuốc cụ thể của một bệnh nhân cụ thể

`GET /patients/{patientId}/prescriptions/{prescriptionId}`

Trong quan hệ này `patients` là tài nguyên cha, `prescriptions` là tài nguyên con, `patientId` xác định bệnh nhân và `prescriptionId` xác định đơn thuốc cần lấy

### 3. Thêm một đơn thuốc mới cho bệnh nhân

`POST /patients/{patientId}/prescriptions`

Dùng POST trên tài nguyên con `prescriptions` để tạo một đơn thuốc mới thuộc về bệnh nhân được xác định bởi `patientId`

## 3. Kết luận

Các Endpoint sử dụng đúng cấu trúc `/resource/{id}/sub-resource`, qua đó thể hiện rõ quan hệ giữa tài nguyên cha và tài nguyên con trong hệ thống
