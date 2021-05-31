package knightschess.model;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardState {
    public List<List<Integer>> chessBoard =  new ArrayList<List<Integer>>(64);

    public List<Pair> possibleMoves = new ArrayList<>();

    public void initializeBoard(){
        for(int i = 0; i < 8; i++){
            chessBoard.add(new ArrayList<>());
            for(int j = 0; j < 8; j++){
                chessBoard.get(i).add(0);
            }
        }
        chessBoard.get(0).set(0,2);
        chessBoard.get(7).set(7,3);
    }

    public List<Pair> showPossibleMoves(Pair pair) {
        List<Pair> possibleMoves = new ArrayList<>();
        int row = pair.getRow();
        int col = pair.getColumn();


        if(row - 1 >= 0 && row - 1 <= 7) {
            if(col - 2 >= 0 && col - 2 <= 7){
                if(chessBoard.get(row-1).get(col-2) == 0) {
                    possibleMoves.add(new Pair(row - 1,col - 2));
                }
            }
            if(col + 2 >= 0 && col + 2 <= 7){
                if(chessBoard.get(row-1).get(col+2) == 0) {
                    possibleMoves.add(new Pair(row - 1,col + 2));
                }
            }
        }
        if(row + 1 >= 0 && row + 1 <= 7) {
            if(col - 2 >= 0 && col - 2 <= 7){
                if(chessBoard.get(row+1).get(col-2) == 0) {
                    possibleMoves.add(new Pair(row + 1,col - 2));
                }
            }
            if(col + 2 >= 0 && col + 2 <= 7){
                if(chessBoard.get(row+1).get(col+2) == 0) {
                    possibleMoves.add(new Pair(row + 1,col + 2));
                }
            }
        }
        if(col - 1 >= 0 && col - 1 <= 7) {
            if(row - 2 >= 0 && row - 2 <= 7){
                if(chessBoard.get(row-2).get(col-1) == 0) {
                    possibleMoves.add(new Pair(row - 2,col - 1));
                }
            }
            if(row + 2 >= 0 && row + 2 <= 7){
                if(chessBoard.get(row+2).get(col-1) == 0) {
                    possibleMoves.add(new Pair(row + 2,col - 1));
                }
            }
        }
        if(col + 1 >= 0 && col + 1 <= 7) {
            if(row - 2 >= 0  && row - 2 <= 7){
                if(chessBoard.get(row-2).get(col+1) == 0) {
                    possibleMoves.add(new Pair(row - 2,col + 1));
                }
            }
            if(row + 2 >= 0 && row + 2 <= 7){
                if(chessBoard.get(row+2).get(col+1) == 0) {
                    possibleMoves.add(new Pair(row + 2,col + 1));
                }
            }
        }
        return possibleMoves;
    }

    public boolean isKnightMoveValid(PlayerState playerState,int row, int col) {
        Pair posPair = playerState.getMoveList().get(0);
        Pair destPair = new Pair(row, col);
        int state = chessBoard.get(row).get(col);
        if (possibleMoves.contains(destPair) && state != 2 && state != 3  && state != 1) {
            if(playerState.isPlayer1Turn()) {
                chessBoard.get(row).set(col, 2);
            }
            else {
                chessBoard.get(row).set(col, 3);
            }
            chessBoard.get(posPair.getRow()).set(posPair.getColumn(),1);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isGameFinished() {
        if(possibleMoves.isEmpty()){
            return true;
        }
        return false;
    }
}
