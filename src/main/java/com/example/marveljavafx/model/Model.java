package com.example.marveljavafx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Model {
    private final ObservableList<Comic> comics;
    public Model(){
        this.comics = FXCollections.observableArrayList();

    }

    public ObservableList<Comic> getComics(){
        return this.comics;
    }
}
