public class Square {
    /*        MARKS
    1-a square object contains justa one piece !
    2- one square only contains one piece
    idea : list of pieces

     look at Date time Obligation !
     Date date
     Time time;
     */
    private int row; //private integer instance called row (the row number of the square)
    private int col; //private integer instance called col , which is column (the column number of the square)
    public Piece piece; //Since the relation between the Piece and Square is Obligation so the piece located on the square !
    public ChessBoard board;  //Since the relation between the  Square is Obligation so the chessboard that the square belongs to.



    public Square(int row , int col , ChessBoard board) { //parametric constructor for Square class
        // we are using this to avoid shadowing since name of instance is the same with the parameters of the constructor!
        this.row = row;
        this.col = col;
        this.board = board;
    }
    public Square(int row , int col) { //parametric constructor for Square class, but with different signature(means different parameters)
        // we are using this to avoid shadowing since name of instance is the same with the parameters of the constructor!
        this.row = row;
        this.col= col;
    }
    public ChessBoard getBoard() { //a public method which is reachable everywhere that will get or returns the ChessBoard object
        return board;
    }

    public int getRowDistance(Square location) {
        //this method calculates the Row distance between 2 square objects.
        return this.row - location.row;  //this line calculates the row distance between the : current Square (which we use the key word this to represent it), and another specific location Square.
    }
    public int getColDistance(Square location) { //the method calculates the column distance between 2 square objects .
        return this.col - location.col; //this line calculates the column distance between the : current Square (which we use the key word this to represent it), and another specific location Square.
    }

    public boolean isEmpty() { //To check if a square is empty (does not contain any Piece)
        return piece == null? true : false;
    }
    public boolean isNotEmpty() { //To check if a square is not empty
        return piece != null;
    }

    public Piece getPiece() {  //getting the piece its return type is a Piece
        return piece;
    }
    public void setPiece(Piece piece){ //setting the piece its return type void , maens it doesn't return anything
        this.piece = piece; // returns the piece located on the square!
    }

    public void clear() { //to clear the place of any piece after it moves
        piece = null; //we will make the piece object null means empty ( removing the piece from the square)
        //we can use the key word null to objects to give them a value which is empty
    }

    public boolean isAtSameColumn(Square targetLocation) { //This method returns a boolean value, which check if this square and the target location are in the same column
        return col == targetLocation.getCol() ? true : false;
    }
    public boolean isAtSameRow(Square targetLocation) { //This method returns a boolean value, which check if this square and the target location are in the same row
        return this.row == targetLocation.row;

    }

    public boolean isNeighborColumn(Square targetLocation) { //This method returns a boolean value, checks if this square and the target location are neighbor in terms of columns
        return col ==targetLocation.getCol() || col+1 == targetLocation.getCol() || col-1 ==targetLocation.getCol()? true : false;  //we are making if else statements to check this statement
        //if the current column is equal to the column of target location,  or check the next column (column +1) is equal to the column of target location or the (column -1) going back , is equal to the target location
        //if one of those cases is true it will stop checking the others. it will return true, and the oppiste is false!
    }
    public boolean isAtLastRow(int color){
        //we can use this method in :
        //1- to turn the pawn into Queen .

        if(this.piece != null){ //if the piece is not empty
            //checking if the piece on the square is at the last row for the given colur
            if (color == ChessBoard.WHITE && this.piece.location.row == 7){ //check White if It's at the last row
                return true;
            }
            if (color == ChessBoard.BLACK && this.piece.location.row == 0){ //check Black if It's at the last row
                return true;
            }
        }
        return false; //other than that return false
    }

    public boolean isDiagonal(Square targetLocation) { //to check Diagonal ( if this square and the target location are on a diagonal line)
        return (Math.abs(this.row - targetLocation.row ) == Math.abs(this.col - targetLocation.col) );

    }



    public int getRow() { //getter to get the private row instance
        return row;
    }

    public int getCol() { //getter to get the private column instance
        return col;
    }

    public void putNewQueen(int color) { //when a pawn reaches the last row it will turn into a Queen
        //in fact it can be turned into any piece you lost (Queen , Bishop , Knight).
        this.piece = new Queen(color, this); //make this piece a new Queen piece on the square for the given color whether it was black of white!
    }
    public int getDifRowCol(Square location) { //this method calculates the difference between the row and column of a current square(this) and another square (used in Queen class)
        int difRowCol = location.getRow() - row;
        return difRowCol;
    }
    @Override // the string method returns a string representation of the square if It's either empty or the piece's string representation
    public String toString(){
        return this.piece == null ? " ": this.piece.toString();
    }

    }
