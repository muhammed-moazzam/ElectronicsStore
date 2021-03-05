package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane {

    private ElectronicStore model;

    private Label titleLabel;
    private Label salesLabel;
    private Label revenueLabel;
    private Label averageLabel;
    private TextField salesField;
    private TextField revenueField;
    private TextField averageField;
    private Label titleLabel2;
    private ListView<Product> popularItems;
    private Button reset;

    private Label titleLabel3;
    private ListView<Product> storeStock;
    private Button addCart;

    private Label titleLabel4;
    private ListView<Product> curCart;
    private Button removeCart;
    private Button completeSale;



    public ElectronicStoreView(ElectronicStore model){

        this.model = model;

        Pane firstPane = new Pane();


        firstPane.setPrefSize(200,430);
        firstPane.relocate(10, 10);

        titleLabel = new Label("Store Summary");
        titleLabel.setPrefSize(200, 25);
        titleLabel.setAlignment(Pos.CENTER);
        salesLabel = new Label("# Sales:");
        salesLabel.relocate(0, 25);
        salesLabel.setPrefSize(90, 40);
        salesLabel.setAlignment(Pos.CENTER_RIGHT);
        revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(0, 75);
        revenueLabel.setPrefSize(90, 40);
        revenueLabel.setAlignment(Pos.CENTER_RIGHT);
        averageLabel = new Label("$ / Sale:");
        averageLabel.relocate(0, 125);
        averageLabel.setPrefSize(90, 40);
        averageLabel.setAlignment(Pos.CENTER_RIGHT);

        salesField = new TextField();
        salesField.relocate(100, 25);
        salesField.setPrefSize(100, 40);
        salesField.setEditable(false);

        revenueField = new TextField();
        revenueField.relocate(100, 75);
        revenueField.setPrefSize(100, 40);
        revenueField.setEditable(false);

        averageField = new TextField();
        averageField.relocate(100, 125);
        averageField.setPrefSize(100, 40);
        averageField.setEditable(false);

        titleLabel2 = new Label("Most Popular Items");
        titleLabel2.setPrefSize(200, 25);
        titleLabel2.relocate(0,170);
        titleLabel2.setAlignment(Pos.CENTER);

        popularItems = new ListView<>();
        popularItems.setPrefSize(200, 170);
        popularItems.relocate(0, 200);

        reset = new Button("Reset Store");
        reset.setPrefSize(175, 40);
        reset.relocate(12.5, 395);


        firstPane.getChildren().addAll(titleLabel, salesLabel, revenueLabel, averageLabel, salesField, revenueField, averageField, titleLabel2, popularItems, reset);

        Pane secondPane = new Pane();
        secondPane.setPrefSize(350,430);
        secondPane.relocate(220, 10);

        titleLabel3 = new Label("Store Stock");
        titleLabel3.setPrefSize(350, 25);
        titleLabel3.setAlignment(Pos.CENTER);

        storeStock = new ListView<>();
        storeStock.setPrefSize(350, 345);
        storeStock.relocate(0, 25);

        addCart = new Button("Add to Cart");
        addCart.setPrefSize(175, 40);
        addCart.relocate(82.5, 395);

        secondPane.getChildren().addAll(titleLabel3, storeStock, addCart);

        Pane thirdPane = new Pane();
        thirdPane.setPrefSize(350,430);
        thirdPane.relocate(580, 10);

        titleLabel4 = new Label("Current Cart ($0.00):");
        titleLabel4.setPrefSize(350, 25);
        titleLabel4.setAlignment(Pos.CENTER);

        curCart = new ListView<>();
        curCart.setPrefSize(350, 345);
        curCart.relocate(0, 25);

        removeCart = new Button("Remove from Cart");
        removeCart.setPrefSize(175, 40);
        removeCart.relocate(0, 395);

        completeSale = new Button("Complete Sale");
        completeSale.setPrefSize(175, 40);
        completeSale.relocate(175, 395);

        thirdPane.getChildren().addAll(titleLabel4, curCart, removeCart, completeSale);

        getChildren().addAll(firstPane, secondPane, thirdPane);
    }

    public ListView<Product> getPopularItems() { return popularItems; }
    public Button getReset() { return reset; }
    public ListView<Product> getStoreStock() { return storeStock; }
    public Button getAddCart() { return addCart; }
    public ListView<Product> getCurCart() { return curCart; }
    public Button getRemoveCart() { return removeCart; }
    public Button getCompleteSale() { return completeSale; }

    public void updateView(){

        storeStock.setItems(FXCollections.observableArrayList(model.getStock()));
        curCart.setItems(FXCollections.observableArrayList(model.getCart()));
        salesField.setText(String.valueOf(model.getNum_sales()));
        revenueField.setText(String.valueOf(model.getRevenue()));
        titleLabel4.setText("Current Cart ($" + model.cartTotal() + ")");
        popularItems.setItems(FXCollections.observableArrayList(model.mostPopular()));

        if(model.getNum_sales() == 0){
            averageField.setText("N/A");
        }else {
            String average = String.valueOf(model.getRevenue() / model.getNum_sales());
            averageField.setText(average);
        }

        if(storeStock.getSelectionModel().getSelectedIndex() != -1){
            addCart.setDisable(false);
        }else{
            addCart.setDisable(true);
        }

        if(curCart.getSelectionModel().getSelectedIndex() != -1){
            removeCart.setDisable(false);
        }else{
            removeCart.setDisable(true);
        }

        if(model.getCart().isEmpty()){
            completeSale.setDisable(true);
        }else {
            completeSale.setDisable(false);
        }
    }

    public void setModel(ElectronicStore newModel){
        this.model = newModel;
    }


}
