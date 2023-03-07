package com.exercise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class Application extends javafx.application.Application {
    private String[] coursesComputer = {"Java", "C#", "Python"};
    private String[] coursesBusiness = {"Business 101", "Administration", "Economic"};
    private String[] courses = {"Java", "C#", "Python", "Business 101", "Administration", "Economic"};
    private TextArea tArea;
    private ListView<String> lv;
    private ObservableList data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        //create the grid pane for entries
        GridPane personalInformationPanel = new GridPane();

        //personal information style
        personalInformationPanel.setHgap(5);
        personalInformationPanel.setVgap(5);
        personalInformationPanel.setStyle(
                "-fx-padding: 10;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: grey;");

        //create labels
        Label lblTittle = new Label("Student Information:");
        Label lblFullName = new Label("Full Name:");
        Label lblAddress = new Label("Address:");
        Label lblCity = new Label("City:");
        Label lblProvince = new Label("Province:");
        Label lblPostalCode = new Label("Postal Code:");
        Label lblPhoneNumber = new Label("Phone Number:");
        Label lblEmail = new Label("Email:");

        //create text fields
        TextField txtFullName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtCity = new TextField();
        TextField txtProvince = new TextField();
        TextField txtPostalCode = new TextField();
        TextField txtPhoneNumber = new TextField();
        TextField txtEmail = new TextField();


        //create buttons
        Button btnDisplay = new Button("Display");

        personalInformationPanel.add(lblTittle, 0, 0);
        //add controls to grid pane
        personalInformationPanel.add(lblFullName, 0, 1);
        personalInformationPanel.add(txtFullName, 1, 1);

        personalInformationPanel.add(lblAddress, 0, 2);
        personalInformationPanel.add(txtAddress, 1, 2);

        personalInformationPanel.add(lblCity, 0, 3);
        personalInformationPanel.add(txtCity, 1, 3);

        personalInformationPanel.add(lblProvince, 0, 4);
        personalInformationPanel.add(txtProvince, 1, 4);

        personalInformationPanel.add(lblPostalCode, 0, 5);
        personalInformationPanel.add(txtPostalCode, 1, 5);

        personalInformationPanel.add(lblPhoneNumber, 0, 6);
        personalInformationPanel.add(txtPhoneNumber, 1, 6);

        personalInformationPanel.add(lblEmail, 0, 7);
        personalInformationPanel.add(txtEmail, 1, 7);

        personalInformationPanel.add(btnDisplay, 1, 10);

        //align buttons in grid pane
        GridPane.setHalignment(btnDisplay, HPos.RIGHT);

        //place grid pane in the center of border pane
        borderPane.setCenter(personalInformationPanel);


        //create the toggle group to group Student major box
        ToggleGroup studentGroup = new ToggleGroup();

        //create radio buttons and add them to the toggle group
        RadioButton rb1 = new RadioButton("Computer Science");
        rb1.setToggleGroup(studentGroup);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Business");
        rb2.setToggleGroup(studentGroup);

        //create the box pane and place to the right
        VBox studentBox = new VBox();

        //set the style
        studentBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: grey;");

        //set the title of box pane
        Text studentBoxTitle = new Text("Student Major:");
        studentBox.getChildren().add(studentBoxTitle);
        studentBox.getChildren().addAll(rb1, rb2);


        //create check buttons
        CheckBox cb1 = new CheckBox("Student Council");
        CheckBox cb2 = new CheckBox("Volunteer Work");

        //create the box pane and place to the right
        VBox activityBox = new VBox();

        //set the style
        activityBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: grey;");

        //set the title of box pane
        Text activityBoxTitle = new Text("Extra Activities:");
        activityBox.getChildren().add(activityBoxTitle);
        activityBox.getChildren().addAll(cb1, cb2);

        //create courses box
        VBox coursesBox = new VBox();
        Text coursesTitle = new Text("Courses");
        coursesBox.getChildren().add(coursesTitle);

        //set courses box style
        coursesBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: grey;");

        //set list of courses
        ComboBox<String> cbo = new ComboBox<>();
        ObservableList<String> items = FXCollections.observableArrayList(courses);

        cbo.getItems().addAll(FXCollections.observableArrayList(coursesComputer));
        cbo.setPrefSize(200, 50);
        lv = new ListView<>(FXCollections.observableArrayList());
        lv.setPrefSize(200, 200);

        // Display the selected courses
        cbo.setOnAction(e -> setDisplay( items.indexOf(cbo.getValue()) ));
        rb1.setOnAction(e -> setCoursesList(true, cbo));
        rb2.setOnAction(e -> setCoursesList(false, cbo));

        coursesBox.getChildren().addAll(cbo, lv);

        HBox topRightBox = new HBox();
        topRightBox.getChildren().addAll(activityBox, studentBox);
        VBox rightBox = new VBox();
        rightBox.getChildren().addAll(topRightBox, coursesBox);


        borderPane.setRight(rightBox);


        //create the text area
        tArea = new TextArea();

        // Create a scroll pane to hold the text area
        ScrollPane scrollPane = new ScrollPane(tArea);

        //handle click events
        btnDisplay.setOnAction(e -> displayEntries(
                txtFullName,
                txtAddress,
                txtCity,
                txtProvince,
                txtPostalCode,
                txtPhoneNumber,
                txtEmail,
                cb1,
                cb2
        ));

        //set the scroll pane to the bottom of border pane
        scrollPane.setFitToWidth(true);
        borderPane.setBottom(scrollPane);


        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 600, 450);

        primaryStage.setTitle("Student Info"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    public void setDisplay(int index) {
        if (index == -1) return;
        if (data.indexOf(courses[index]) == -1) {
            data.add(courses[index]);
            lv.setItems(data);
        }
    }

    // display entries in text area
    public void displayEntries(
            TextField txtFullName,
            TextField txtAddress,
            TextField txtCity,
            TextField txtProvince,
            TextField txtPostalCode,
            TextField txtPhoneNumber,
            TextField txtEmail,
            CheckBox cb1 ,
            CheckBox cb2
    ) {
        String fullName = txtFullName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();


        tArea.appendText(
                fullName + ", " + address + ", " + city + ", " + province + ", " + postalCode + ", " + phoneNumber + ", " + email);
        tArea.appendText("\n");
        tArea.appendText("Extra Activities: ");
        if (cb1.isSelected()){
            tArea.appendText( cb1.getText() +" \n");
        }
        if (cb2.isSelected()){
            tArea.appendText( cb2.getText() +" \n");
        }
        tArea.appendText("Students Courses: ");
        for (int i = 0; i < lv.getItems().size(); i++)
            tArea.appendText(lv.getItems().get(i) + ", ");

        tArea.appendText("\n-----------------------\n");
    }

    public void setCoursesList(boolean isComputerScience, ComboBox<String> cbo) {
        if (isComputerScience) {
            ObservableList<String> items = FXCollections.observableArrayList(coursesComputer);
            cbo.setItems(items);
        } else {
            ObservableList<String> items = FXCollections.observableArrayList(coursesBusiness);
            cbo.setItems(items);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}