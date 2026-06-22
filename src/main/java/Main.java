import interfaz.menu_principal.MenuPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mafia");
        stage.setScene(new MenuPrincipal());
        stage.show();
    }
}