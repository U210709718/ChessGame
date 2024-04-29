public class King extends Piece{
    //all the game we are trying to protect it !
    //it can move just one square forward , backwards, right , left to do the checkmate! But checkmate isn't implemented in the game!!

    public King(int color , Square location){ //parameterized constructor.
        super(color, location); //use the key word super to call the parametric constructor from the Piece class (The parent class)
    }
    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);
        if(this.location.isAtSameColumn(targetLocation)) {
            if(rowDistance == 1 || rowDistance == -1) {
                validMove = (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color);
            }
        } else if(this.location.isNeighborColumn(targetLocation)) {
            if(colDistance == 1 || colDistance == -1) {
                validMove = (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color);
            }
        }
        return validMove;
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
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
