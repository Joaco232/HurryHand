# HurryHand 🚀

Plataforma para la contratación de servicios profesionales desarrollada con Spring Boot y React.

## 🛠️ Tecnologías

### Backend
- **Spring Boot 3.5.5** con Java 21
- **Spring Security** con JWT
- **H2 Database** (desarrollo) / **PostgreSQL** (producción)
- **MinIO** para almacenamiento de archivos
- **Maven** como build tool
- **Docker** para containerización

### Frontend
- **React 19** con **Vite**
- **React Router** para navegación
- **Axios** para HTTP requests
- **Framer Motion** para animaciones
- **React Query** para gestión de estado

## 🚀 Inicio Rápido

### Desarrollo Local

#### Opción 1: Script automático (recomendado)
```bash
# Windows
start-dev.bat

# Linux/Mac
chmod +x start-dev.sh
./start-dev.sh
```

#### Opción 2: Manual

1. **Iniciar MinIO** (almacenamiento de archivos):
```bash
docker run -d \
  --name hurryhand-minio-dev \
  -p 9000:9000 \
  -p 9001:9001 \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin123" \
  minio/minio server /data --console-address ":9001"
```

2. **Iniciar Backend**:
```bash
cd backend
mvn clean spring-boot:run -Dspring-boot.run.profiles=dev
```

3. **Iniciar Frontend** (en tu directorio del frontend):
```bash
cd frontend
npm install
npm run dev
```

### Producción con Docker Compose

```bash
docker-compose up --build
```

## 🌐 URLs de Desarrollo

- **Backend API**: http://localhost:8080
- **Frontend**: http://localhost:5173  
- **MinIO Console**: http://localhost:9001
- **H2 Database Console**: http://localhost:8080/h2-console

### Credenciales por defecto:
- **MinIO**: `minioadmin` / `minioadmin123`
- **H2**: `sa` / `password`

## 📡 API Endpoints

### Autenticación
```
POST /auth/register         - Registro de usuario
POST /auth/login           - Login (devuelve JWT token)
POST /auth/send-code       - Enviar código de verificación
```

### Service Posts
```
GET  /api/service-post/all     - Listar service posts (público)
POST /api/service-post         - Crear service post (requiere PROVIDER)
POST /api/service-post/photos  - Subir fotos
```

### Usuarios
```
GET  /api/users/{id}                  - Obtener usuario por ID
POST /api/users/set-provider/{id}     - Convertir usuario en provider
```

## 🔧 Configuración

### Variables de Entorno (Producción)

```env
# Base de datos
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/hurryhand
SPRING_DATASOURCE_USERNAME=hurryhand_user
SPRING_DATASOURCE_PASSWORD=hurryhand_pass

# MinIO
MINIO_ENDPOINT=http://minio:9000
MINIO_ACCESS_KEY=minioadmin
MINIO_SECRET_KEY=minioadmin123

# JWT
JWT_SECRET=your-super-secret-jwt-key-change-this-in-production
JWT_EXPIRATION=86400000

# Logging
LOGGING_LEVEL_COM_HURRYHAND_BACKEND=DEBUG
```

### Perfiles de Spring

- **dev**: H2 + logs detallados + CORS habilitado
- **prod**: PostgreSQL + logs mínimos + configuración para contenedores

## 🐛 Debugging

### Logs del Backend
```bash
# Ver logs del contenedor
docker logs hurryhand-backend -f

# Logs en desarrollo
tail -f backend/logs/application.log
```

### Problemas Comunes

#### ❌ Error 500 al crear service posts
- **Causa**: Token JWT expirado o rol incorrecto
- **Solución**: Hacer login nuevamente y verificar que el usuario tenga rol PROVIDER

#### ❌ CORS errors en frontend
- **Causa**: Configuración incorrecta de CORS
- **Solución**: Verificar que el frontend esté en puerto 5173 y que el backend tenga CORS configurado

#### ❌ MinIO connection failed
- **Causa**: MinIO no está corriendo o mal configurado
- **Solución**: Verificar que MinIO esté corriendo en puerto 9000

## 🔑 Flujo de Autenticación

1. **Registro**: `POST /auth/register`
2. **Login**: `POST /auth/login` → recibe JWT token
3. **Convertir en Provider**: `POST /api/users/set-provider/{id}`
4. **Crear Service Post**: `POST /api/service-post` (requiere Bearer token)

### Ejemplo de uso:

```bash
# 1. Registrar usuario
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","password":"test123","firstName":"Test","lastName":"User"}'

# 2. Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","password":"test123"}'

# 3. Usar token en requests
curl -X POST http://localhost:8080/api/service-post \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"title":"Test Service","description":"Test","price":100,"durationInMinutes":60}'
```

## 🛡️ Seguridad

- JWT tokens con expiración de 24 horas
- Passwords hasheados con BCrypt
- CORS configurado para desarrollo y producción
- Validación de roles para endpoints protegidos

## 📝 Correcciones Necesarias en Frontend

Ver archivo `FRONTEND_FIXES.md` para detalles sobre:
- Endpoints incorrectos (`/api/servicepost` → `/api/service-post`)
- Configuración de autenticación
- Estructura de datos esperada por el backend

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo `LICENSE` para detalles.
Proyecto final Ingenieria de Software 
