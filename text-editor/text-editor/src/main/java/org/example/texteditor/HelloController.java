package org.example.texteditor;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.net.URI;
import java.security.cert.Extension;
import java.util.Optional;
import java.util.stream.Collectors;


public class HelloController {


    @FXML
    private TextArea text;

    @FXML
    private Spinner<Integer> spinner;
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
    File file = null;
    boolean isSaved = false;
    String bg = "-fx-control-inner-background: #ffffff;";
    String color = "-fx-text-fill: #000000;";


    @FXML
    protected void fontsize(){
        int size = spinner.getValue();
        Font currentFont = text.getFont();
        String style = currentFont.getStyle();
        String family = currentFont.getFamily();  // Font ailesini al

        // Stil (Bold, Italic) kontrol et
        FontWeight weight = FontWeight.NORMAL;
        FontPosture posture = FontPosture.REGULAR;

        if (style.contains("Bold")) {
            weight = FontWeight.BOLD;
        }
        if (style.contains("Italic")) {
            posture = FontPosture.ITALIC;
        }

        text.setFont(Font.font(family, weight, posture, size));
    }




    @FXML
    protected void close() {

        Stage stage = (Stage) this.text.getScene().getWindow();
        if(isSaved == false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uygulamayı Kapat");
            alert.setHeaderText("Çıkmak istediğinize emin misiniz?");
            alert.setContentText("Kaydedilmemiş değişiklikleriniz kaybolabilir.");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("Uygulama kapatılıyor...");
                stage.close(); // Uygulamayı kapat
            } else {
                System.out.println("Kapatma işlemi iptal edildi.");
            }

        }

        else {
            stage.close();
        }


    }

    @FXML
    public void initialize() {
        float size = Float.parseFloat(String.valueOf(text.getFont().getSize()));
        int n_size = Math.round(size);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,72,n_size,1));

    }

    @FXML
    protected void save() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dosya Aç");
        fileChooser.getExtensionFilters().addAll(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        if (file != null) {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(text.getText());
            writer.flush();
            writer.close();
            isSaved = true;

        }

    }


    @FXML
    protected void diffsave() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dosya Kaydet");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(extensionFilter);

        // Dosya seçim diyalogunu aç
        File file = fileChooser.showSaveDialog(this.text.getScene().getWindow());

        // Eğer kullanıcı bir dosya seçtiyse veya yeni bir dosya adı girdiyse
        if (file != null) {
            // Eğer dosya yoksa, oluştur
            if (!file.exists()) {
                file.createNewFile();
            }

            // Dosyayı yaz
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(text.getText());
                writer.flush();
            }

            // Kaydedildi durumunu ayarla
            isSaved = true;
        }
    }


    @FXML


    protected void open() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Dosyayı Kaydet");
        fileChooser.getExtensionFilters().addAll(extensionFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        file = fileChooser.showOpenDialog(this.text.getScene().getWindow());

        if (file != null) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            text.setText(reader.lines().collect(Collectors.joining("\n"))) ;


        }
    }


    @FXML
    private CheckBox kalin;

    @FXML
    private CheckBox ital;


    @FXML
    public void change(){

        FontWeight weight = FontWeight.NORMAL;  // Varsayılan kalınlık
        FontPosture posture = FontPosture.REGULAR;  // Varsayılan duruş


        // Kalınlık kontrolü
        if (kalin.isSelected()) {
            weight = FontWeight.BOLD;
        }

        // İtalik kontrolü
        if (ital.isSelected()) {
            posture = FontPosture.ITALIC;
        }

        // Font ayarlarını uygula
        text.setFont(Font.font(text.getFont().getFamily(), weight, posture, text.getFont().getSize()));

        // Altı çizili stilini uygula

    }

    @FXML
    protected void bgred(){
        text.setStyle("-fx-control-inner-background: #c04000;"+color);
        bg = "-fx-control-inner-background: #c04000;";
    }

    @FXML
    protected void bgblue(){
        text.setStyle("-fx-control-inner-background: #005eff;"+color);
        bg = "-fx-control-inner-background: #005eff;";
    }

    @FXML
    protected void bgreen(){
        text.setStyle("-fx-control-inner-background: #248104;"+color);
        bg = "-fx-control-inner-background: #248104;";
    }

    @FXML
    protected void bgyellow(){
        text.setStyle("-fx-control-inner-background: #ffc84f;"+color);
        bg = "-fx-control-inner-background: #ffc84f;";
    }

    @FXML
    protected void bgblack(){
        text.setStyle("-fx-control-inner-background: #000000;" + color);
        bg = "-fx-control-inner-background: #000000;";
    }

    @FXML
    protected void bgwhite(){
        text.setStyle("-fx-control-inner-background: #ffffff;"+color);
        bg = "-fx-control-inner-background: #ffffff;";
    }


    @FXML
    protected void arial(){
        Font font = text.getFont();
        String style = font.getStyle();
        FontWeight weight = style.contains("Bold") ? FontWeight.BOLD : FontWeight.NORMAL;
        FontPosture posture = style.contains("Italic") ? FontPosture.ITALIC : FontPosture.REGULAR;
        text.setFont(Font.font("Arial",weight,posture,text.getFont().getSize()));
    }

    @FXML
    protected void times(){
        Font font = text.getFont();
        String style = font.getStyle();
        FontWeight weight = style.contains("Bold") ? FontWeight.BOLD : FontWeight.NORMAL;
        FontPosture posture = style.contains("Italic") ? FontPosture.ITALIC : FontPosture.REGULAR;
        text.setFont(Font.font("Times New roman",weight,posture,text.getFont().getSize()));
    }

    @FXML
    protected void courier(){
        Font font = text.getFont();
        String style = font.getStyle();
        FontWeight weight = style.contains("Bold") ? FontWeight.BOLD : FontWeight.NORMAL;
        FontPosture posture = style.contains("Italic") ? FontPosture.ITALIC : FontPosture.REGULAR;
        text.setFont(Font.font("Courier",weight,posture,text.getFont().getSize()));
    }

    @FXML
    protected void blue(){
        text.setStyle("-fx-text-fill: blue;"+bg);
        color = "-fx-text-fill: blue;";
    }

    @FXML
    protected void red(){
        text.setStyle("-fx-text-fill: red;"+bg);
        color = "-fx-text-fill: red;";
    }

    @FXML
    protected void yellow(){

        text.setStyle("-fx-text-fill: yellow;"+bg);
        color = "-fx-text-fill: yellow;";
    }

    @FXML
    protected void left() {
        Node metin= text.lookup(".text");
        metin.setStyle(text.getStyle()+"-fx-text-alignment: left;");

    }

    @FXML
    protected void right() {
        Node metin= text.lookup(".text");
        metin.setStyle(text.getStyle()+"-fx-text-alignment: right;");

    }

    @FXML
    protected void center() {
        Node metin= text.lookup(".text");
        metin.setStyle(text.getStyle()+"-fx-text-alignment: center;");

    }




    @FXML

    protected void help(){

        try {
            String url = "https://www.google.com";
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("linux")) {
                // Linux ortamı için xdg-open
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                // Diğer işletim sistemleri için Desktop sınıfı
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI(url));
                } else {
                    throw new UnsupportedOperationException("Desktop sınıfı desteklenmiyor!");
                }
            }
        } catch (Exception e) {
            System.err.println("Link açılamadı: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

