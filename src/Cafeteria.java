import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cafeteria implements CrudPedido {

    private String nombreCafeteria;

    HashSet<Cliente> clientes = new HashSet<>();

    Map<String, Double> mapaPedidos = new HashMap<>();

    public Cafeteria(String nombreCafeteria) {
        this.nombreCafeteria = nombreCafeteria;
    }

    public boolean registrarCliente(Cliente cliente) {

        if (clientes.add(cliente)) {
            return true;
        }

        return false;
    }

    public Cliente buscarCliente(String correo) {

        for (Cliente cliente : clientes) {

            if (cliente.getCorreo().equalsIgnoreCase(correo)) {
                return cliente;
            }
        }

        return null;
    }

    public void mostrarClientes() {

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    @Override
    public void registrarPedido(String correo,
                                double consumo) {

        Cliente cliente = buscarCliente(correo);

        if (cliente == null) {
            System.out.println(
                    "No se puede registrar el pedido. Cliente inexistente.");
            return;
        }

        if (consumo <= 0) {
            System.out.println(
                    "El consumo debe ser mayor que cero.");
            return;
        }

        mapaPedidos.put(cliente.getCodigoCliente(),
                consumo);

        System.out.println("Pedido registrado correctamente.");
    }

    @Override
    public void actualizarPedido(String correo,
                                 double nuevoConsumo) {

        Cliente cliente = buscarCliente(correo);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        if (!mapaPedidos.containsKey(
                cliente.getCodigoCliente())) {

            System.out.println("Pedido inexistente.");
            return;
        }

        mapaPedidos.put(cliente.getCodigoCliente(),
                nuevoConsumo);

        System.out.println("Pedido actualizado.");
    }

    @Override
    public void eliminarPedido(String correo) {

        Cliente cliente = buscarCliente(correo);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        if (!mapaPedidos.containsKey(
                cliente.getCodigoCliente())) {

            System.out.println("Pedido inexistente.");
            return;
        }

        mapaPedidos.remove(cliente.getCodigoCliente());

        System.out.println("Pedido eliminado.");
    }

    @Override
    public void promedioConsumo() {

        if (mapaPedidos.isEmpty()) {

            System.out.println(
                    "No hay datos disponibles.");
            return;
        }

        double suma = 0;

        for (double consumo : mapaPedidos.values()) {
            suma += consumo;
        }

        double promedio = suma /
                mapaPedidos.size();

        System.out.println(
                "Promedio de consumo: " + promedio);
    }

    @Override
    public void mejorCliente() {

        if (mapaPedidos.isEmpty()) {

            System.out.println(
                    "No hay datos disponibles.");
            return;
        }

        String mejorCodigo = "";
        double mayorConsumo = 0;

        for (Map.Entry<String, Double> pedido :
                mapaPedidos.entrySet()) {

            if (pedido.getValue() > mayorConsumo) {

                mayorConsumo = pedido.getValue();
                mejorCodigo = pedido.getKey();
            }
        }

        System.out.println(
                "Mejor cliente: " +
                        mejorCodigo +
                        " Consumo: " +
                        mayorConsumo);
    }

    public void mostrarPedidos() {

        if (mapaPedidos.isEmpty()) {

            System.out.println(
                    "No existen pedidos registrados.");
            return;
        }

        for (Map.Entry<String, Double> pedido :
                mapaPedidos.entrySet()) {

            System.out.println(
                    "Código Cliente: "
                            + pedido.getKey()
                            + " Consumo: "
                            + pedido.getValue());
        }
    }
}