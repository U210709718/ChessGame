public class Bishop extends Piece{
    /*
    How does it move? like letter X, so we have to check Diagonals!
    can't move when pawn is in front of it .

     */
    public Bishop(int color , Square location){ //parametered constructor.
        super(color, location); //use the key word super to call the parametric constructor from the Piece class (The parent class)
    }

    //since it moves like ( X ) we have to check Diagonals !
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        Square [] between = location.getBoard().getSquaresBetween(location,targetLocation);
        if(this.location.isDiagonal(targetLocation)){
            for (Square square : between){
                validMove = square.isEmpty();
            }
            if(!validMove){
                if(targetLocation.getPiece() != null && targetLocation.getPiece().isEnemy(this))
                    validMove = true;
            }
            return validMove;
        }

        return false;
    }
    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }


}
