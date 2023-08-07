package lk.ijse.thogakadepos.util;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case CUSTOMER:
                window.setTitle("Customer Manage Form");
                initUI("CustomerForm.fxml");
                break;
            case ITEM:
                window.setTitle("Item Manage Form");
                initUI("ItemForm.fxml");
                break;
            case PLACE_ORDER:
                window.setTitle("Place Order Form");
                initUI("OrderForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle("DashBoard Form");
                initUI("Dashboard.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                        .getResource("/lk/ijse/thogakadepos/view/" + location)));
    }
}
