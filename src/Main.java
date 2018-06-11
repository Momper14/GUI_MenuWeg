
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private MenuBar menu;
    private BorderPane root;
    private Label text;

    @Override
    public void start(Stage primaryStage) {
        text = new Label();
        text.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (event.getClickCount() == 2) {
                    root.setTop(menu);
                }
            }
        });

        root = new BorderPane(text);
        initMenu();

        Scene scene = new Scene(root, 300, 250);

        this.primaryStage = primaryStage;
        setTitel("Hier könnte ihre Werbung stehen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setTitel(String titel) {
        primaryStage.setTitle(titel);
        text.setText(titel);
    }

    private void initMenu() {
        Menu menuDatei = new Menu("Datei"),
                menuTitel = new Menu(),
                menuWeg = new Menu();

        MenuItem itemClose = new MenuItem("Beenden");
        menuDatei.getItems().setAll(itemClose);

        itemClose.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });

        Label label = new Label("Titel Ändern");
        label.setOnMouseClicked((MouseEvent event) -> {
            titelAendern();
        });
        menuTitel.setGraphic(label);

        label = new Label("MenuWeg");
        label.setOnMouseClicked((MouseEvent event) -> {
            menuWeg();
        });
        menuWeg.setGraphic(label);

//        menuWeg.setOnShown((Event event) -> {
//            menuWeg.hide();
//            menuTitel.hide();
//            Platform.runLater(() -> {
//                menuWeg();
//            });
//        });
//        menuTitel.setOnShown((Event event) -> {
//            menuTitel.hide();
//            neuerTitel();
//        });
//        menuWeg.getItems().addAll(new MenuItem());
//        menuTitel.getItems().addAll(new MenuItem());
        menu = new MenuBar(menuDatei, menuTitel, menuWeg);
        root.setTop(menu);
    }

    private void titelAendern() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Neuer Titel");
        dialog.setContentText("Neuer Titel:");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(titel -> setTitel(titel));
    }

    private void menuWeg() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        root.setTop(null);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
