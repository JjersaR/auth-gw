
# API de Autenticación y Usuarios

## 1. Autenticación de Usuario

### `POST /api/auth/login`

Este endpoint permite autenticar a un usuario y obtener un token JWT.

- **URL:** `http://localhost:8080/api/auth/login`
- **Método:** `POST`
- **Content-Type:** `application/json`

#### Cuerpo de la solicitud:

```json
{
  "username": "jersa",
  "password": "JjersaR5649"
}
````

#### Respuesta exitosa:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

El token retornado debe ser usado como `Bearer Token` para autenticar futuras peticiones.

---

## 2. Obtener información del usuario

### `GET /api/user`

Este endpoint retorna información del usuario autenticado.

* **URL:** `http://localhost:8080/api/user`
* **Método:** `GET`
* **Autenticación:** `Bearer Token` (JWT obtenido en `/api/auth/login`)

#### Ejemplo de encabezado de autorización:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Respuesta exitosa:

```json
{
  "userId": "jersa",
  "roles": ["ROLE_USER"]
}
```
