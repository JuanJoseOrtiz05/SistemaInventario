package inventario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.collections.*;

public class Main extends Application {

    private Inventario inventario = new Inventario();
    private TableView<Producto> tabla = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Inventario de Tienda");

        TableColumn<Producto, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCodigo()));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));

        TableColumn<Producto, String> colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCategoria()));

        TableColumn<Producto, Number> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPrecio()));

        TableColumn<Producto, Number> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getCantidad()));

        tabla.getColumns().addAll(colCodigo, colNombre, colCategoria, colPrecio, colCantidad);

        TextField tfCodigo = new TextField(); tfCodigo.setPromptText("Código");
        TextField tfNombre = new TextField(); tfNombre.setPromptText("Nombre");
        TextField tfCategoria = new TextField(); tfCategoria.setPromptText("Categoría");
        TextField tfPrecio = new TextField(); tfPrecio.setPromptText("Precio");
        TextField tfCantidad = new TextField(); tfCantidad.setPromptText("Cantidad");

        Button btnAgregar = new Button("Agregar Producto");
        btnAgregar.setOnAction(e -> {
            try {
                Producto p = new Producto(
                        tfCodigo.getText(),
                        tfNombre.getText(),
                        tfCategoria.getText(),
                        Double.parseDouble(tfPrecio.getText()),
                        Integer.parseInt(tfCantidad.getText())
                );
                inventario.agregarProducto(p);
                tabla.setItems(FXCollections.observableArrayList(inventario.obtenerProductos()));

                tfCodigo.clear(); tfNombre.clear(); tfCategoria.clear(); tfPrecio.clear(); tfCantidad.clear();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error en los datos.").showAndWait();
            }
        });

        VBox formulario = new VBox(5, tfCodigo, tfNombre, tfCategoria, tfPrecio, tfCantidad, btnAgregar);
        formulario.setPadding(new Insets(10));
        VBox layout = new VBox(10, new Label("Inventario de Tienda"), tabla, formulario);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
}
