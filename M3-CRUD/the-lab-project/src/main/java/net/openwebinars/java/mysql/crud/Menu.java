package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.dao.EmpleadoDao;
import net.openwebinars.java.mysql.crud.dao.EmpleadoDaoImpl;
import net.openwebinars.java.mysql.crud.model.Empleado;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImpl;
import net.openwebinars.java.mysql.crud.model.Producto;

import net.openwebinars.java.mysql.crud.dao.CategoriaDao;
import net.openwebinars.java.mysql.crud.dao.CategoriaDaoImp;
import net.openwebinars.java.mysql.crud.model.Categoria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Menu {

    private KeyboardReader lector;
    private EmpleadoDao repositorio;
    private ProductoDao repositorioProducto;
    private CategoriaDao repositorioCategoria;

    public Menu() {
        lector = new KeyboardReader();
        repositorio = EmpleadoDaoImpl.getInstance();
        repositorioProducto = ProductoDaoImpl.getInstance(); // Inicialización del repositorio de productos
        repositorioCategoria = CategoriaDaoImp.getInstance();

    }

    public void init() {

        int seleccion;

        do {
            mostrarMenu();
            seleccion = lector.nextInt();

            switch (seleccion) {
                case 1:
                    mostrarTodos();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    agregar();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    modificar();
                    break;
                case 6:
                    agregarProducto();
                    break;
                case 7:
                    mostrarTodosProductos();
                    break;
                case 8:
                    modificarProducto();
                    break;
                case 9:
                    eliminarProducto();
                    break;
                case 10:
                    mostrarTodasCategorias();
                    break;
                case 11:
                    agregarCategoria();
                    break;
                case 12:
                    modificarCategoria();
                    break;
                case 13:
                    eliminarCategoria();
                    break;
                case 14:
                    System.out.println("\nFinalizando aplicación...\n");
                    break;
                default:
                    System.err.println("\nLa opción seleccionada no es válida\n\n");
            }

        } while(seleccion != 0);
    }


    public void mostrarMenu() {

        System.out.println("Seleccione una opción de las siguientes: \n");
        System.out.println("1- Mostrar todos los empleados");
        System.out.println("2- Buscar un empleado por el ID");
        System.out.println("3- Agregar un nuevo empleado");
        System.out.println("4- Eliminar un empleado");
        System.out.println("5- Modificar un empleado");
        System.out.println("6- Agregar un nuevo producto");
        System.out.println("7- Mostrar todos los productos");
        System.out.println("8- Modificar un producto");
        System.out.println("9- Eliminar un producto");
        System.out.println("10- Mostrar todas las categorías");
        System.out.println("11- Agregar una nueva categoría");
        System.out.println("12- Modificar una categoría");
        System.out.println("13- Eliminar una categoría");
        System.out.println("14- Finalizar");
        System.out.print("\nOpción: ");
    }

    public void agregar() {

        System.out.println("\nAGREGAR NUEVO EMPLEADO");
        System.out.println("----------------------\n");

        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = lector.nextLine();

        System.out.print("Ingrese los apellidos del empleado: ");
        String apellidos = lector.nextLine();

        System.out.print("Ingrese la fecha de nacimiento del empleado (dd/MM/aaaa): ");
        LocalDate fechaNacimiento = lector.nextLocalDate();

        System.out.print("Ingrese el cargo del empleado: ");
        String puesto = lector.nextLine();

        System.out.print("Ingrese el correo electrónico del empleado: ");
        String email = lector.nextLine();

        try {
            repositorio.add(new Empleado(nombre, apellidos, fechaNacimiento, puesto, email));
            System.out.println("Nuevo empleado agregado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al insertar el nuevo registro en la base de datos.");
            e.printStackTrace();
        }

        System.out.println("");

    }

    public void mostrarTodos() {
        System.out.println("\nTODOS LOS EMPLEADOS");
        System.out.println("----------------------------\n");


        try {
            List<Empleado> resultado = repositorio.getAll();

            if (resultado.isEmpty())
                System.out.println("No existen empleados registrados en la base de datos");
            else {
                imprimirCabeceraTablaEmpleado();
                resultado.forEach(this::imprimirEmpleado);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
            e.printStackTrace();
        }

        System.out.println("\n");


    }

    public void buscarPorId() {

        System.out.println("\nBÚSQUEDA DE EMPLEADO POR ID");
        System.out.println("--------------------------\n");

        try {

            System.out.print("Ingrese el ID del empleado a buscar: ");
            int id = lector.nextInt();

            Empleado empleado = repositorio.getById(id);

            if (empleado == null)
                System.out.println("No existe ningún empleado con ese ID en la base de datos");
            else {
                imprimirCabeceraTablaEmpleado();
                imprimirEmpleado(empleado);
            }

            System.out.println("\n");

        } catch (SQLException ex) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }

    }

    public void modificar() {

        System.out.println("\nMODIFICACIÓN DE EMPLEADO");
        System.out.println("------------------------\n");

        try {

            System.out.print("Ingrese el ID del empleado a modificar: ");
            int id = lector.nextInt();

            Empleado empleado = repositorio.getById(id);

            if (empleado == null)
                System.out.println("No existe ningún empleado con ese ID en la base de datos");
            else {
                imprimirCabeceraTablaEmpleado();
                imprimirEmpleado(empleado);
                System.out.println("\n");

                System.out.printf("Ingrese el nombre (sin apellidos) del empleado (%s): ", empleado.getNombre());
                String nombre = lector.nextLine();
                nombre = (nombre.isBlank()) ? empleado.getNombre() : nombre;

                System.out.printf("Ingrese los apellidos del empleado (%s): ", empleado.getApellidos());
                String apellidos = lector.nextLine();
                apellidos = (apellidos.isBlank()) ? empleado.getApellidos() : apellidos;

                System.out.printf("Ingrese la fecha de nacimiento del empleado (formato dd/MM/aaaa) (%s)",
                        empleado.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                String strFechaNacimiento = lector.nextLine();
                LocalDate fechaNacimiento = (strFechaNacimiento.isBlank()) ? empleado.getFechaNacimiento() :
                        LocalDate.parse(strFechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.printf("Ingrese el cargo del empleado (%s): ", empleado.getPuesto());
                String puesto = lector.nextLine();
                puesto = (puesto.isBlank()) ? empleado.getPuesto() : puesto;

                System.out.printf("Ingrese el correo electrónico del empleado (%s): ", empleado.getEmail());
                String email = lector.nextLine();
                email = (email.isBlank()) ? empleado.getEmail() : email;

                empleado.setNombre(nombre);
                empleado.setApellidos(apellidos);
                empleado.setFechaNacimiento(fechaNacimiento);
                empleado.setPuesto(puesto);
                empleado.setEmail(email);

                repositorio.update(empleado);

                System.out.println("");
                System.out.printf("Empleado con ID %s actualizado correctamente", empleado.getId_empleado());
                System.out.println("");

            }

        } catch (SQLException ex) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }


    }

    public void eliminar() {

        System.out.println("\nELIMINACIÓN DE EMPLEADO");
        System.out.println("----------------------\n");

        try {

            System.out.print("Ingrese el ID del empleado a eliminar: ");
            int id = lector.nextInt();

            System.out.printf("¿Está seguro que desea eliminar al empleado con ID=%s? (s/n): ", id);
            String confirmar = lector.nextLine();

            if (confirmar.equalsIgnoreCase("s")) {
                repositorio.delete(id);
                System.out.printf("El empleado con ID %s ha sido eliminado\n", id);
            }


        } catch (SQLException ex) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }

        System.out.println("");

    }




    private void imprimirCabeceraTablaEmpleado() {
        System.out.printf("%2s %30s %8s %10s %25s", "ID", "NOMBRE", "FEC. NAC.", "CARGO", "EMAIL");
        System.out.println("");
        IntStream.range(1, 100).forEach(x -> System.out.print("-"));
        System.out.println("\n");
    }

    private void imprimirEmpleado(Empleado emp) {
        System.out.printf("%2s %30s %9s %10s %25s\n",
                emp.getId_empleado(),
                emp.getNombre() + " " + emp.getApellidos(),
                emp.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
                emp.getPuesto(),
                emp.getEmail());
    }


    static class KeyboardReader {

        BufferedReader br;
        StringTokenizer st;

        public KeyboardReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    System.err.println("Error al leer del teclado");
                    ex.printStackTrace();
                }
            }

            return st.nextToken();

        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        LocalDate nextLocalDate() {
            return LocalDate.parse(next(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreElements())
                    str = st.nextToken("\n");
                else
                    str = br.readLine();
            } catch (IOException ex) {
                System.err.println("Error al leer del teclado");
                ex.printStackTrace();
            }

            return str;
        }



    }
    public void mostrarTodosProductos() {
        System.out.println("\nLISTA DE TODOS LOS PRODUCTOS");
        System.out.println("---------------------------\n");

        try {
            List<Producto> productos = repositorioProducto.getAll();

            if (productos.isEmpty())
                System.out.println("No existen productos registrados en la base de datos");
            else {
                imprimirCabeceraTablaProducto();
                productos.forEach(this::imprimirProducto);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }
    }

    // Métodos que necesitan ser corregidos en la clase Menu

    public void agregarProducto() {
        System.out.println("\nAGREGAR NUEVO PRODUCTO");
        System.out.println("---------------------\n");

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = lector.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = lector.nextDouble();

        System.out.print("Ingrese el ID de la categoría del producto: ");
        int categoriaId = lector.nextInt();

        try {
            Producto producto = new Producto(nombre, precio, categoriaId);
            repositorioProducto.add(producto);
            System.out.println("Nuevo producto registrado correctamente");
        } catch (SQLException e) {
            System.err.println("Error al insertar el nuevo registro en la base de datos. Intente nuevamente o contacte al administrador del sistema.");
            e.printStackTrace();
        }
    }

    public void agregarCategoria() {
        System.out.println("\nAGREGAR NUEVA CATEGORÍA");
        System.out.println("----------------------\n");

        System.out.print("Ingrese el nombre de la categoría: ");
        String nombre = lector.nextLine();

        try {
            Categoria categoria = new Categoria(nombre);
            repositorioCategoria.add(categoria);
            System.out.println("Nueva categoría registrada correctamente");
        } catch (SQLException e) {
            System.err.println("Error al insertar el nuevo registro en la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }
    }

    public void modificarProducto() {
        System.out.println("\nMODIFICACIÓN DE PRODUCTO");
        System.out.println("-----------------------\n");

        try {
            System.out.print("Ingrese el ID del producto a modificar: ");
            int id = lector.nextInt();

            Producto producto = repositorioProducto.getById(id);

            if (producto == null)
                System.out.println("No existe ningún producto con ese ID en la base de datos");
            else {
                System.out.printf("Ingrese el nombre del producto (%s): ", producto.getNombre());
                String nombre = lector.nextLine();
                nombre = (nombre.isBlank()) ? producto.getNombre() : nombre;

                System.out.printf("Ingrese el precio del producto (%.2f): ", producto.getPrecio());
                double precio = lector.nextDouble();

                System.out.printf("Ingrese el ID de la categoría (%d): ", producto.getCategoriaId());
                int categoriaId = lector.nextInt();

                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setCategoriaId(categoriaId);

                repositorioProducto.update(producto);

                System.out.println("Producto actualizado correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }
    }

    public void eliminarProducto() {
        System.out.println("\nELIMINACIÓN DE PRODUCTO");
        System.out.println("----------------------\n");

        try {
            System.out.print("Ingrese el ID del producto a eliminar: ");
            int id = lector.nextInt();

            System.out.printf("¿Está seguro que desea eliminar el producto con ID=%s? (s/n): ", id);
            String confirmar = lector.nextLine();

            if (confirmar.equalsIgnoreCase("s")) {
                repositorioProducto.delete(id);
                System.out.printf("El producto con ID %s ha sido eliminado\n", id);
            }

        } catch (SQLException ex) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
        }
    }

    public void mostrarTodasCategorias() {
        System.out.println("\nLISTA DE TODAS LAS CATEGORÍAS");
        System.out.println("----------------------------\n");

        try {
            List<Categoria> categorias = repositorioCategoria.getAll();

            if (categorias.isEmpty())
                System.out.println("No existen categorías registradas en la base de datos");
            else {
                imprimirCabeceraTablaCategoria();
                categorias.forEach(this::imprimirCategoria);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
            e.printStackTrace();
        }
    }



    public void modificarCategoria() {
        System.out.println("\nMODIFICACIÓN DE CATEGORÍA");
        System.out.println("------------------------\n");

        try {
            System.out.print("Ingrese el ID de la categoría a modificar: ");
            int id = lector.nextInt();

            Categoria categoria = repositorioCategoria.getById(id);

            if (categoria == null)
                System.out.println("No existe ninguna categoría con ese ID en la base de datos");
            else {
                System.out.printf("Ingrese el nombre de la categoría (%s): ", categoria.getNombre());
                String nombre = lector.nextLine();
                nombre = (nombre.isBlank()) ? categoria.getNombre() : nombre;

                categoria.setNombre(nombre);
                repositorioCategoria.update(categoria);

                System.out.println("Categoría actualizada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
            e.printStackTrace();
        }
    }

    public void eliminarCategoria() {
        System.out.println("\nELIMINACIÓN DE CATEGORÍA");
        System.out.println("-----------------------\n");

        try {
            System.out.print("Ingrese el ID de la categoría a eliminar: ");
            int id = lector.nextInt();

            System.out.printf("¿Está seguro que desea eliminar la categoría con ID=%s? (s/n): ", id);
            String confirmar = lector.nextLine();
            if (confirmar.equalsIgnoreCase("s")) {
                repositorioCategoria.delete(id);
                System.out.printf("La categoría con ID %s ha sido eliminada\n", id);
            }

        } catch (SQLException ex) {
            System.err.println("Error al consultar la base de datos. Intente nuevamente o contacte al administrador del sistema.");
            ex.printStackTrace();
        }
    }

    // Métodos de impresión de tablas
    public void imprimirCabeceraTablaProducto() {
        System.out.printf("| %-5s | %-30s | %-50s | %-10s | %-10s |\n", "ID", "Nombre", "Descripción", "Precio", "Categoría ID");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void imprimirProducto(Producto producto) {
        System.out.printf("| %-5d | %-30s | %-50s | %-10.2f | %-10d |\n", producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getCategoriaId());
    }

    public void imprimirCabeceraTablaCategoria() {
        System.out.printf("| %-5s | %-30s |\n", "ID", "Nombre");
        System.out.println("--------------------------------------");
    }

    public void imprimirCategoria(Categoria categoria) {
        System.out.printf("| %-5d | %-30s |\n", categoria.getId(), categoria.getNombre());
    }
}