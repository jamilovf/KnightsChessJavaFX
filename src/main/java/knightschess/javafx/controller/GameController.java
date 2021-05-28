package knightschess.javafx.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import knightschess.model.ChessBoardState;
import knightschess.model.Pair;
import knightschess.model.PlayerState;

import java.util.List;

public class GameController {
    private ChessBoardState chessBoardState = new ChessBoardState();

    private PlayerState playerState = new PlayerState();

    @FXML
    private GridPane gridPane;

    private Image knightBlack = new Image(getClass().getResource("/images/knightBlack.png").toExternalForm());

    private Image knightWhite = new Image(getClass().getResource("/images/knightWhite.png").toExternalForm());

    private Image moveImage = new Image(getClass().getResource("/images/move.png").toExternalForm());

    @FXML
    public void initialize(){
        chessBoardState.initializeBoard();
        System.out.println(playerState.getMoveList());
    }

    public void handleClickOnCell(MouseEvent mouseEvent){
        var row= GridPane.getRowIndex((Node) mouseEvent.getSource());
        var column= GridPane.getColumnIndex((Node) mouseEvent.getSource());

        ImageView im = (ImageView) mouseEvent.getTarget();

        if(playerState.getMoveList().isEmpty() && ChessBoardState.chessBoard.get(row).get(column) == 2){
            Pair pair = new Pair(row,column);
            playerState.getMoveList().add(pair);
            List<Pair> possibleMoves = chessBoardState.showPossibleMoves(pair);
            showPossibleMovesOnBoard(possibleMoves);
        }
        else if(playerState.getMoveList().size() == 1 && ChessBoardState.chessBoard.get(row).get(column) == 0){
            playerState.getMoveList().add(new Pair(row,column));
        }
    }

    private void showPossibleMovesOnBoard(List<Pair> possibleMoves) {
        System.out.println(possibleMoves);
        for(Pair p: possibleMoves){
            ImageView imageView = getImageViewFromGridPane(gridPane,p.getRow(),p.getColumn());
            imageView.setImage(moveImage);
        }
    }

    private ImageView getImageViewFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                System.out.println(node);
                Pane pane = (Pane) node;
                System.out.println(pane.getChildren());
                Node node1 = pane.getChildren().get(0);
               ImageView imageView  = (ImageView) node1;
                return imageView;
            }
        }
        return null;
    }

}
