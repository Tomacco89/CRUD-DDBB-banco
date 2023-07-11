package banco_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class main_gestor {
    private Connection conexion;
    private Scanner scanner;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/banco";
        String usuario = "banco";
        String contraseña = "banco";
        conexion = DriverManager.getConnection(url, usuario, contraseña);
        
     // Muestra información del tipo de sistema de base de datos.
        System.out.println("Base de datos => " + ANSI_YELLOW +
        conexion.getMetaData().getDatabaseProductName() + ANSI_RESET);
        
     // Muestra información sobre la versión del sistema de base de datos.
        System.out.println("Version => " + ANSI_YELLOW +
        conexion.getMetaData().getDatabaseProductVersion() + ANSI_RESET);
        
     // Muestra información del diver MySQL.
        System.out.println("Driver => " + ANSI_YELLOW + conexion.getMetaData().getDriverName() + ANSI_RESET);
        
     // Muestra información de la versión del driver MySQL.
        System.out.println("Version del driver => " + ANSI_YELLOW +
        		conexion.getMetaData().getDriverVersion() + ANSI_RESET + "\n");
        
        System.out.println(ANSI_GREEN + "Conexion establecida con la base de datos \n" + ANSI_RESET);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
            System.out.println(ANSI_RED + "Conexion cerrada" + ANSI_RESET);
        }
    }

    public void ejecutar() {
        scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada.

            switch (opcion) {
                case 1:
                    crearGestor();
                    break;
                case 2:
                    obtenerGestorPorId();
                    break;
                case 3:
                    obtenerTodosLosGestores();
                    break;
                case 4:
                    actualizarGestor();
                    break;
                case 5:
                    eliminarGestor();
                    break;
                case 6:
                    crearCliente();
                    break;
                case 7:
                    obtenerClientePorId();
                    break;
                case 8:
                    obtenerTodosLosClientes();
                    break;
                case 9:
                    actualizarCliente();
                    break;
                case 10:
                    eliminarCliente();
                    break;
                case 11:
                    crearMensaje();
                    break;
                case 12:
                    obtenerMensajePorId();
                    break;
                case 13:
                    obtenerTodosLosMensajes();
                    break;
                case 14:
                    actualizarMensaje();
                    break;
                case 15:
                    eliminarMensaje();
                    break;
                case 16:
                    enviarTransferencia();
                    break;
                case 17:
                    obtenerTransferenciaPorId();
                    break;
                case 18:
                    obtenerTodasLasTransferencias();
                    break;
                case 19:
                    actualizarTransferencia();
                    break;
                case 20:
                    eliminarTransferencia();
                    break;
                case 0:
                    System.out.println("Saliendo del programa... \n");
                    break;
                default:
                    System.out.println(ANSI_RED + "Opción inválida. Por favor, ingrese una opción válida" + ANSI_RESET + "\n");
                    break;
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println(ANSI_CYAN + "======== Menu de opciones ========" + ANSI_RESET);
        System.out.println("1 => Crear un Gestor");
        System.out.println("2 => Obtener un Gestor por su ID");
        System.out.println("3 => Obtener todos los Gestores");
        System.out.println("4 => Actualizar un Gestor");
        System.out.println("5 => Eliminar un Gestor por su ID");
        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
        System.out.println("6 => Crear un Cliente");
        System.out.println("7 => Obtener un Cliente por su ID");
        System.out.println("8 => Obtener todos los Clientes");
        System.out.println("9 => Actualizar un Cliente");
        System.out.println("10 => Eliminar un Cliente por su ID");
        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
        System.out.println("11 => Crear un Mensaje");
        System.out.println("12 => Obtener un Mensaje por su ID");
        System.out.println("13 => Obtener todos los Mensajes");
        System.out.println("14 => Actualizar un Mensaje");
        System.out.println("15 => Eliminar un Mensaje un por su ID");
        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
        System.out.println("16 => Enviar una Transferencia");
        System.out.println("17 => Obtener una Transferencia por su ID");
        System.out.println("18 => Obtener todas las Transferencias");
        System.out.println("19 => Actualizar datos de una Transferencia");
        System.out.println("20 => Eliminar datos de una Trasnferencia un por su ID");
        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
        System.out.println("0 => Salir");
        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
        System.out.print("Seleccione una opcion => ");
    }

    // Creación de un Gestor.
    private void crearGestor() {
        System.out.print("Ingrese el ID del Gestor => ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el Nombre del Gestor => ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la Password del Gestor => ");
        String contraseña = scanner.nextLine();
        System.out.print("Ingrese el Correo del Gestor => ");
        String correo = scanner.nextLine();

        try {
            String create = "INSERT INTO gestor (id, usuario, password, correo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(create)) {
                statement.setString(1, id);
                statement.setString(2, usuario);
                statement.setString(3, contraseña);
                statement.setString(4, correo);
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Gestor ha sido creado exitosamente ===" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al crear el Gestor (Verifique la informacion ingresada)" + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Obtención de un Gestor por ID.
    private void obtenerGestorPorId() {
        System.out.print("Ingrese el ID del Gestor => ");
        int getting = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String getter = "SELECT * FROM gestor WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(getter)) {
                statement.setInt(1, getting);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String usuario = resultSet.getString("usuario");
                        String contraseña = resultSet.getString("password");
                        String correo = resultSet.getString("correo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("Usuario => " + usuario);
                        System.out.println("Password => " + contraseña);
                        System.out.println("Correo => " + correo);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                    } else {
                        System.out.println(ANSI_RED + "No se encontro ningun Gestor con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener el Gestor => " +  ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Obtención de todos los Gestores.
    private void obtenerTodosLosGestores() {
        try {
            String getter = "SELECT * FROM gestor";
            try (Statement statement = conexion.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getter)) {
                    System.out.println( ANSI_GREEN + "=== Lista de todos los Gestores ===" + ANSI_RESET);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String usuario = resultSet.getString("usuario");
                        String password = resultSet.getString("password");
                        String correo = resultSet.getString("correo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("Usuario => " + usuario);
                        System.out.println("Password => " + password);
                        System.out.println("Correo => " + correo);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n" );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener los Gestores => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Actualización de un Gestor
    private void actualizarGestor() {
        System.out.print("Ingrese el ID del Gestor a actualizar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String update = "SELECT * FROM gestor WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(update)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String idActual = resultSet.getString("id");
                        String usuarioActual = resultSet.getString("usuario");
                        String passwordActual = resultSet.getString("password");
                        String correoActual = resultSet.getString("correo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "=== Datos actuales del Gestor ===" + ANSI_RESET + "\n");
                        System.out.println("ID => " + idActual);
                        System.out.println("Usuario => " + usuarioActual);
                        System.out.println("Password => " + passwordActual);
                        System.out.println("Correo => " + correoActual);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                        
                        System.out.println("=== Ingrese los nuevos datos del Gestor ===");
                        System.out.print("Nueva ID => ");
                        String idNueva = scanner.nextLine();
                        System.out.print("Nuevo Usuario => ");
                        String usuarioNuevo = scanner.nextLine();
                        System.out.print("Nueva Password => ");
                        String passwordNueva = scanner.nextLine();
                        System.out.print("Nuevo Correo => ");
                        String correoNuevo = scanner.nextLine();

                        String update1 = "UPDATE gestor SET id = ?, usuario = ?, password = ?, correo = ? WHERE id = ?";
                        try (PreparedStatement statementActualizar = conexion.prepareStatement(update1)) {
                            statementActualizar.setString(1, idNueva);
                            statementActualizar.setString(2, usuarioNuevo);
                            statementActualizar.setString(3, passwordNueva);
                            statementActualizar.setString(4, correoNuevo);
                            statementActualizar.setInt(5, id);
                            int filasActualizadas = statementActualizar.executeUpdate();
                            if (filasActualizadas > 0) {
                                System.out.println("\n" + ANSI_GREEN + "=== El Gestor ha sido actualizado exitosamente ===" + ANSI_RESET + "\n");
                            }
                        }
                    } else {
                        System.out.println("\n" + ANSI_RED + "No se encontro ningun Gestor con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al actualizar el Gestor => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Eliminación un Gestor.
    private void eliminarGestor() {
        System.out.print("Ingrese el ID del Gestor a eliminar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String delete = "DELETE FROM gestor WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(delete)) {
                statement.setInt(1, id);
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Gestor ha sido eliminado exitosamente ===" + ANSI_RESET + "\n");
                } else {
                    System.out.println("\n" + ANSI_RED + "No se encontro ningun Gestor con el ID especificado" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al eliminar el Gestor => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Creación de un Cliente.
    private void crearCliente() {
        System.out.print("Ingrese el ID del Cliente => ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el ID del Gestor => ");
        String id_gestor = scanner.nextLine();
        System.out.print("Ingrese el Nombre del Cliente => ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la Password del Cliente => ");
        String password = scanner.nextLine();
        System.out.print("Ingrese el Correo del Cliente => ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese el Saldo del Cliente => ");
        String saldo = scanner.nextLine();

        try {
            String create = "INSERT INTO cliente (id, id_gestor, usuario, password, correo, saldo) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(create)) {
                statement.setString(1, id);
                statement.setString(2, id_gestor);
                statement.setString(3, usuario);
                statement.setString(4, password);
                statement.setString(5, correo);
                statement.setString(6, saldo);
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Cliente ha sido creado exitosamente ===" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al crear el Cliente (Verifique la informacion ingresada)" + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Obtención de un Cliente por ID.
    private void obtenerClientePorId() {
        System.out.print("Ingrese el ID del Cliente => ");
        int getting = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String getter = "SELECT * FROM cliente WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(getter)) {
                statement.setInt(1, getting);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String id_gestor = resultSet.getString("id_gestor");
                        String usuario = resultSet.getString("usuario");
                        String contraseña = resultSet.getString("password");
                        String correo = resultSet.getString("correo");
                        String saldo = resultSet.getString("saldo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID del Gestor => " + id_gestor);
                        System.out.println("Usuario => " + usuario);
                        System.out.println("Password => " + contraseña);
                        System.out.println("Correo => " + correo);
                        System.out.println("Saldo => " + saldo);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                    } else {
                        System.out.println(ANSI_RED + "No se encontró ningún Cliente con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener el Cliente => " +  ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Obtención de todos los Clientes.
    private void obtenerTodosLosClientes() {
        try {
            String getter = "SELECT * FROM cliente";
            try (Statement statement = conexion.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getter)) {
                    System.out.println( ANSI_GREEN + "=== Lista de todos los Clientes ===" + ANSI_RESET);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int id_gestor = resultSet.getInt("id_gestor");
                        String usuario = resultSet.getString("usuario");
                        String password = resultSet.getString("password");
                        String correo = resultSet.getString("correo");
                        double saldo = resultSet.getDouble("saldo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID del Gestor => " + id_gestor);
                        System.out.println("Usuario => " + usuario);
                        System.out.println("Password => " + password);
                        System.out.println("Correo => " + correo);
                        System.out.println("Saldo => " + saldo);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n" );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener los Clientes => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Actualización de un Cliente
    private void actualizarCliente() {
        System.out.print("Ingrese el ID del Cliente a actualizar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String update = "SELECT * FROM cliente WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(update)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String idActual = resultSet.getString("id");
                        String id_gestorActual = resultSet.getString("id_gestor");
                        String usuarioActual = resultSet.getString("usuario");
                        String passwordActual = resultSet.getString("password");
                        String correoActual = resultSet.getString("correo");
                        String saldoActual = resultSet.getString("saldo");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "=== Datos actuales del Cliente ===" + ANSI_RESET + "\n");
                        System.out.println("ID => " + idActual);
                        System.out.println("ID del Gestor => " + id_gestorActual);
                        System.out.println("Usuario => " + usuarioActual);
                        System.out.println("Password => " + passwordActual);
                        System.out.println("Correo => " + correoActual);
                        System.out.println("Saldo => " + saldoActual);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                        
                        System.out.println("=== Ingrese los nuevos datos del Cliente ===");
                        System.out.print("Nueva ID => ");
                        String idNueva = scanner.nextLine();
                        System.out.print("Nueva ID del Gestor => ");
                        String id_gestorNuevo = scanner.nextLine();
                        System.out.print("Nuevo Usuario => ");
                        String usuarioNuevo = scanner.nextLine();
                        System.out.print("Nueva Password => ");
                        String passwordNueva = scanner.nextLine();
                        System.out.print("Nuevo Correo => ");
                        String correoNuevo = scanner.nextLine();
                        System.out.print("Nuevo Saldo => ");
                        String saldoNuevo = scanner.nextLine();

                        String update1 = "UPDATE cliente SET id = ?, id_gestor = ?, usuario = ?, password = ?, correo = ?, saldo =? WHERE id = ?";
                        try (PreparedStatement statementActualizar = conexion.prepareStatement(update1)) {
                            statementActualizar.setString(1, idNueva);
                            statementActualizar.setString(2, id_gestorNuevo);
                            statementActualizar.setString(3, usuarioNuevo);
                            statementActualizar.setString(4, passwordNueva);
                            statementActualizar.setString(5, correoNuevo);
                            statementActualizar.setString(6, saldoNuevo);
                            statementActualizar.setInt(7, id);
                            int filasActualizadas = statementActualizar.executeUpdate();
                            if (filasActualizadas > 0) {
                                System.out.println("\n" + ANSI_GREEN + "=== El Cliente ha sido actualizado exitosamente ===" + ANSI_RESET + "\n");
                            }
                        }
                    } else {
                        System.out.println("\n" + ANSI_RED + "No se encontro ningun Cliente con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al actualizar el Cliente => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Eliminación un Cliente.
    private void eliminarCliente() {
        System.out.print("Ingrese el ID del Cliente a eliminar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String delete = "DELETE FROM cliente WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(delete)) {
                statement.setInt(1, id);
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Cliente ha sido eliminado exitosamente ===" + ANSI_RESET + "\n");
                } else {
                    System.out.println("\n" + ANSI_RED + "No se encontro ningun Cliente con el ID especificado" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al eliminar el Cliente => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Creación de un Mensaje.
    private void crearMensaje() {
        System.out.print("Ingrese el ID del Mensaje => ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el ID de Origen => ");
        String id_origen = scanner.nextLine();
        System.out.print("Ingrese el ID de Destino => ");
        String id_destino = scanner.nextLine();
        System.out.print("Ingrese el Texto => ");
        String texto = scanner.nextLine();
        System.out.print("Ingrese la Fecha de Envio => ");
        String fecha = scanner.nextLine();
    
        try {
            String create = "INSERT INTO mensaje (id, id_origen, id_destino, texto, fecha) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(create)) {
                statement.setString(1, id);
                statement.setString(2, id_origen);
                statement.setString(3, id_destino);
                statement.setString(4, texto);
                statement.setString(5, fecha);
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Mensaje ha sido enviado exitosamente ===" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al enviar Mensaje (Verifique la informacion ingresada)" + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Obtención de un Mensaje por ID.
    private void obtenerMensajePorId() {
        System.out.print("Ingrese el ID del Mensaje => ");
        int getting = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String getter = "SELECT * FROM mensaje WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(getter)) {
                statement.setInt(1, getting);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String id_origen = resultSet.getString("id_origen");
                        String id_destino = resultSet.getString("id_destino");
                        String texto = resultSet.getString("texto");
                        String fecha = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID de Origen => " + id_origen);
                        System.out.println("ID de Destino => " + id_destino);
                        System.out.println("Texto => " + texto);
                        System.out.println("Fecha => " + fecha);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                    } else {
                        System.out.println(ANSI_RED + "No se encontró ningún Mensaje con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener Mensaje=> " +  ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Obtención de todos los Mensajes.
    private void obtenerTodosLosMensajes() {
        try {
            String getter = "SELECT * FROM mensaje";
            try (Statement statement = conexion.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getter)) {
                    System.out.println( ANSI_GREEN + "=== Lista de todos los Mensajes ===" + ANSI_RESET);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int id_origen = resultSet.getInt("id_origen");
                        int id_destino = resultSet.getInt("id_destino");
                        String texto = resultSet.getString("texto");
                        String fecha = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID de Origen => " + id_origen);
                        System.out.println("ID de Destino => " + id_destino);
                        System.out.println("Texto => " + texto);
                        System.out.println("Fecha => " + fecha);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n" );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener los Mensajes => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Actualización de un Mensaje
    private void actualizarMensaje() {
        System.out.print("Ingrese el ID del Mensaje a actualizar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String update = "SELECT * FROM mensaje WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(update)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String idActual = resultSet.getString("id");
                        String id_origenActual = resultSet.getString("id_origen");
                        String id_destinoActual = resultSet.getString("id_destino");
                        String textoActual = resultSet.getString("texto");
                        String fechaActual = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "=== Datos actuales del Gestor ===" + ANSI_RESET + "\n");
                        System.out.println("ID => " + idActual);
                        System.out.println("ID de Origen => " + id_origenActual);
                        System.out.println("ID de Destino => " + id_destinoActual);
                        System.out.println("Texto => " + textoActual);
                        System.out.println("Fecha => " + fechaActual);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                        
                        System.out.println("=== Ingrese los nuevos datos del Mensaje ===");
                        System.out.print("Nueva ID => ");
                        String idNueva = scanner.nextLine();
                        System.out.print("Nuevo ID de Origen => ");
                        String id_origenNueva = scanner.nextLine();
                        System.out.print("Nueva ID de Destino => ");
                        String id_destinoNueva = scanner.nextLine();
                        System.out.print("Nuevo Texto => ");
                        String textoNuevo = scanner.nextLine();
                        System.out.print("Nueva Fecha de Envio => ");
                        String fechaNueva = scanner.nextLine();

                        String update1 = "UPDATE mensaje SET id = ?, id_origen = ?, id_destino = ?, texto = ?, fecha = ? WHERE id = ?";
                        try (PreparedStatement statementActualizar = conexion.prepareStatement(update1)) {
                            statementActualizar.setString(1, idNueva);
                            statementActualizar.setString(2, id_origenNueva);
                            statementActualizar.setString(3, id_destinoNueva);
                            statementActualizar.setString(4, textoNuevo);
                            statementActualizar.setString(5, fechaNueva);
                            statementActualizar.setInt(6, id);
                            int filasActualizadas = statementActualizar.executeUpdate();
                            if (filasActualizadas > 0) {
                                System.out.println("\n" + ANSI_GREEN + "=== El Mensaje ha sido actualizado exitosamente ===" + ANSI_RESET + "\n");
                            }
                        }
                    } else {
                        System.out.println("\n" + ANSI_RED + "No se encontro ningun Mensaje con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al actualizar el Mensaje => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Eliminación un Mensaje.
    private void eliminarMensaje() {
        System.out.print("Ingrese el ID del Mensaje a eliminar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String delete = "DELETE FROM mensaje WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(delete)) {
                statement.setInt(1, id);
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== El Mensaje ha sido eliminado exitosamente ===" + ANSI_RESET + "\n");
                } else {
                    System.out.println("\n" + ANSI_RED + "No se encontro ningun Mensaje con el ID especificado" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al eliminar el Mensaje => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Envío de una Transferencia.
    private void enviarTransferencia() {
        System.out.print("Ingrese el ID de la Transferencia => ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el ID del Ordenante => ");
        String id_ordenante = scanner.nextLine();
        System.out.print("Ingrese el ID del Beneficiario => ");
        String id_beneficiario = scanner.nextLine();
        System.out.print("Ingrese el Importe de la Transferencia => ");
        String importe = scanner.nextLine();
        System.out.print("Ingrese el Concepto de la Transferencia => ");
        String concepto = scanner.nextLine();
        System.out.print("Ingrese la Fecha de la Transferencia => ");
        String fecha = scanner.nextLine();

        try {
            String create = "INSERT INTO transferencia (id, id_ordenante, id_beneficiario, importe, concepto, fecha) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(create)) {
                statement.setString(1, id);
                statement.setString(2, id_ordenante);
                statement.setString(3, id_beneficiario);
                statement.setString(4, importe);
                statement.setString(5, concepto);
                statement.setString(6, fecha);
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== La Transferencia ha sido registrada exitosamente ===" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al registrar la Transferencia (Verifique la informacion ingresada)" + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    // Obtención de los Datos de una Transferencia por ID.
    private void obtenerTransferenciaPorId () {
        System.out.print("Ingrese el ID de la Transferencia => ");
        int getting = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String getter = "SELECT * FROM transferencia WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(getter)) {
                statement.setInt(1, getting);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String id_ordenante = resultSet.getString("id_ordenante");
                        String id_beneficiario = resultSet.getString("id_beneficiario");
                        String importe = resultSet.getString("importe");
                        String concepto = resultSet.getString("concepto");
                        String fecha = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID del Ordenante => " + id_ordenante);
                        System.out.println("ID del Beneficiario => " + id_beneficiario);
                        System.out.println("Importe => " + importe);
                        System.out.println("Concepto => " + concepto);
                        System.out.println("Fecha => " + fecha);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                    } else {
                        System.out.println(ANSI_RED + "No se encontro ninguna Transferencia con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener el Cliente => " +  ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Obtención de todas las Transferencias.
    private void obtenerTodasLasTransferencias() {
        try {
            String getter = "SELECT * FROM transferencia";
            try (Statement statement = conexion.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(getter)) {
                    System.out.println( ANSI_GREEN + "=== Lista de todos las Transferencia ===" + ANSI_RESET);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int id_ordenante = resultSet.getInt("id_ordenante");
                        String id_beneficiario = resultSet.getString("id_beneficiario");
                        Double importe = resultSet.getDouble("importe");
                        String concepto = resultSet.getString("concepto");
                        String fecha = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println("ID => " + id);
                        System.out.println("ID del Ordenante => " + id_ordenante);
                        System.out.println("ID del Beneficiario => " + id_beneficiario);
                        System.out.println("Importe => " + importe);
                        System.out.println("Concepto => " + concepto);
                        System.out.println("Fecha => " + fecha);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n" );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ANSI_RED + "Error al obtener las Transferencias => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

     //Actualización de los Datos una Transferencia
    private void actualizarTransferencia() {
        System.out.print("Ingrese el ID de la trasnferencia a actualizar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String update = "SELECT * FROM transferencia WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(update)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String idActual = resultSet.getString("id");
                        String id_ordenanteActual = resultSet.getString("id_ordenante");
                        String id_beneficiarioActual = resultSet.getString("id_beneficiario");
                        String importeActual = resultSet.getString("Importe");
                        String conceptoActual = resultSet.getString("concepto");
                        String fechaActual = resultSet.getString("fecha");
                        System.out.println("\n" + ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "=== Datos actuales de la Transferencia ===" + ANSI_RESET + "\n");
                        System.out.println("ID => " + idActual);
                        System.out.println("ID del Ordenante => " + id_ordenanteActual);
                        System.out.println("ID del Beneficiario => " + id_beneficiarioActual);
                        System.out.println("Importe => " + importeActual);
                        System.out.println("Concepto => " + conceptoActual);
                        System.out.println("Fecha => " + fechaActual);
                        System.out.println(ANSI_BLACK + "-------------------------------------------------------" + ANSI_RESET + "\n");
                        
                        System.out.println("=== Ingrese los nuevos datos de la Transferencia ===");
                        System.out.print("Nueva ID => ");
                        String idNueva = scanner.nextLine();
                        System.out.print("Nueva ID del Ordenante => ");
                        String id_ordenanteNuevo = scanner.nextLine();
                        System.out.print("Nueva ID del Beneficiario => ");
                        String id_beneficiarioNuevo = scanner.nextLine();
                        System.out.print("Nuevo Importe => ");
                        String importeNuevo = scanner.nextLine();
                        System.out.print("Nuevo Concepto => ");
                        String conceptoNuevo = scanner.nextLine();
                        System.out.print("Nueva Fecha => ");
                        String fechaNueva = scanner.nextLine();

                        String update1 = "UPDATE transferencia SET id = ?, id_ordenante = ?, id_beneficiario = ?, importe = ?, concepto = ?, fecha =? WHERE id = ?";
                        try (PreparedStatement statementActualizar = conexion.prepareStatement(update1)) {
                            statementActualizar.setString(1, idNueva);
                            statementActualizar.setString(2, id_ordenanteNuevo);
                            statementActualizar.setString(3, id_beneficiarioNuevo);
                            statementActualizar.setString(4, importeNuevo);
                            statementActualizar.setString(5, conceptoNuevo);
                            statementActualizar.setString(6, fechaNueva);
                            statementActualizar.setInt(7, id);
                            int filasActualizadas = statementActualizar.executeUpdate();
                            if (filasActualizadas > 0) {
                                System.out.println("\n" + ANSI_GREEN + "=== Los datos de la Transferencia han sido actualizada exitosamente ===" + ANSI_RESET + "\n");
                            }
                        }
                    } else {
                        System.out.println("\n" + ANSI_RED + "No se encontro ninguna Transferencia con el ID especificado" + ANSI_RESET + "\n");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al actualizar los datos de la Transferencia => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }

    // Eliminación los Datos de una Transferencia.
    private void eliminarTransferencia() {
        System.out.print("Ingrese el ID de la Transferencia a eliminar => ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            String delete = "DELETE FROM transferencia WHERE id = ?";
            try (PreparedStatement statement = conexion.prepareStatement(delete)) {
                statement.setInt(1, id);
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("\n" + ANSI_GREEN + "=== Los datos de la Transferencia han sido eliminados exitosamente ===" + ANSI_RESET + "\n");
                } else {
                    System.out.println("\n" + ANSI_RED + "No se encontro ninguna Transferencia con el ID especificado" + ANSI_RESET + "\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al eliminar los datos de la Transferencia => " + ANSI_RESET + e.getMessage() + "\n");
        }
    }
    
    


    public static void main(String[] args) {
        main_gestor gestorDatos = new main_gestor();
        try {
            gestorDatos.conectar();
            gestorDatos.ejecutar();
        } catch (SQLException e) {
            System.out.println("\n" + ANSI_RED + "Error al conectar con la base de datos => " + ANSI_RESET + e.getMessage() + "\n");
        } finally {
            try {
                gestorDatos.desconectar();
            } catch (SQLException e) {
                System.out.println("\n" + ANSI_RED + "Error al cerrar la conexión => " + ANSI_RESET + e.getMessage() + "\n");
            }
        }
    }
    // Colores de la Consola
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
}

