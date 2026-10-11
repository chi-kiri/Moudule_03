# Thiết kế RESTful API quản lý Task và User

## 1. Lấy toàn bộ danh sách

### Lấy danh sách công việc
- Method: `GET`
- Endpoint: `/api/tasks`

### Lấy danh sách người dùng
- Method: `GET`
- Endpoint: `/api/users`

## 2. Tạo mới

### Tạo công việc
- Method: `POST`
- Endpoint: `/api/tasks`
- Body:

```json
{
  "title": "Làm báo cáo",
  "status": "pending",
  "priority": "high",
  "userId": 1
}
```

### Tạo người dùng
- Method: `POST`
- Endpoint: `/api/users`
- Body:

```json
{
  "name": "Nguyen Van A",
  "role": "member"
}
```

## 3. Cập nhật

### Cập nhật trạng thái công việc
- Method: `PATCH`
- Endpoint: `/api/tasks/{id}/status`
- Body:

```json
{
  "status": "completed"
}
```

### Cập nhật vai trò người dùng
- Method: `PATCH`
- Endpoint: `/api/users/{id}/role`
- Body:

```json
{
  "role": "admin"
}
```

## 4. Xóa

### Xóa công việc
- Method: `DELETE`
- Endpoint: `/api/tasks/{id}`

### Xóa người dùng
- Method: `DELETE`
- Endpoint: `/api/users/{id}`

## 5. Tìm công việc có độ ưu tiên high

- Method: `GET`
- Endpoint: `/api/tasks?priority=high`

## 6. Tìm công việc priority high của user id = 1

- Method: `GET`
- Endpoint: `/api/tasks?priority=high&userId=1`

## 7. Liệt kê toàn bộ công việc của một người dùng

- Method: `GET`
- Endpoint: `/api/users/{userId}/tasks`

## 8. Gắn công việc cho người dùng

- Method: `PATCH`
- Endpoint: `/api/tasks/{taskId}/assignee`
- Body:

```json
{
  "userId": 1
}
```

## Validation cơ bản

- `title` không được để trống.
- `priority` chỉ nhận các giá trị hợp lệ như `low`, `medium`, `high`.
- `status` chỉ nhận các trạng thái hợp lệ.
- `userId` phải tồn tại trước khi gắn vào Task.
- `role` phải là vai trò hợp lệ của hệ thống.
