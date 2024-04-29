public class Pawn extends Piece { //the relation here is inheritance from the Piece class (Parent class)

    /*
    1-first step can be two then it will be 1.
    2-eats a piece diagonally. (The very first diagonal)
    3-if the pawn reaches the last row it becomes a Queen!
     */


    boolean initialLocation = true; //the piece here hasn't been moved yet

    public Pawn(int color, Square location) { ////parameterized constructor for Pawn
        super(color, location); //use the key word super to call the parametric constructor from the Piece class (The parent class)
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        //Getting the difference between the current location and target location.
        int rowDistance = targetLocation.getRowDistance(location);

        //if the target coordinate is at the same column
        if (this.location.isAtSameColumn(targetLocation)) {
            //for white check pawn is moving forward at most 2 Squares
            if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        //pawn is moving twice , check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
                //for black check pawn is moving forward at most 2 Squares
            } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                if (rowDistance == -2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
            // if the target column is not at the same column, it should be a neighbour column
        } else if (this.location.isNeighborColumn(targetLocation)) {
            //pawn can only move to forward diagonals if there is an attack
            if (color == ChessBoard.WHITE && rowDistance == 1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.BLACK;
            } else if (color == ChessBoard.BLACK && rowDistance == -1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() == ChessBoard.WHITE;
            }

        }
        return validMove;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        //turning the pawn into Queen if It's at the last row!
        if (targetLocation.isAtLastRow(color)) {
            targetLocation.putNewQueen(color); //we will turn the pawn into Queen
        } else {
            targetLocation.setPiece(this);
        }
        //clear previous location
        location.clear();
        //update current location
        location = targetLocation;
        location.getBoard().nextPlayer();
        //piece has been moved at least once
        initialLocation = false;

    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p"; //This is if else statement (?) means if , (:) means else
        // if the color is white so write P , if black write p
    }
}

