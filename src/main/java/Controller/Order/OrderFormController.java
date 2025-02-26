package Controller.Order;

import DBConnection.DBConnection;
import Model.*;
import Service.Custom.CustomerService;
import Service.Custom.ItemService;
import Service.ServiceFactory;
import Util.ServiceType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class OrderFormController implements Initializable {
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXComboBox comboBox;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXComboBox comboBoxItem;
    public TableView tlbOrder;
    public TableColumn colItemID;
    public TableColumn colDescription;
    public TableColumn colQtyInHand;
    public TableColumn ColUnitPrice;
    public TableColumn ColTotal;
    public JFXTextField txtQty;
    public Label txtTotalView;
    public Label lblOrderID;
    private ActionEvent actionEvent;

    public void txtCode(ActionEvent actionEvent) {
    }

//    public void addAction(ActionEvent actionEvent) {
//    }
//
//    public void updateaction(ActionEvent actionEvent) {
//    }
//
//    public void searchAction(ActionEvent actionEvent) {
//    }
//
//    public void delateaction(ActionEvent actionEvent) {
//    }

    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadCustomerId() {
        try {
            comboBox.setItems(customerService.getCustomerId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemId() {
        try {
            comboBoxItem.setItems(itemService.getId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDataInTlb();
        loadDateAndTime();

        loadItemId();
        loadCustomerId();
        generateOrderID();

        comboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadCustomerDatatxt((String) newValue);
            }

        });

        comboBoxItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadItemDatatxt((String) newValue);
            }

        });
    }

    private void loadCustomerDatatxt(String id) {
        try {
            Customer customer = customerService.searchCustomer(id);
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtSalary.setText(customer.getSalary().toString());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadItemDatatxt(String id) {
        try {
            Item item = itemService.search(id);
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(item.getUnitPrice().toString());
            txtQtyOnHand.setText(item.getQtyOnHand().toString());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    ObservableList<OrderCart> orderCartObservableList = FXCollections.observableArrayList();

    public void addTOCartAction(ActionEvent actionEvent) {

        if (Integer.parseInt(txtQty.getText()) < Integer.parseInt(txtQtyOnHand.getText())) {

            Double total = Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtQty.getText());

            orderCartObservableList.add(new OrderCart(
                    comboBoxItem.getValue().toString(),
                    txtDescription.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    total
            ));
            tlbOrder.setItems(orderCartObservableList);
            calcTotal();
        } else {
            new Alert(Alert.AlertType.WARNING, "Enter Valid QTY Number").show();
        }

    }

    private void addDataInTlb() {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQtyInHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private void calcTotal() {
        Double total = 0.0;
        for (OrderCart order : orderCartObservableList) {
            total += order.getTotal();
        }
        txtTotalView.setText(total.toString());
    }

//    public int isAlreadyExists(String code) {
//        for (int i = 0; i < tlbOrder.getItems().size(); i++) {
//            String tempCode = (String) tlbOrder.getColumns().get(0).getCellData(i);
//            if (tempCode.equals(code)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public void OrderPlaceAction(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        List<OrderDetail> orderDetails = new ArrayList<>();

        orderCartObservableList.forEach(orderCart -> orderDetails.add(new OrderDetail(
                lblOrderID.getText(),
                orderCart.getItemcode(),
                orderCart.getQty(),
                orderCart.getUnitPrice()

        )));

        Order order = new Order(
                lblOrderID.getText(),
                lblDate.getText(),
                comboBox.getValue().toString(),
                orderDetails
        );

        try {
            boolean isAddedOrder = OrderController.placeOrder(order);
            if (isAddedOrder) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Added Fail").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
        }
        generateOrderID();
    }

    private void generateOrderID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
            ResultSet rst = stm.executeQuery();

            String newOrderId;
            if (rst.next()) {
                String lastId = rst.getString("OrderID");
                int lastNumber = Integer.parseInt(lastId.substring(1));
                int newNumber = lastNumber + 1;
                newOrderId = String.format("D%03d", newNumber);
            } else {
                newOrderId = "D001";
            }
            lblOrderID.setText(newOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void delateaction(ActionEvent actionEvent) {
    }
}
