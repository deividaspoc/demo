package com.example.demo;

import User.Books;
import User.BooksDAO;
import User.UserDAO;
import User.UserSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label groupLabel, usernameLabel;
    @FXML
    private Label statusLabel;

    @FXML
    private TextField titleField;

    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextArea summaryField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField photoField;
    @FXML
    private TextField pagesField;
    @FXML
    private TextField categoryField;
    // Lentelės stulpeliai
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn summaryColumn;
    @FXML
    private TableColumn isbnColumn;
    @FXML
    private TableColumn photoColumn;
    @FXML
    private TableColumn pagesColumn;
    @FXML
    private TableColumn categoryCollumn;
    @FXML
    private TableColumn user_idCollumn;

    @FXML
    private TableView bookTableView;
    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Prisijungimo langas");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    ObservableList<Books> list = FXCollections.observableArrayList();
    @FXML
    public void onToBooked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("booked-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Rezervuotos knygos");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    @FXML
    public void searchButtonClick() {
        list.clear();
        String nameField2 = nameField.getText();

        List<Books> bookList = BooksDAO.searchByName(nameField2);

        for (Books book : bookList) {

            list.add(new Books(book.getId(),book.getName(),book.getSummary(),book.getIsbn(),book.getPhoto(),book.getPages(),book.getCategory(),book.getUser_id()));



            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            photoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
            pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
            categoryCollumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            user_idCollumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

            bookTableView.setItems(list);
        }
        /*
        if (bookList.isEmpty()) {
            status.setText("Nepavyko atlikti paieška pagal filmų pavadinimą");
        } else {
            status.setText("Pavyko atlikti paieška pagal filmų pavadinimą");
        }*/
    }
    @FXML
    public void onCreateButtonClick() {
        String nameField2 = nameField.getText();
        String summaryField2 = summaryField.getText();
        String isbnField2 = isbnField.getText();
        String photoField2 = photoField.getText();
        String pagesField2 = pagesField.getText();
        String categoryField2 = categoryField.getText();

        // Tikriname pagal Validacijas
        if (!Validation.isValidTitle(nameField2)) {
            statusLabel.setText("Neteisingai įvedėt pavadinimą");
        } else {
            // keiciame kintamuju tipus pagal konstruktoriu
            int pages = Integer.parseInt(pagesField.getText());
           // int year2 = Integer.parseInt(year);
            //int secondsField3 = Integer.parseInt(secondsField.getText());

            // Kuriame įrašą DB
            //int userID = UserDAO.searchByUsernameReturnID(usernameLabel.getText());

            Books book = new Books(nameField2,summaryField2,isbnField2,photoField2,pages,categoryField2);
            BooksDAO.create(book);
            statusLabel.setText("Pavyko sukurti įrašą");
        }
    }
    @FXML
    public void onBookButtonClick(){
        int id = Integer.parseInt(idField.getText());
        int x;
        x = BooksDAO.isBooked(id);
        if(x==-1){
            statusLabel.setText("Knyga jau rezervuota kito vartotojo");
        }
        else if(x==0){
            BooksDAO.book(id);
            statusLabel.setText("Knyga sekmingai rezervuota");
        }
    }
    @FXML
    public void onUpdateButtonClick() {
        String nameField2 = nameField.getText();
        String summaryField2 = summaryField.getText();
        String isbnField2 = isbnField.getText();
        String photoField2 = photoField.getText();
        String categoryField2 = categoryField.getText();

        if(groupLabel.getText().equals("ADMINISTRATORIUS")){
                // keiciame kintamuju tipus pagal konstruktoriu
                int pages = Integer.parseInt(pagesField.getText());
                int id = Integer.parseInt(idField.getText());

                Books book = new Books(id, nameField2,summaryField2,isbnField2,photoField2,pages,categoryField2);
                BooksDAO.update(book);
                statusLabel.setText("Pavyko paredaguoti įrašą");
        } else{
            // Vartotojas
            statusLabel.setText("Redaguoti įraša gali tik administratorius. SORIUKAS");
        }


    }

    @FXML
    public void onDeleteButtonClick() {
        String idField2 = idField.getText();

        if(groupLabel.getText().equals("ADMINISTRATORIUS")) {
            if (!Validation.isValidTime(idField2)) {
                statusLabel.setText("Neteisingai įvestas ID");
            } else {
                int idField3 = Integer.parseInt(idField.getText());
                BooksDAO.deleteById(idField3);
                statusLabel.setText("Pavyko sėkmingai ištrinti įrašą");
            }
        } else{
            // Vartotojas
            statusLabel.setText("Trinti įraša gali tik administratorius. SORIUKAS");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Parodomas prisijunges vartotojas
        String username = UserSingleton.getInstance().getUserName();
        usernameLabel.setText(username);

        // Parodoma prisijungusio vartotojo role(grupe)
        String role = UserDAO.searchByUsername(username);
        groupLabel.setText(role);

    }
}
