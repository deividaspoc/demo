package com.example.demo;

import User.Books;
import User.BooksDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BookedController {

    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableView bookedBooks;

    @FXML
    public void onBackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("dashboard-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Knygu langas");
        LoginStage.setScene(new Scene(root, 1280, 720));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    ObservableList<Books> list = FXCollections.observableArrayList();
    @FXML
    public void onRefreshButton(){
        List<Books> bookList = BooksDAO.getBookNames();

        for (Books book : bookList) {
            //list.clear();
            list.add(new Books(book.getId(),book.getName()));

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            bookedBooks.setItems(list);
        }
    }
}
