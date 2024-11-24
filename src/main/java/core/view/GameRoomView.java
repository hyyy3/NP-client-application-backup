package core.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class GameRoomView {

    private VBox chatBox;
    private ScrollPane scrollPane;
    private TextField inputField;
    private Button sendButton;
    private Parent layout;

    public GameRoomView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/core/view/gameroom.fxml"));
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the game room view.");
        }
    }

    public Parent getLayout() {
        return layout;
    }
}