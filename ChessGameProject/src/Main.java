import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in); //to take an input from the user
        ChessBoard board = new ChessBoard(); //creating a ChessBoard instance.
        System.out.println(board); //printing the board
        while (!board.isGameEnded()){ //creating a while loop for : checking if the game doesn't end ask the user moves
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn!");

            //* Mark : null can only be given to objects ! the constrictor gives default values for objects initial value is  = null
            Piece piece = null; //giving the piece of kind Piece object an initial value of null, which means empty

            do { //Do while loop : While the given coordinate does not contain the piece of the current player
                //request a coordinate
                System.out.print("Enter the location of the piece:");
                String from = reader.next();
                //get the piece at the given location
                piece = board.getPieceAt(from);
                //piece's color and current player's color should be consistent as they are!
            }while(piece == null || piece.getColor()!=(board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK));
            String to = null;
            do {  //Do while loop to check this : while the target coordinate is not valid Square to move, request new coordinate
                System.out.print("Enter the new location of the piece:");
                to = reader.next();
            }while(!piece.canMove(to));
            //move the piece to the target location that user choose!
            piece.move(to);
            //print the board after the valid move !
            System.out.println(board);
        }
        reader.close();
    }
}
