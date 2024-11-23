package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class HelloApplication extends Application {

    // ArrayList to store submitted data
    private final ArrayList<UserData> userDataList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Form Screen
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setHgap(10);
        formPane.setVgap(10);

        // Input Fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label fatherNameLabel = new Label("Father's Name:");
        TextField fatherNameField = new TextField();

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("Male");
        maleButton.setToggleGroup(genderGroup);
        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setToggleGroup(genderGroup);

        ImageView personIcon = new ImageView(new Image("https://w7.pngwing.com/pngs/527/663/png-transparent-logo-person-user-person-icon-rectangle-photography-computer-wallpaper-thumbnail.png")); // Replace with your image URL or file path
        personIcon.setFitHeight(80);
        personIcon.setFitWidth(80);

        Label imageLabel = new Label("Image:");
        Button uploadButton = new Button("Upload Image");
        Label imagePathLabel = new Label();
        FileChooser fileChooser = new FileChooser();
        final File[] selectedImage = {null};



        uploadButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                selectedImage[0] = file;
                imagePathLabel.setText(file.getAbsolutePath());
            }
        });

        Button submitButton = new Button("Submit");

        formPane.add(nameLabel, 0, 0);
        formPane.add(nameField, 1, 0);
        formPane.add(fatherNameLabel, 0, 1);
        formPane.add(fatherNameField, 1, 1);
        formPane.add(cityLabel, 0, 2);
        formPane.add(cityField, 1, 2);
        formPane.add(addressLabel, 0, 3);
        formPane.add(addressField, 1, 3);
        formPane.add(emailLabel, 0, 4);
        formPane.add(emailField, 1, 4);
        formPane.add(genderLabel, 0, 5);
        formPane.add(maleButton, 1, 5);
        formPane.add(femaleButton, 2, 5);
        formPane.add(imageLabel, 0, 6);
        formPane.add(personIcon, 1, 6);
        formPane.add(uploadButton, 2, 6);
        formPane.add(imagePathLabel, 3, 6);
        formPane.add(submitButton, 1, 7);

        // Yellow banner with "Hello World" text
        Region yellowBar = new Region();
        yellowBar.setStyle("-fx-background-color: yellow;");
        yellowBar.setPrefHeight(50);

        Label bannerText = new Label("BANNER");
        bannerText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        StackPane banner = new StackPane(yellowBar, bannerText);

        // Wrap formPane in VBox with yellow banner
        VBox formContainer = new VBox(banner, formPane);

        Scene formScene = new Scene(formContainer, 500, 400);

        // Display Screen
        VBox displayPane = new VBox(10);
        displayPane.setPadding(new Insets(10));
        displayPane.setAlignment(Pos.TOP_LEFT);
        Label displayLabel = new Label();
        ImageView displayImage = new ImageView();
        Button backButton = new Button("Back");
        displayPane.getChildren().addAll(displayLabel, displayImage, backButton);

        Scene displayScene = new Scene(displayPane, 500, 400);

        // Button Actions
        submitButton.setOnAction(e -> {
            // Collect input data
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String city = cityField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "Not specified";

            // Handle image
            String imagePath = (selectedImage[0] != null) ? selectedImage[0].getAbsolutePath() : "No Image Selected";

            // Add to ArrayList
            UserData userData = new UserData(name, fatherName, city, address, email, gender, imagePath);
            userDataList.add(userData);

            // Display data
            displayLabel.setText(
                    "Name: " + name + "\n" +
                            "Father's Name: " + fatherName + "\n" +
                            "City: " + city + "\n" +
                            "Address: " + address + "\n" +
                            "Email: " + email + "\n" +
                            "Gender: " + gender
            );

            if (selectedImage[0] != null) {
                displayImage.setImage(new Image(selectedImage[0].toURI().toString()));
                displayImage.setFitHeight(150);
                displayImage.setFitWidth(150);
            } else {
                displayImage.setImage(null);
            }

            primaryStage.setScene(displayScene);
        });

        backButton.setOnAction(e -> {
            nameField.clear();
            fatherNameField.clear();
            cityField.clear();
            addressField.clear();
            emailField.clear();
            genderGroup.selectToggle(null);
            imagePathLabel.setText("");
            selectedImage[0] = null;
            primaryStage.setScene(formScene);
        });

        primaryStage.setTitle("Simple Form Application");
        primaryStage.setScene(formScene);
        primaryStage.show();
    }

    // Data class to store user information
    static class UserData {
        private final String name;
        private final String fatherName;
        private final String city;
        private final String address;
        private final String email;
        private final String gender;
        private final String imagePath;

        public UserData(String name, String fatherName, String city, String address, String email, String gender, String imagePath) {
            this.name = name;
            this.fatherName = fatherName;
            this.city = city;
            this.address = address;
            this.email = email;
            this.gender = gender;
            this.imagePath = imagePath;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Father's Name: " + fatherName + ", City: " + city +
                    ", Address: " + address + ", Email: " + email + ", Gender: " + gender +
                    ", Image Path: " + imagePath;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
