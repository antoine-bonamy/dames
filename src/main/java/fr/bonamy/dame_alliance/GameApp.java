package fr.bonamy.dame_alliance;

import fr.bonamy.dame_alliance.controller.GameController;
import fr.bonamy.dame_alliance.model.Game;
import fr.bonamy.dame_alliance.view.BoardView;
import fr.bonamy.dame_alliance.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameController gameController = setupGame(stage);
        stage.show();
    }

    private GameController setupGame(Stage stage) {
        Game game = new Game();
        GameView gameView = new GameView(new BoardView());
        GameController gameController = new GameController(game, gameView);
        Scene scene = new Scene(gameView, 800, 800);
        stage.setScene(scene);
        stage.setTitle("Dame Alliance");
        stage.setMinHeight(800);
        stage.setMinWidth(800);
        return gameController;
    }

    public static void main(String[] args) {
        launch();
    }

}