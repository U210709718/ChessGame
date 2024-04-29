import java.text.DecimalFormat;
public class Knight extends Piece{

    //limited steps
    //knight moves like letter L
    //the only piece can move even if there is a pawn in front of it
    // one two three then left or right
    //we have 2 knights
    //يقدر يقفز وينعطف الوحيد ويعمل حرف L

    public Knight (int color , Square location){ //parameterized constructor.
        super(color, location); //use the key word super to call the parametric constructor from the Piece class (The parent class)
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);
        if((rowDistance == 1 || rowDistance == -1) && (colDistance == 2 || colDistance == -2)) {
            validMove = (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color);
        }else if((rowDistance == 2 || rowDistance == -2) && (colDistance == 1 || colDistance == -1)) {
            validMove = (targetLocation.isEmpty() || targetLocation.getPiece().getColor() != color);
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
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
