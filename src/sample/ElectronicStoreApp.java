package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {

    private ElectronicStoreView mView;
    private ElectronicStore mModel;

    @Override
    public void start(Stage primaryStage) throws Exception{

        mModel = ElectronicStore.createStore();
        mView = new ElectronicStoreView(mModel);

        primaryStage.setTitle(mModel.name);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mView, 940, 470));
        primaryStage.show();

        mView.updateView();

        mView.getStoreStock().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mView.updateView();
            }
        });

        mView.getCurCart().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mView.updateView();
            }
        });

        mView.getAddCart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int product = mView.getStoreStock().getSelectionModel().getSelectedIndex();
                if(product != -1){
                    int actIndex = -1;
                    while(actIndex < product){
                        if(mModel.getProducts()[actIndex + 1].getStockQuantity() <= 0){
                            product += 1;
                        }
                        actIndex += 1;
                    }
                    mModel.addToCart(product);
                    mView.updateView();
                }
            }
        });

        mView.getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int product = mView.getCurCart().getSelectionModel().getSelectedIndex();
                mModel.removeCart(product);
                mView.updateView();
            }
        });

        mView.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mModel.sellCart();
                mModel.emptyCart();
                mView.updateView();
            }
        });

        mView.getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mModel = ElectronicStore.createStore();
                mView.setModel(mModel);
                mView.updateView();
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
