# 🧠 Actividad Sumativa 3 - Semana 8 - Desarrollo Orientado a Objetos II


👤 Autor del proyecto

Nombre completo: Daniel Francisco Caballero Salas

Sección: Programación Orientada a Objetos II

Carrera: Analista Programador Computacional

Sede: Campus Virtual


📘 Descripción general del sistema

Este proyecto implementa una interfaz gráfica en Java Swing para gestionar pedidos dentro de la aplicación SpeedFast. La interfaz permite:

-Registrar pedidos ingresando dirección y tipo de pedido (el ID es autoincremental en la BD).

-Listar pedidos en una tabla con columnas ordenadas (ID, Dirección, Tipo, Estado).

-Refrescar la lista para visualizar los pedidos almacenados.

-Asignar repartidores a pedidos pendientes e iniciar entregas.

-Actualizar las tablas de pedidos y repartidores en tiempo real mediante un botón de actualización.

Consideraciones al registrar pedidos

-El ID no se ingresa manualmente, se genera automáticamente en la base de datos.

-La dirección y el tipo de pedido son obligatorios.

-El estado inicial de un pedido es PENDIENTE.

-Las ventanas se centran en la pantalla al abrirse para mejorar la experiencia de usuario.


🧱 Estructura general del proyecto

El proyecto está organizado en paquetes siguiendo la convención de dominio invertido (cl.speedfast), lo que facilita la escalabilidad y la claridad del código.

```
src/
├── 📂 data/
│   └── RegistroPedido.java       # Contenedor de pedidos, con métodos para agregar y obtener lista.
│
├── 📂 dao/
│   ├── PedidoDAO.java            # Acceso a datos de pedidos (INSERT, UPDATE, SELECT).
│   ├── RepartidorDAO.java        # Acceso a datos de repartidores.
│   └── EntregaDAO.java           # Acceso a datos de entregas.
│
├── 📂 controller/
│   └── PedidoController.java     # Coordina la lógica entre DAO y las ventanas (view).
│
├── 📂 main/
│   └── Main.java                 # Punto de entrada de la aplicación. Abre la VentanaPrincipal.
│
├── 📂 model/
│   ├── Pedido.java               # Clase que representa un pedido (id autoincremental, dirección, tipo, estado).
│   ├── Repartidor.java           # Clase que representa un repartidor.
│   └── Entrega.java              # Clase que representa una entrega (pedido + repartidor + fecha/hora).
│
├── 📂 view/
│   ├── VentanaPrincipal.java     # Menú principal con botones para registrar, listar y asignar entregas.
│   ├── VentanaRegistroPedido.java# Formulario para ingresar un nuevo pedido (sin ID manual).
│   ├── VentanaListaPedidos.java  # Tabla que muestra los pedidos registrados con opción de refrescar.
│   └── VentanaAsignarEntrega.java# Ventana para asignar repartidores y actualizar tablas.

```


⚙️ Instrucciones para clonar y ejecutar el proyecto
Clonar el repositorio desde GitHub:

Opcion 1:
 git clone https://github.com/DCaballero1164/POO2_S3_S8.git

Opcion2:
 Archivo .jar en carpeta en main de GitHub -> Ruta: out/artifacts/Semana_8_jar/Semana_8.jar

Abrir el proyecto en IntelliJ IDEA (utilizar JDK 17 o superior).

Ejecutar el archivo Main.java desde el package ui.

Visualizar los resultados en la consola.

📌 Repositorio GitHub: https://github.com/DCaballero1164/POO2_S3_S8.git 📅 Fecha de entrega: [01/03/2026]