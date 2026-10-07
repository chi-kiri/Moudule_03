# Phân tích mô hình Client - Server cho ứng dụng Quản lý sản phẩm

Khi người dùng muốn xem danh sách sản phẩm, hệ thống hoạt động theo mô hình Client - Server như sau:

- **Client:** Trình duyệt gửi yêu cầu đến Server bằng phương thức `GET`, ví dụ: `GET /products`.
- **Server:** Ứng dụng Spring Boot nhận request, xử lý yêu cầu và lấy danh sách sản phẩm.
- **Database:** Có nhiệm vụ lưu trữ dữ liệu sản phẩm. Trong bài này có thể tạm thời giả lập bằng `List` trong RAM thay vì dùng cơ sở dữ liệu thật.
- **Response:** Sau khi lấy được dữ liệu, Server trả kết quả về Client dưới dạng `JSON` cùng trạng thái `200 OK`.

Ví dụ dữ liệu trả về:

```json
[
  {
    "id": 1,
    "name": "Laptop",
    "price": 15000000
  },
  {
    "id": 2,
    "name": "Mouse",
    "price": 300000
  }
]
```

Luồng xử lý:

`Client → GET /products → Spring Boot Server → Database/RAM → JSON Response → Client`

Như vậy, Client có nhiệm vụ gửi yêu cầu và hiển thị dữ liệu, còn Server chịu trách nhiệm xử lý logic và lấy dữ liệu để trả về cho Client.
