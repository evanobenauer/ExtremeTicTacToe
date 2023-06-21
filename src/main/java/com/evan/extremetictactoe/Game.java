package com.evan.extremetictactoe;

import com.ejo.glowlib.misc.Container;
import com.ejo.glowlib.setting.Setting;
import com.evan.extremetictactoe.board.*;
import com.evan.extremetictactoe.board.util.FXTranslator;
import com.evan.extremetictactoe.controllers.Actions;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Game {

    /**
     * Game Settings
     */
    public static Container<Image> player1Avatar = new Container<>(null);
    public static Container<Image> player2Avatar = new Container<>(null);

    public static Setting<String> player1AvatarPath = new Setting<>("Player1Avatar",null);
    public static Setting<String> player2AvatarPath = new Setting<>("Player2Avatar",null);

    public static Setting<String> player1Name = new Setting<>("Player1Name","Player 1");
    public static Setting<String> player2Name = new Setting<>("Player2Name","Player 2");
    public static Setting<Boolean> darkMode = new Setting<>("DarkMode",false);

    private Parent scene;
    private final MainBoard mainBoard;

    private final Piece turnIndicator;
    private final Text turnIndicatorText;

    private int turnNumber = 0;


    public Game(Parent scene) {
        this.scene = scene;
        this.mainBoard = new MainBoard();
        this.turnIndicator = new Piece(null);
        this.turnIndicatorText = new Text(null);
    }

    public Game() {
        this(null);
    }


    public void initialize(Parent scene) {
        this.scene = scene;
        getMainBoard().setScene(scene);

        initializeAvatarImages();
        initializeTurnIndicator();
        initializeBoardObjects();
    }

    public void initializeAvatarImages() {
        if (!(player1AvatarPath.get() == null))
            try {
                player1Avatar.set(new Image(Game.player1AvatarPath.get()));
            } catch (Exception e) {
                //
            }

        if (!(player2AvatarPath.get() == null))
            try {
                player2Avatar.set(new Image(Game.player1AvatarPath.get()));
            } catch (Exception e) {
                //
            }
        if (player1Avatar.get() == null) //The null checks makes sure that there is not a values already saved to the image
            player1Avatar.set(new Image(getClass().getResourceAsStream("board/x.png")));
        if (player2Avatar.get() == null)
            player2Avatar.set(new Image(getClass().getResourceAsStream("board/o.png")));
    }

    public void initializeTurnIndicator() {
        getScene().getChildren().add(getTurnIndicatorImage()); //Adds the turn indicator piece to the anchor pane
        getScene().getChildren().add(getTurnIndicatorText()); //Adds the turn indicator text to the anchor pane
        Actions.setTurnIndicator(Piece.Team.X); //Sets the starting turn indicator
    }

    public void initializeBoardObjects() {
        for (int i = 9; i < 18; i++) {
            SubBoard subBoard = new SubBoard(FXTranslator.getSubBoardPane(getMainBoard().getMainBoardPane(),i)); //Registers all SubBoard objects
            getMainBoard().getSubBoards().add(subBoard); //Add each SubBoard to the list in the MainBoard
            for (int j = 0; j < 9; j++) {
                Tile tile = new Tile(FXTranslator.getTilePane(subBoard.getSubBoardPane(),j)); //Register all tile objects
                subBoard.getTiles().add(tile); //Add all tile objects to the tiles list in their SubBoard
                tile.addButton(new TileButton(tile));//Adds a button to each Tile at index = 0;
            }
        }
    }


    public Piece getTurnIndicatorImage() {
        return turnIndicator;
    }

    public Text getTurnIndicatorText() {
        return turnIndicatorText;
    }


    public void nextTurn() {
        turnNumber += 1;
    }

    public void resetTurnNumber() {
        turnNumber = 0;
    }

    public int getTurnNumber() {
        return turnNumber;
    }


    public AnchorPane getScene() {
        return (AnchorPane) scene;
    }

    public MainBoard getMainBoard() {
        return mainBoard;
    }

}
