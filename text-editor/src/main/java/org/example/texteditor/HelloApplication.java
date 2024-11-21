package org.example.texteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("text-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 440);
        stage.setTitle("Text Editor");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setOnCloseRequest(event -> {
            event.consume();

            closeApplication(stage);
        });
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }


    private void closeApplication(Stage stage) {
        // Onay dialogu oluştur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Uygulamayı Kapat");
        alert.setHeaderText("Çıkmak istediğinize emin misiniz?");
        alert.setContentText("Kaydedilmemiş değişiklikleriniz Olabilir");

        // Kullanıcının yanıtını al
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Uygulama kapatılıyor...");
            stage.close(); // Uygulamayı kapat
        } else {
            System.out.println("Kapatma işlemi iptal edildi.");
        }
    }
    public static void main(String[] args) {
        launch();
    }
}