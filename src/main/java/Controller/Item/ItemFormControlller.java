package Controller.Item;

import DBConnection.DBConnection;
import Model.Item;
import Model.OrderDetail;
import Service.Custom.ItemService;
import Service.ServiceFactory;
import Util.ServiceType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormControlller implements Initializable {

    public TableColumn tlbCode;
    public TableColumn tlbDes;
    public TableColumn tlbPrice;
    public TableColumn tlbQty;
    public Label lblDate;
    public Label lblTime;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton search;

    @FXML
    private TableView<Item> tlbItem;

    @FXML
    private JFXTextField txtDes;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtcode;

    @FXML
    private JFXButton update;
    ItemService service = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    @FXML
    void addAction(ActionEvent event) throws RuntimeException {
        Item item = new Item(
                txtcode.getText(),
                txtDes.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        try {
            if (service.save(item)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item added").show();
                loadtable();
            }
            txtcode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHand.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void delateaction(ActionEvent event) {
        try {

            if (service.delete(txtcode.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted").show();
                loadtable();
            }
            txtcode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHand.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchAction(ActionEvent event) {

        try {
            Item item = service.search(txtcode.getText());
            if (item != null) {
                txtDes.setText(item.getDescription());
                txtPrice.setText(item.getUnitPrice() + "");
                txtQtyOnHand.setText(item.getQtyOnHand() + "");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtCode(ActionEvent event) {

        try {
            Item item = service.search(txtcode.getText());
            if (item != null) {
                txtDes.setText(item.getDescription());
                txtPrice.setText(item.getUnitPrice() + "");
                txtQtyOnHand.setText(item.getQtyOnHand() + "");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateaction(ActionEvent event) {
        Item item = new Item(
                txtcode.getText(),
                txtDes.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        try {
            if (service.update(item)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
                loadtable();
            }
            txtcode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHand.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    List<Item> ItemList = new ArrayList<>();

    private void loadtable() {

        try {
            List<Item> itemList = service.getAll();
            ObservableList<Item> ItemOBList = FXCollections.observableArrayList(itemList);

            tlbItem.setItems(ItemOBList);

            tlbCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            tlbDes.setCellValueFactory(new PropertyValueFactory<>("description"));
            tlbPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            tlbQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadtable();
        tlbItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setValues(newValue);
            }
        });
    }

    private void setValues(Item item) {
        txtcode.setText(item.getCode());
        txtDes.setText(item.getDescription());
        txtPrice.setText(item.getUnitPrice().toString());
        txtQtyOnHand.setText(item.getQtyOnHand().toString());
    }

    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderUpdate : orderDetails) {
            boolean isUpdateStock = updateStock(orderUpdate);
            if (!isUpdateStock) {
                return false;
            }
        }
        return true;
    }

    public boolean updateStock(OrderDetail orderDetails) {
        String sql = "UPDATE item SET QtyOnHand = QtyOnHand - ? WHERE ItemCode=?";

        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setObject(1, orderDetails.getQty());
            stm.setString(2, orderDetails.getItemCode());
            return stm.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

