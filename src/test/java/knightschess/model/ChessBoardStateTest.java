package knightschess.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardStateTest {
    private ChessBoardState chessBoardState = new ChessBoardState();

    @Test
    void initializeBoard() {
        chessBoardState.initializeBoard();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(!(i == 0 && j == 0) && !(i == 7 && j == 7)){
                    assertEquals(0,chessBoardState.chessBoard.get(i).get(j));
                }
            }
        }
        assertEquals(2,chessBoardState.chessBoard.get(0).get(0));
        assertEquals(3,chessBoardState.chessBoard.get(7).get(7));
    }

    @Test
    void isGameFinished() {
        assertTrue(chessBoardState.isGameFinished());

        chessBoardState.possibleMoves.add(new Pair(0,0));
        assertFalse(chessBoardState.isGameFinished());

    }
}