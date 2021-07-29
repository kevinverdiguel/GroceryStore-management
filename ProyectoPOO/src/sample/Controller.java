package sample;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    //ELEMENTOS DE PEDIDOS
    @FXML private JFXComboBox<String> comboBox_nompd;
    @FXML private JFXComboBox<String> comboBox_nomcl;
    @FXML private JFXButton btadd_pe;
    @FXML private JFXTextField tfcantidad_nuevavent;
    @FXML private JFXButton btAceptar;
    @FXML private JFXButton btCancelar;
    @FXML private JFXButton btselecccl;
    @FXML private JFXButton btelimpp;
    @FXML private JFXButton btgenerar_ticket;
    @FXML private JFXTextField tfid_pe;
    @FXML private JFXTextField tfidcl_pe;
    @FXML private JFXDatePicker date_pe;
    @FXML private JFXTimePicker time_pe;
    @FXML private JFXTextField tftotal_pe;
    @FXML private Label tfnom_nuevavent1;
    @FXML private Label tfprecio_nuevavent1;
    ObservableList<String> nombresPd= FXCollections.observableArrayList();
    ObservableList<String> nombresCl=FXCollections.observableArrayList();
    ProductoDTO productoG;
    ClienteDTO clienteG;
    int id_pedidoSelc;
    int idpp;
    double total;
    private static int idPedidoSeleccionado=0;

    //TABLA PEDIDO
    @FXML private TableView<ProductoPedido> tabla_pedido;
    @FXML private TableColumn<ProductoPedido, Integer> col_idpe;
    @FXML private TableColumn<ProductoPedido, Integer> col_idprod;
    @FXML private TableColumn<ProductoPedido, Integer> col_index;
    @FXML private TableColumn<ProductoPedido, String> col_nompe;
    @FXML private TableColumn<ProductoPedido, Integer> col_unidpe;
    @FXML private TableColumn<ProductoPedido, Double> col_prepe;
    @FXML private TableColumn<ProductoPedido, Double> col_subpe;
    ObservableList<ProductoPedido> lista_pp=FXCollections.observableArrayList();
    private int index_pp=-1;

    //ELEMENTOS LISTA PEDIDOS
    @FXML private JFXButton btmod_pe;
    @FXML private JFXButton btregistrar_pe;
    @FXML private JFXButton btelim_pe;
    @FXML private JFXButton btnew_pe;
    @FXML private JFXButton btprodped;

    //TABLA LISTA DE PEDIDOS
    @FXML private TableView<PedidoDTO> tabla_listapedidos;
    @FXML private TableColumn<PedidoDTO, Integer> col_idlipe;
    @FXML private TableColumn<PedidoDTO, Integer> col_idcllipe;
    @FXML private TableColumn<PedidoDTO, String> col_nomlipe;
    @FXML private TableColumn<PedidoDTO, Double> col_totallipe;
    @FXML private TableColumn<PedidoDTO, Date> col_fechalipe;
    @FXML private TableColumn<PedidoDTO, Time> col_horalipe;
    ObservableList<PedidoDTO> lista_pe=FXCollections.observableArrayList();
    private int index_pe=-1;

    //ELEMENTOS DE CLIENTES
    @FXML private JFXButton btregistrar_cl;
    @FXML private JFXButton btmod_cl;
    @FXML private JFXButton btelim_cl;
    @FXML private JFXButton btnew_cl;
    @FXML private JFXTextField tfid_cl;
    @FXML private JFXTextField tfnom_cl;
    @FXML private JFXTextField tfdir_cl;
    @FXML private JFXTextField tftel_cl;
    @FXML private JFXTextField tfmail_cl;
    //TABLA CLIENTES
    @FXML private TableView<ClienteDTO> tabla_clientes;
    @FXML private TableColumn<ClienteDTO, Integer> col_idcl;
    @FXML private TableColumn<ClienteDTO, String> col_nomcl;
    @FXML private TableColumn<ClienteDTO, String> col_dircl;
    @FXML private TableColumn<ClienteDTO, String> col_telcl;
    @FXML private TableColumn<ClienteDTO, String> col_mailcl;
    ObservableList<ClienteDTO> lista_cl;
    private int index_cl=-1;

    //ELEMENTOS DE PROVEEDORES
    @FXML private JFXButton btregistar_pv;
    @FXML private JFXButton btmod_pv;
    @FXML private JFXButton btelim_pv;
    @FXML private JFXButton btnew_pv;
    @FXML private JFXTextField tfid_pv;
    @FXML private JFXTextField tfnom_pv;
    @FXML private JFXTextField tfdir_pv;
    @FXML private JFXTextField tftel_pv;
    @FXML private JFXTextField tfmail_pv;
    //TABLA PROVEEDORES
    @FXML private TableView<ProveedorDTO> tabla_proveedores;
    @FXML private TableColumn<ProveedorDTO, Integer> col_idpv;
    @FXML private TableColumn<ProveedorDTO, String> col_nompv;
    @FXML private TableColumn<ProveedorDTO, String> col_dirpv;
    @FXML private TableColumn<ProveedorDTO, String> col_telpv;
    @FXML private TableColumn<ProveedorDTO, String> col_mailpv;
    ObservableList<ProveedorDTO> lista_pv;
    private int index_pv=-1;


    //ELEMENTOS DE PRODUCTOS
    @FXML private JFXButton btmod_pd;
    @FXML private JFXButton btelim_pd;
    @FXML private JFXButton btnew_pd;
    @FXML private JFXButton btregistrar_pd;
    @FXML private JFXTextField tfid_pd;
    @FXML private JFXTextField tfnom_pd;
    @FXML private JFXTextField tfunid_pd;
    @FXML private JFXTextField tfprecio_pd;

    //TABLA PRODUCTOS
    @FXML private TableView<ProductoDTO> tabla_productos;
    @FXML private TableColumn<ProductoDTO, Integer> col_idpd;
    @FXML private TableColumn<ProductoDTO, String> col_nompd;
    @FXML private TableColumn<ProductoDTO, Integer> col_unidpd;
    @FXML private TableColumn<ProductoDTO, Double> col_preciopd;
    ObservableList<ProductoDTO> lista_pd;
    private int index_pd=-1;


    //PANELES
    @FXML private AnchorPane homePanel;
    @FXML private AnchorPane clientPanel;
    @FXML private AnchorPane productPanel;
    @FXML private AnchorPane provPanel;
    @FXML private AnchorPane pedidoPanel;
    @FXML private AnchorPane addPdPanel;
    @FXML private AnchorPane ticket_panel;

    //TICKET
    @FXML private JFXTextArea taTicket;
    @FXML private JFXButton btCerrarTicket;

    //FILTROS DE LETRAS Y NUMEROS
    EventHandler<KeyEvent> handlerLetters = new EventHandler<KeyEvent>()
    {
        private boolean willConsume = false;

        @Override
        public void handle(KeyEvent event)
        {
            Object tempO = event.getSource();
            if(willConsume)
            {
                event.consume();
            }
            String temp=event.getCode().toString();
            if(!event.getCode().toString().matches("[a-zA-Z]")&&event.getCode()!= KeyCode.BACK_SPACE
                    &&event.getCode()!=KeyCode.SHIFT&&event.getCode()!=KeyCode.SPACE)
            {
                if(event.getEventType()==KeyEvent.KEY_PRESSED)
                {
                    willConsume=true;
                }
                else if(event.getEventType()==KeyEvent.KEY_RELEASED)
                {
                    willConsume=false;
                }
            }
        }
    };

    EventHandler<KeyEvent> handlerNumbers = new EventHandler<KeyEvent>()
    {
        private boolean willConsume=false;
        @Override
        public void handle(KeyEvent event)
        {
            JFXTextField temp = (JFXTextField) event.getSource();
            if(willConsume)
            {
                event.consume();
            }
            if(!event.getText().matches("[0-9]")&&event.getCode()!=KeyCode.BACK_SPACE)
            {
                if(event.getEventType()==KeyEvent.KEY_PRESSED)
                {
                    willConsume=true;
                }
                else if(event.getEventType()==KeyEvent.KEY_RELEASED)
                {
                    willConsume=false;
                }
            }
        }
    };
    //

    //PANELES
    public void onExitButtonClicked(MouseEvent event)
    {
        Platform.exit();
        System.exit(0);
    }
    public void onHomeButtonClicked(MouseEvent event)
    {
        homePanel.setVisible(true);
        clientPanel.setVisible(false);
        productPanel.setVisible(false);
        provPanel.setVisible(false);
        pedidoPanel.setVisible(false);
    }
    public void onClientButtonClicked(MouseEvent event)
    {
        homePanel.setVisible(false);
        clientPanel.setVisible(true);
        productPanel.setVisible(false);
        provPanel.setVisible(false);
        pedidoPanel.setVisible(false);
    }
    public void onProductButtonClicked(MouseEvent event)
    {
        homePanel.setVisible(false);
        clientPanel.setVisible(false);
        productPanel.setVisible(true);
        provPanel.setVisible(false);
        pedidoPanel.setVisible(false);
    }
    public void onProvButtonClicked(MouseEvent event)
    {
        homePanel.setVisible(false);
        clientPanel.setVisible(false);
        productPanel.setVisible(false);
        provPanel.setVisible(true);
        pedidoPanel.setVisible(false);
    }
    public void onPedidoButtonClicked(MouseEvent event)
    {
        homePanel.setVisible(false);
        clientPanel.setVisible(false);
        productPanel.setVisible(false);
        provPanel.setVisible(false);
        pedidoPanel.setVisible(true);
        time_pe.setValue(LocalTime.now());
    }
    //

    //PRODUCTOPEDIDO
    @FXML public void agregarProductoPedido(ActionEvent event)
    {
        if(comboBox_nompd.getSelectionModel().getSelectedIndex()<0)
        {
            JOptionPane.showMessageDialog(null,"Seleccione una opción de producto");
        }
        else
        {
            productoG = ProductoDAO.buscarPdNombre(comboBox_nompd.getSelectionModel().getSelectedItem().toString());
            tfnom_nuevavent1.setText(productoG.getNom());
            tfprecio_nuevavent1.setText(productoG.getPrecio()+"");
            tfcantidad_nuevavent.setText("");
        }
    }
    @FXML public void seleccionarCliente(ActionEvent event)
    {
        if(comboBox_nomcl.getSelectionModel().getSelectedIndex()<0)
        {
            JOptionPane.showMessageDialog(null,"Seleccione una opción de cliente");
        }
        else
        {
            clienteG = ClienteDAO.buscarCliente(comboBox_nomcl.getSelectionModel().getSelectedItem().toString());
            tfidcl_pe.setText(clienteG.getId()+"");
        }
    }
    @FXML public void aceptarAniadirProducto(ActionEvent event)
    {
        if(Verificador.verificarTexto(tfcantidad_nuevavent)!=1&&Verificador.verificarString(tfnom_nuevavent1.toString())!=1)
        {
            PedidoDTO pedido= PedidoDAO.buscarPedido(id_pedidoSelc);
            double subtotal;
            total=pedido.getTotal_ped();
            pedido.setId_ped(id_pedidoSelc);
            //

            ProductoPedido productoPedido = new ProductoPedido();
            productoPedido.setId_prod(productoG.getId());
            productoPedido.setId_pe(id_pedidoSelc);
            productoPedido.setNom_pp(productoG.getNom());
            productoPedido.setPrecio_pp(productoG.getPrecio());
            productoPedido.setUnid_pp(Integer.parseInt(tfcantidad_nuevavent.getText()));
            subtotal = productoPedido.getPrecio_pp()*productoPedido.getUnid_pp();
            productoPedido.setSubt_pp(subtotal);
            total=total+subtotal;
            if(total>0)
            {
                pedido.setTotal_ped(total);
            }
            else
            {
                pedido.setTotal_ped(0);
            }
            ProductoPedidoDAO.agregarProductoPedido(productoPedido);
            inicializarTablaPe();
            //
            PedidoDAO.modificarPedido(pedido);
            inicializarTablaLiPe();
            //
            tfnom_nuevavent1.setText("");
            tfcantidad_nuevavent.setText("");
            tfprecio_nuevavent1.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Campos faltantes");
        }
    }
    @FXML public void eliminarRegistroPedido(ActionEvent event)
    {
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "¿Desea eliminar esa fila?",
                "Eliminar producto",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,    // null para icono por defecto.
                new Object[] { "No", "Si" },
                "No");
        if(seleccion==1)
        {
            PedidoDTO pedido= PedidoDAO.buscarPedido(id_pedidoSelc);
            total = pedido.getTotal_ped();
            ProductoPedido pp= ProductoPedidoDAO.buscarProductoPedido(idpp);
            total= total -pp.getSubt_pp();
            if(total>0)
            {
                pedido.setTotal_ped(total);
            }
            else
            {
                pedido.setTotal_ped(0);
            }
            //
            ProductoPedidoDAO.eliminarProductoPedido(idpp);
            inicializarTablaPe();
            //
            PedidoDAO.modificarPedido(pedido);
            inicializarTablaLiPe();

            tfnom_nuevavent1.setText("");
            tfcantidad_nuevavent.setText("");
            tfprecio_nuevavent1.setText("");
            btelimpp.setDisable(true);
        }
    }
    @FXML public void cancelarAniadirProducto(ActionEvent event)
    {
        addPdPanel.setVisible(false);
        lista_pp.clear();
        tabla_pedido.setItems(null);
    }
    @FXML public void abirVentProd(ActionEvent event)
    {
        addPdPanel.setVisible(true);
        inicializarTablaPe();
    }

    //
    @FXML private void nuevoCliente(ActionEvent event)
    {
        tfid_cl.setText("ID");
        tfnom_cl.setText("");
        tfdir_cl.setText("");
        tftel_cl.setText("");
        tfmail_cl.setText("");
        btelim_cl.setDisable(true);
        btmod_cl.setDisable(true);
        btregistrar_cl.setDisable(false);
    }
    @FXML private void nuevoProveedor(ActionEvent event)
    {
        tfid_pv.setText("ID");
        tfnom_pv.setText("");
        tfdir_pv.setText("");
        tftel_pv.setText("");
        tfmail_pv.setText("");
        btelim_pv.setDisable(true);
        btmod_pv.setDisable(true);
        btregistar_pv.setDisable(false);
    }
    @FXML private void nuevoProducto(ActionEvent event)
    {
        tfid_pd.setText("ID");
        tfnom_pd.setText("");
        tfunid_pd.setText("");
        tfprecio_pd.setText("");
        btelim_pd.setDisable(true);
        btmod_pd.setDisable(true);
        btregistrar_pd.setDisable(false);
    }
    @FXML private void nuevoPedido(ActionEvent event)
    {
        total=0;
        tfid_pe.setText("ID");
        tfidcl_pe.setText("");
        tftotal_pe.setText(total+"");
        btelim_pe.setDisable(true);
        btmod_pe.setDisable(true);
        btregistrar_pe.setDisable(false);
        btprodped.setDisable(true);
        btgenerar_ticket.setDisable(true);
        btprodped.setDisable(true);
    }
    @FXML private void agregarCliente(ActionEvent event)
    {
        ClienteDTO cliente = new ClienteDTO();

        if(Verificador.verificarTexto(tfnom_cl)!=1&&
                Verificador.verificarTexto(tfdir_cl)!=1&&
                Verificador.verificarTexto(tftel_cl)!=1&&
                Verificador.verificarTexto(tfmail_cl)!=1)
        {
            cliente.setNom(tfnom_cl.getText());
            cliente.setDir(tfdir_cl.getText());
            cliente.setTel(tftel_cl.getText());
            cliente.setMail(tfmail_cl.getText());

            ClienteDAO.agregarCliente(cliente);
            inicializarTablaCl();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void agregarPedido(ActionEvent event)
    {
        PedidoDTO pedido= new PedidoDTO();
        if (Verificador.verificarTexto(tfidcl_pe)!=1&&Verificador.verificarString(date_pe.getValue().toString())!=1&&Verificador.verificarString(time_pe.getValue().toString())!=1)
        {
            pedido.setId_cl(clienteG.getId());
            pedido.setNom_cl(clienteG.getNom());
            pedido.setTotal_ped(Double.parseDouble(tftotal_pe.getText()));
            pedido.setFecha_ped(java.sql.Date.valueOf(date_pe.getValue()));
            pedido.setHora_ped(java.sql.Time.valueOf(time_pe.getValue()));
            PedidoDAO.agregarpedido(pedido);
            inicializarTablaLiPe();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void agregarProveedor(ActionEvent event)
    {
        ProveedorDTO proveedor = new ProveedorDTO();
            if(Verificador.verificarTexto(tfnom_pv)!=1&&
                    Verificador.verificarTexto(tfdir_pv)!=1&&
                    Verificador.verificarTexto(tftel_pv)!=1&&
                    Verificador.verificarTexto(tfmail_pv)!=1)
            {
                proveedor.setNom(tfnom_pv.getText());
                proveedor.setDir(tfdir_pv.getText());
                proveedor.setTel(tftel_pv.getText());
                proveedor.setMail(tfmail_pv.getText());

                ProveedorDAO.agregarProveedor(proveedor);
                inicializarTablaPv();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
            }

    }
    @FXML private void agregarProducto(ActionEvent event)
    {
        ProductoDTO producto = new ProductoDTO();
        try
        {
            if(Verificador.verificarTexto(tfnom_pd)!=1&&Verificador.verificarTexto(tfunid_pd)!=1&&Verificador.verificarTexto(tfprecio_pd)!=1)
            {
                producto.setNom(tfnom_pd.getText());
                producto.setUnid(Integer.parseInt(tfunid_pd.getText()));
                producto.setPrecio(Double.parseDouble(tfprecio_pd.getText()));
                ProductoDAO.insertarProducto(producto);
                inicializarTablaPd();
                comboBox_nompd.setItems(nombresPd);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error en llenado de campos");
        }
    }
    @FXML private void modificarCliente(ActionEvent event)
    {
        ClienteDTO cliente = new ClienteDTO();

        if(Verificador.verificarTexto(tfnom_cl)!=1&&
                Verificador.verificarTexto(tfdir_cl)!=1&&
                Verificador.verificarTexto(tftel_cl)!=1&&
                Verificador.verificarTexto(tfmail_cl)!=1)
        {
            cliente.setId(Integer.parseInt(tfid_cl.getText()));
            cliente.setNom(tfnom_cl.getText());
            cliente.setDir(tfdir_cl.getText());
            cliente.setTel(tftel_cl.getText());
            cliente.setMail(tfmail_cl.getText());

            ClienteDAO.modificarCliente(cliente, cliente.getId());
            inicializarTablaCl();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void modificarProveedor(ActionEvent event)
    {
        ProveedorDTO proveedor= new ProveedorDTO();

        if(Verificador.verificarTexto(tfnom_pv)!=1&&
                Verificador.verificarTexto(tfdir_pv)!=1&&
                Verificador.verificarTexto(tftel_pv)!=1&&
                Verificador.verificarTexto(tfmail_pv)!=1)
        {
            proveedor.setId(Integer.parseInt(tfid_pv.getText()));
            proveedor.setNom(tfnom_pv.getText());
            proveedor.setDir(tfdir_pv.getText());
            proveedor.setTel(tftel_pv.getText());
            proveedor.setMail(tfmail_pv.getText());
            ProveedorDAO.modificarProveedor(proveedor);
            inicializarTablaPv();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void modificarProducto(ActionEvent event)
    {
        ProductoDTO producto = new ProductoDTO();
        if(Verificador.verificarTexto(tfnom_pd)!=1&&Verificador.verificarTexto(tfunid_pd)!=1&&Verificador.verificarTexto(tfprecio_pd)!=1)
        {
            producto.setId(Integer.parseInt(tfid_pd.getText()));
            producto.setNom(tfnom_pd.getText());
            producto.setUnid(Integer.parseInt(tfunid_pd.getText()));
            producto.setPrecio(Double.parseDouble(tfprecio_pd.getText()));
            ProductoDAO.modificarProducto(producto);
            comboBox_nompd.setItems(nombresPd);
            inicializarTablaPd();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void modificarPedido(ActionEvent event)
    {
        PedidoDTO pedido= new PedidoDTO();
        if (Verificador.verificarTexto(tfidcl_pe)!=1&&Verificador.verificarString(date_pe.getValue().toString())!=1&&Verificador.verificarString(time_pe.getValue().toString())!=1)
        {
            pedido.setId_ped(Integer.parseInt(tfid_pe.getText()));
            pedido.setId_cl(clienteG.getId());
            ClienteDTO cliente = ClienteDAO.buscarCliente(pedido.getId_cl());
            pedido.setNom_cl(cliente.getNom());
            pedido.setTotal_ped(Double.parseDouble(tftotal_pe.getText()));
            pedido.setFecha_ped(java.sql.Date.valueOf(date_pe.getValue()));
            pedido.setHora_ped(java.sql.Time.valueOf(time_pe.getValue()));
            PedidoDAO.modificarPedido(pedido);
            inicializarTablaLiPe();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Faltan campos por llenar");
        }
    }
    @FXML private void eliminarCliente(ActionEvent event)
    {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setId(Integer.parseInt(tfid_cl.getText()));
        ClienteDAO.eliminarCliente(cliente.getId());
        inicializarTablaCl();
    }
    @FXML private void eliminarPedido(ActionEvent event)
    {
        PedidoDTO pedido = new PedidoDTO();
        pedido.setId_ped(Integer.parseInt(tfid_pe.getText()));
        PedidoDAO.eliminarPedido(pedido.getId_ped());
        inicializarTablaLiPe();
    }
    @FXML private void eliminarProveedor(ActionEvent event)
    {
        ProveedorDTO proveedor= new ProveedorDTO();
        proveedor.setId(Integer.parseInt(tfid_pv.getText()));
        ProveedorDAO.eliminarProveedor(proveedor.getId());
        inicializarTablaPv();
    }
    @FXML private void eliminarProducto(ActionEvent event)
    {
        ProductoDTO producto = new ProductoDTO();
        producto.setId(Integer.parseInt(tfid_pd.getText()));
        ProductoDAO.eliminarProducto(producto.getId());
        comboBox_nompd.setItems(nombresPd);
        inicializarTablaPd();
    }


    //TABLAS SELECCIONADAS
    private final ListChangeListener<ClienteDTO> selectorTablaCliente = new ListChangeListener<ClienteDTO>()
    {
        @Override
        public void onChanged(Change<? extends ClienteDTO> c)
        {
            datosClienteSelect();
        }
    };
    private final ListChangeListener<ProveedorDTO> selectorTablaProveedor = new ListChangeListener<ProveedorDTO>()
    {
        @Override
        public void onChanged(Change<? extends ProveedorDTO> c)
        {
            datosProveedorSelect();
        }
    };
    private final ListChangeListener<ProductoDTO> selectorTablaProducto = new ListChangeListener<ProductoDTO>()
    {
        @Override
        public void onChanged(Change<? extends ProductoDTO> c)
        {
            datosProductoSelect();
        }
    };
    private final ListChangeListener<PedidoDTO> selectorTablaLiPedido = new ListChangeListener<PedidoDTO>()
    {
        @Override
        public void onChanged(Change<? extends PedidoDTO> c)
        {
            datosLiPedidoSelect();
        }
    };
    private final ListChangeListener<ProductoPedido> selectorTablaPedido = new ListChangeListener<ProductoPedido>()
    {
        @Override
        public void onChanged(Change<? extends ProductoPedido> c)
        {
            datosPedidoSelect();
        }
    };
    public ClienteDTO getClienteSelect()
    {
        if(tabla_clientes!=null)
        {
            List<ClienteDTO> tabla_cl=tabla_clientes.getSelectionModel().getSelectedItems();
            if(tabla_cl.size()==1)
            {
                final ClienteDTO competicionSeleccionada = tabla_cl.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
    public ProveedorDTO getProveedorSelect()
    {
        if(tabla_proveedores!=null)
        {
            List<ProveedorDTO> tabla_pv=tabla_proveedores.getSelectionModel().getSelectedItems();
            if(tabla_pv.size()==1)
            {
                final ProveedorDTO competicionSeleccionada = tabla_pv.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
    public ProductoDTO getProductoSelect()
    {
        if(tabla_productos!=null)
        {
            List<ProductoDTO> tabla_pd=tabla_productos.getSelectionModel().getSelectedItems();
            if(tabla_pd.size()==1)
            {
                final ProductoDTO competicionSeleccionada = tabla_pd.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
    public PedidoDTO getLiPedidoSelect()
    {
        if(tabla_listapedidos!=null)
        {
            List<PedidoDTO> tabla_lipe=tabla_listapedidos.getSelectionModel().getSelectedItems();
            if(tabla_lipe.size()==1)
            {
                final PedidoDTO competicionSeleccionada = tabla_lipe.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }
    public ProductoPedido getPedidoSelect()
    {
        if(tabla_pedido!=null)
        {
            List<ProductoPedido> tabla_pp=tabla_pedido.getSelectionModel().getSelectedItems();
            if(tabla_pp.size()==1)
            {
                final ProductoPedido competenciaSeleccionada = tabla_pp.get(0);
                return competenciaSeleccionada;
            }
        }
        return null;
    }
    private void datosClienteSelect()
    {
        final ClienteDTO cliente = getClienteSelect();
        index_cl=lista_cl.indexOf(cliente);

        if(cliente!=null)
        {
            tfid_cl.setText(cliente.getId()+"");
            tfnom_cl.setText(cliente.getNom());
            tfdir_cl.setText(cliente.getDir());
            tftel_cl.setText(cliente.getTel());
            tfmail_cl.setText(cliente.getMail());

            btmod_cl.setDisable(false);
            btelim_cl.setDisable(false);
            btregistrar_cl.setDisable(true);
        }
    }
    private void datosProveedorSelect()
    {
        final ProveedorDTO proveedor = getProveedorSelect();
        index_pv=lista_pv.indexOf(proveedor);

        if(proveedor!=null)
        {
            tfid_pv.setText(proveedor.getId()+"");
            tfnom_pv.setText(proveedor.getNom());
            tfdir_pv.setText(proveedor.getDir());
            tftel_pv.setText(proveedor.getTel());
            tfmail_pv.setText(proveedor.getMail());

            btmod_pv.setDisable(false);
            btelim_pv.setDisable(false);
            btregistar_pv.setDisable(true);
        }
    }
    private void datosProductoSelect()
    {
        final ProductoDTO producto = getProductoSelect();
        index_pd=lista_pd.indexOf(producto);

        if(producto!=null)
        {
            tfid_pd.setText(producto.getId()+"");
            tfnom_pd.setText(producto.getNom());
            tfunid_pd.setText(producto.getUnid()+"");
            tfprecio_pd.setText(producto.getPrecio()+"");

            btmod_pd.setDisable(false);
            btelim_pd.setDisable(false);
            btregistrar_pd.setDisable(true);
        }
    }
    private void datosLiPedidoSelect()
    {
        final PedidoDTO lipedido= getLiPedidoSelect();
        index_pe=lista_pe.indexOf(lipedido);

        if(lipedido!=null)
        {
            tfid_pe.setText(lipedido.getId_ped()+"");
            tfidcl_pe.setText(lipedido.getId_cl()+"");
            date_pe.setValue(lipedido.getFecha_ped().toLocalDate());
            time_pe.setValue(lipedido.getHora_ped().toLocalTime());
            tftotal_pe.setText(lipedido.getTotal_ped()+"");
            id_pedidoSelc=Integer.parseInt(tfid_pe.getText());
            idPedidoSeleccionado=id_pedidoSelc;
            total=Double.parseDouble(tftotal_pe.getText());

            btmod_pe.setDisable(false);
            btelim_pe.setDisable(false);
            btregistrar_pe.setDisable(true);
            btprodped.setDisable(false);
            btgenerar_ticket.setDisable(false);
        }
    }
    private void datosPedidoSelect()
    {
        final ProductoPedido productoPedido = getPedidoSelect();
        index_pp=lista_pp.indexOf(productoPedido);
        if(productoPedido!=null)
        {
            btelimpp.setDisable(false);
            idpp=productoPedido.getId_pp();
        }
    }
    private void inicializarTablaCl()
    {
        nombresCl.clear();
        tfid_cl.setText("ID");
        btmod_cl.setDisable(true);
        btelim_cl.setDisable(true);
        btregistrar_cl.setDisable(true);

        col_idcl.setCellValueFactory(new PropertyValueFactory<ClienteDTO,Integer>("id"));
        col_nomcl.setCellValueFactory(new PropertyValueFactory<ClienteDTO,String>("nom"));
        col_dircl.setCellValueFactory(new PropertyValueFactory<ClienteDTO,String>("dir"));
        col_telcl.setCellValueFactory(new PropertyValueFactory<ClienteDTO,String>("tel"));
        col_mailcl.setCellValueFactory(new PropertyValueFactory<ClienteDTO,String>("mail"));
        lista_cl=ClienteDAO.getDataClienteDTO();
        tabla_clientes.setItems(lista_cl);

        final ObservableList<ClienteDTO> tablaClienteSel = tabla_clientes.getSelectionModel().getSelectedItems();
        tablaClienteSel.addListener(selectorTablaCliente);

        for(ClienteDTO cliente: ClienteDAO.getDataClienteDTO())
        {
            nombresCl.add(cliente.getNom());
        }
        comboBox_nomcl.setItems(nombresCl);
    }
    private void inicializarTablaPd()
    {
        nombresPd.clear();
        tfid_pd.setText("ID");
        btmod_pd.setDisable(true);
        btelim_pd.setDisable(true);
        btregistrar_pd.setDisable(true);

        col_idpd.setCellValueFactory(new PropertyValueFactory<ProductoDTO,Integer>("id"));
        col_nompd.setCellValueFactory(new PropertyValueFactory<ProductoDTO,String>("nom"));
        col_unidpd.setCellValueFactory(new PropertyValueFactory<ProductoDTO,Integer>("unid"));
        col_preciopd.setCellValueFactory(new PropertyValueFactory<ProductoDTO,Double>("precio"));
        lista_pd=ProductoDAO.getDataProductoDTO();
        tabla_productos.setItems(lista_pd);

        final ObservableList<ProductoDTO> tablaProductoSel = tabla_productos.getSelectionModel().getSelectedItems();
        tablaProductoSel.addListener(selectorTablaProducto);

        for(ProductoDTO producto: ProductoDAO.getDataProductoDTO())
        {
            nombresPd.add(producto.getNom());
        }
        comboBox_nompd.setItems(nombresPd);
    }
    private void inicializarTablaPv()
    {
        tfid_pv.setText("ID");
        btmod_pv.setDisable(true);
        btelim_pv.setDisable(true);
        btregistar_pv.setDisable(true);


        col_idpv.setCellValueFactory(new PropertyValueFactory<ProveedorDTO,Integer>("id"));
        col_nompv.setCellValueFactory(new PropertyValueFactory<ProveedorDTO,String>("nom"));
        col_dirpv.setCellValueFactory(new PropertyValueFactory<ProveedorDTO,String>("dir"));
        col_telpv.setCellValueFactory(new PropertyValueFactory<ProveedorDTO,String>("tel"));
        col_mailpv.setCellValueFactory(new PropertyValueFactory<ProveedorDTO,String>("mail"));
        lista_pv=ProveedorDAO.getDataProveedorDTO();
        tabla_proveedores.setItems(lista_pv);

        final ObservableList<ProveedorDTO> tablaProveedorSel = tabla_proveedores.getSelectionModel().getSelectedItems();
        tablaProveedorSel.addListener(selectorTablaProveedor);
    }
    public void inicializarTablaLiPe()
    {
        btmod_pe.setDisable(true);
        btelim_pe.setDisable(true);
        btregistrar_pe.setDisable(true);
        btprodped.setDisable(true);

        col_idlipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,Integer>("id_ped"));
        col_idcllipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,Integer>("id_cl"));
        col_nomlipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,String>("nom_cl"));
        col_totallipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,Double>("total_ped"));
        col_fechalipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,Date>("fecha_ped"));
        col_horalipe.setCellValueFactory(new PropertyValueFactory<PedidoDTO,Time>("hora_ped"));
        lista_pe=PedidoDAO.getDataPedidoDTO();
        tabla_listapedidos.setItems(lista_pe);

        final ObservableList<PedidoDTO> tablaLiPedidoSel = tabla_listapedidos.getSelectionModel().getSelectedItems();
        tablaLiPedidoSel.addListener(selectorTablaLiPedido);
    }
    private void inicializarTablaPe()
    {
        col_idpe.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Integer>("id_pe"));
        col_idprod.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Integer>("id_prod"));
        col_index.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Integer>("id_pp"));
        col_nompe.setCellValueFactory(new PropertyValueFactory<ProductoPedido, String>("nom_pp"));
        col_prepe.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Double>("precio_pp"));
        col_unidpe.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Integer>("unid_pp"));
        col_subpe.setCellValueFactory(new PropertyValueFactory<ProductoPedido, Double>("subt_pp"));

        lista_pp=ProductoPedidoDAO.getDataProductoPedido(id_pedidoSelc);
        tabla_pedido.setItems(lista_pp);

        final ObservableList<ProductoPedido> tablaPedidoSel= tabla_pedido.getSelectionModel().getSelectedItems();
        tablaPedidoSel.addListener(selectorTablaPedido);
    }

    //TICKET
    @FXML public void cerrarTicket(ActionEvent event)
    {
        ticket_panel.setVisible(false);
    }

    public static void createFile(String dir){
        File ticketData = new File(dir);
        try{
            //Si se especifica "ticketData.txt" como dirección, la
            //localización del archivo es la misma ruta que ticket.java, cambiar si es necesario
            if(!ticketData.isFile()){
                BufferedWriter writer = new BufferedWriter(new FileWriter(ticketData));
                //Condición se cumple si el archivo no existe
                //Texto dentro del archivo.txt
                String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                writer.write("BIENVENIDO!!\nArchivo creado en: "+formattedDate+"\n");
                //Texto dentro del archivo .txt
                writer.close();
            }
        }catch(IOException err){
            JOptionPane.showMessageDialog(null,"Error al generar ticket");
        }
    }

    public static void appendFile(String dir, List<String> order){
        File ticketData = new File(dir);
        try{
            if(ticketData.isFile()){

                BufferedWriter writer = new BufferedWriter(new FileWriter(ticketData, false));
                PedidoDTO pedido = PedidoDAO.buscarPedido(idPedidoSeleccionado);
                writer.write("\t\tR E C I B O   D E   C O M P R A\n\n");
                writer.flush();
                writer.write("\t\tVilla Chago S.A. de C.V.\n\n");
                writer.flush();
                writer.write("\t\tId del pedido: "+pedido.getId_ped()+"\n\n");
                writer.flush();
                writer.write("\t\tNombre del cliente: "+pedido.getNom_cl()+"\n\n");
                writer.flush();
                writer.write("\t\t============================================\n\n");
                writer.flush();
                writer.write("\t\tProducto" + "\t\t" + "Precio" + "\t\t" + "Unidades" + "\t\t\t" + "Subtotal"+"\n");
                writer.flush();
                for(int i=0;i< order.size();i++)
                {
                    writer.write("\t\t"+order.get(i)+"\n");
                }
                writer.flush();
                writer.write("\t\t============================================\n\n");
                writer.flush();
                writer.write("\t\tFecha de la compra:\t"+pedido.getFecha_ped()+"\n\n");
                writer.flush();
                writer.write("\t\tHora de la compra:\t"+pedido.getHora_ped()+"\n\n");
                writer.flush();
                writer.write("\t\tTOTAL DE LA COMPRA:\t$"+pedido.getTotal_ped()+"\n\n");
                writer.flush();
                writer.write(" \n\n\t\tCreado por ODCAJKN SYSTEM");
                writer.flush();
                writer.close();
            }
        }catch(IOException err){
            JOptionPane.showMessageDialog(null,"Error al generar ticket");
        }
    }

    @FXML public void abirTicket(ActionEvent event)
    {
        try
        {
            List<String> order = new ArrayList<String>();
            for(ProductoPedido pp:ProductoPedidoDAO.getDataProductoPedido(id_pedidoSelc))
            {
                if (pp.getNom_pp().length()>8)
                {
                    String acorta;
                    acorta=pp.getNom_pp().substring(0,8);
                    pp.setNom_pp(acorta);
                }
                order.add(pp+"");
            }
            String dir="ticketData"+idPedidoSeleccionado+".txt";
            createFile(dir);
            appendFile(dir,order);
            FileReader fr = new FileReader(dir);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            String cadena="";
            while((linea=br.readLine())!=null)
            {
                cadena+=linea+"\n";
            }
            taTicket.setText(cadena);
            ticket_panel.setVisible(true);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al generar ticket");
        }
    }
    //


    //INICIO
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Disables
        addPdPanel.setVisible(false);
        clientPanel.setVisible(false);
        provPanel.setVisible(false);
        productPanel.setVisible(false);
        pedidoPanel.setVisible(false);
        homePanel.setVisible(true);
        ticket_panel.setVisible(false);

        btelimpp.setDisable(true);
        tfidcl_pe.setDisable(true);
        tfid_pe.setDisable(true);
        tfid_cl.setDisable(true);
        tfid_pd.setDisable(true);
        tfid_pv.setDisable(true);
        tftotal_pe.setDisable(true);
        btgenerar_ticket.setDisable(true);
        tftotal_pe.setText(total+"");
        taTicket.setEditable(false);

        lista_pp.clear();
        date_pe.setValue(LocalDate.now());
        date_pe.setEditable(false);
        time_pe.set24HourView(true);
        time_pe.setValue((LocalTime.now()));
        time_pe.setEditable(false);

        //Filtrado de Texto y Números
        tfunid_pd.addEventFilter(KeyEvent.ANY,handlerNumbers);
        tfcantidad_nuevavent.addEventFilter(KeyEvent.ANY,handlerNumbers);
        id_pedidoSelc=-2;
        total=0;
        //
        inicializarTablaCl();
        inicializarTablaPd();
        inicializarTablaPv();
        inicializarTablaPe();
        inicializarTablaLiPe();
    }
}
