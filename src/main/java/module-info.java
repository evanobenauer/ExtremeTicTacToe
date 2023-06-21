module com.evan.extremetictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires GlowLib;

    opens com.evan.extremetictactoe to javafx.fxml;
    exports com.evan.extremetictactoe;
    exports com.evan.extremetictactoe.controllers;
    opens com.evan.extremetictactoe.controllers to javafx.fxml;
    exports com.evan.extremetictactoe.board;
    opens com.evan.extremetictactoe.board to javafx.fxml;
    exports com.evan.extremetictactoe.board.util;
    opens com.evan.extremetictactoe.board.util to javafx.fxml;
}