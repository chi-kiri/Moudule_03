# Bài tập 4 - Định dạng dữ liệu trả về

## 1. Mục tiêu

Xây dựng cấu trúc Response thống nhất cho REST API bằng lớp generic `ApiResponse<T>`

## 2. Cấu trúc Response

Response gồm các trường:

- `status`: trạng thái xử lý, ví dụ `success` hoặc `error`
- `code`: HTTP Status Code
- `data`: dữ liệu trả về
- `meta`: thông tin phân trang

## 3. Mẫu JSON - Lấy danh sách bác sĩ

```json
{
  "status": "success",
  "code": 200,
  "data": [
    {
      "id": 1,
      "name": "Nguyen Van A",
      "specialty": "Cardiology"
    },
    {
      "id": 2,
      "name": "Tran Van B",
      "specialty": "Neurology"
    }
  ],
  "meta": {
    "totalRecords": 2,
    "currentPage": 1
  }
}
```

## 4. Kết luận

Sử dụng `ApiResponse<T>` giúp các API trả về cùng một cấu trúc, Frontend có thể xử lý dữ liệu dễ dàng và nhất quán hơn
