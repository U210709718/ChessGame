public abstract class Piece  {
    public int color; //implementing the color of piece (it could be WHITE =0  , Black =1)
    public Square location; // implementing the current location of the piece on the chessboard!


    public Piece(int color, Square location) { //parametric constructor to take the color  and  Square location as parameters
        // we are using this to avoid shadowing and set the color and location
        this.color = color;
        this.location = location;
        this.location.setPiece(this); // setting this piece on a specific location on the chessboard
    }

    public int getColor() { //create a getter, so I can get the color (the return value type of the getter is the same as the value! )
        return color; // returning the value type which is (the color of the piece)
    }

    public abstract boolean canMove(String to); //Abstract method to check if the move is a valid move or not!


    public void move(String to){ //method to move all pieces to the users choosed location!
        Square targetLocation = location.getBoard().getSquareAt(to); //getting the target location square
        targetLocation.setPiece(this); //setting the piece on the previous target location!
        location.clear(); //clear the prevoious location!
        location = targetLocation; //update the location to the target location
        location.getBoard().nextPlayer(); //process to next player turn! for example white will make the first move for sure then black will make a move , the same player can't play twice at the same round!

    }

    protected boolean isEnemy(Piece p){ //this method is used in Bishop to check if the given piece is an Enemy piece.
        return !(this.color == p.color); // return true if the colors are different (they are enemy pieces)!
    }
}
