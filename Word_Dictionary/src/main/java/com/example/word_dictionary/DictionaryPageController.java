package com.example.word_dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DictionaryPageController {
    private DictionarySaver db;
    @FXML
    TextField search;
    @FXML
    TextField newword;
    @FXML
    TextField meaning;
    @FXML
    TextField findword;

    @FXML
    public void searchButton(MouseEvent event) throws IOException, ClassNotFoundException {
        db= new DictionarySaver();

        db.deserializeHashMap();

        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dictionary");

        if(search.getText().equals("")){
            alert.setContentText("Please enter the word to search");
            alert.showAndWait();
        }

        if(db.getDictionaryList().containsKey(search.getText().toLowerCase())) {
            alert.setContentText("The word is available in Dictionary");
            alert.showAndWait();
        }
        else {
            alert.setContentText("New Word is not available in Dictionary");
            alert.showAndWait();
        }
    }

    @FXML
    public void Add(MouseEvent event) throws IOException, ClassNotFoundException {
        db= new DictionarySaver();
        db.deserializeHashMap();

        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dictionary");
        if(newword.getText().equals("") || meaning.getText().equals("")){
            alert.setContentText("Please enter word/meaning to add to the dictionary");
            alert.showAndWait();
        }
        else if(db.getDictionaryList().containsKey(newword.getText().toLowerCase())) {
            alert.setContentText("New Word already in dictionary");
            alert.showAndWait();
        }
        else {
            db.getDictionaryList().put(newword.getText().toLowerCase(), meaning.getText());
            db.serializeHashMap();
            alert.setContentText("New Word is added to Dictionary");
            alert.showAndWait();
        }
    }

    @FXML
    public void Meaning(MouseEvent event) throws IOException, ClassNotFoundException {
        db = new DictionarySaver();
        db.deserializeHashMap();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Meaning");

        if (findword.getText().equals("")) {
            alert.setContentText("Please enter the word to find meaning");
            alert.showAndWait();
        } else if (db.getDictionaryList().containsKey(findword.getText().toLowerCase())) {
            //            System.out.println(db.getDictionaryList().get(findword.getText()));
            alert.setContentText(db.getDictionaryList().get(findword.getText().toLowerCase()));
            alert.showAndWait();

        } else {
            alert.setContentText("New Word is not available in Dictionary");
            alert.showAndWait();

        }
    }
}
