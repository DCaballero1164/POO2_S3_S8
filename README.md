# 🧠 Actividad Sumativa 3 - Semana 8 - Desarrollo Orientado a Objetos II


👤 Autor del proyecto

Nombre completo: Daniel Francisco Caballero Salas

Sección: Programación Orientada a Objetos II

Carrera: Analista Programador Computacional

Sede: Campus Virtual


📘 Descripción general del sistema

SpeedFast es una aplicación de escritorio desarrollada en Java Swing que permite gestionar pedidos y entregas de manera sencilla y sincronizada. El sistema está diseñado para simular el flujo de trabajo de una empresa de reparto, integrando las siguientes funcionalidades:

- Registro de pedidos: el usuario ingresa dirección y tipo de pedido. El ID se genera automáticamente en la base de datos y el estado inicial es PENDIENTE.

- Listado de pedidos: se muestran todos los pedidos en una tabla con columnas ordenadas (ID, Dirección, Tipo, Estado). Incluye opciones para refrescar y eliminar pedidos.

- Asignación de entregas: permite seleccionar un pedido pendiente y asignarle un repartidor disponible. Al hacerlo, el estado del pedido cambia automáticamente a EN_REPARTO.

- Gestión de repartidores: se pueden agregar o eliminar repartidores desde la interfaz.

- Historial de entregas: se mantiene un registro con dirección, tipo, estado, fecha, hora y repartidor asignado.

- Sincronización en tiempo real: todas las ventanas se actualizan automáticamente al registrar, asignar o eliminar pedidos, evitando duplicados y manteniendo consistencia.

- El sistema sigue una arquitectura modular con separación clara entre modelo, DAO, controlador y vista, lo que facilita la escalabilidad y el mantenimiento.

Consideraciones al registrar pedidos:

- El ID no se ingresa manualmente, se genera automáticamente en la base de datos.

- La dirección y el tipo de pedido son obligatorios.

- El estado inicial de un pedido es PENDIENTE.

- Al asignar un repartidor, el estado cambia automáticamente a EN_REPARTO.

- Las ventanas se centran en la pantalla al abrirse para mejorar la experiencia de usuario.

- Se evita la duplicación de ventanas: cada módulo mantiene una instancia única.


🧱 Estructura general del proyecto

El proyecto está organizado en paquetes siguiendo la convención de dominio invertido (cl.speedfast), lo que facilita la escalabilidad y la claridad del código.

```
cl.speedfast/
├── 📂 dao/
│   ├── ConexionDB.java           # Clase para gestionar la conexión a la base de datos.
│   ├── PedidoDAO.java            # Interfaz para operaciones CRUD sobre pedidos.
│   ├── RepartidorDAO.java        # Interfaz para operaciones CRUD sobre repartidores.
│   ├── EntregaDAO.java           # Interfaz para operaciones CRUD sobre entregas.
│   ├── PedidoDAOImpl.java        # Implementación de PedidoDAO con lógica SQL.
│   ├── RepartidorDAOImpl.java    # Implementación de RepartidorDAO con lógica SQL.
│   └── EntregaDAOImpl.java       # Implementación de EntregaDAO con lógica SQL.
│
├── 📂 controller/
│   └── Controlador.java          # Coordina la lógica entre DAOs y las ventanas (view).
│
├── 📂 main/
│   └── Main.java                 # Punto de entrada de la aplicación. Inicializa VentanaPrincipal.
│
├── 📂 model/
│   ├── Pedido.java               # Clase que representa un pedido (id, dirección, tipo, estado).
│   ├── Repartidor.java           # Clase que representa un repartidor (id, nombre).
│   ├── Entrega.java              # Clase que representa una entrega (pedido + repartidor + fecha/hora).
│   ├── EstadoPedido.java         # Enum con estados: PENDIENTE, EN_REPARTO, ENTREGADO.
│   └── TipoPedido.java           # Enum con tipos de pedido (COMIDA, EXPRESS, ENCOMIENDA, etc.).
│
├── 📂 view/
│   ├── 📂 VentanaPrincipal/
│   │   ├── VentanaPrincipal.java           # Menú principal con botones para registrar, listar y asignar entregas.
│   │   └── VentanaPrincipal.form           # Archivo de diseño gráfico de la ventana principal.
│   │
│   ├── 📂 VentanaRegistroPedido/
│   │   ├── VentanaRegistroPedido.java      # Formulario para ingresar un nuevo pedido.
│   │   └── VentanaRegistroPedido.form      # Archivo de diseño gráfico del formulario de registro.
│   │
│   ├── 📂 VentanaListaPedidos/
│   │   ├── VentanaListaPedidos.java        # Tabla que muestra los pedidos registrados, con refresco y eliminación.
│   │   └── VentanaListaPedidos.form        # Archivo de diseño gráfico de la ventana de lista de pedidos.
│   │
│   ├── 📂 VentanaAsignarEntrega/
│   │   ├── VentanaAsignarEntrega.java      # Ventana para asignar repartidores, iniciar entregas y ver historial.
│   │   └── VentanaAsignarEntrega.form      # Archivo de diseño gráfico de la ventana de asignación de entregas.
│   │
│   └── 📂 VentanaAgregarRepartidor/
│       ├── VentanaAgregarRepartidor.java   # Formulario para agregar repartidores.
│       └── VentanaAgregarRepartidor.form   # Archivo de diseño gráfico del formulario de repartidores.
│
└── 📂 META-INF/                  # Configuración adicional del proyecto.

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