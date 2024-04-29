import java.util.ArrayList;
import java.util.Hashtable;

public class ChessBoard {
    //a chess board is composed of 64 squares !
    // a 2D array list of squares
    public static final int WHITE =0 ;
    public static final int BLACK = 1;
    public Square[][] Board2D = new Square[8][8]; // a 2D array list
    public boolean isWhite;
    public Hashtable<Character , Integer> abcHashtable = new Hashtable<Character , Integer>();
    private int NumOfWhites = 16;
    private int NumOfBlacks = 16;


    public ChessBoard() {
        this.isWhite = true;
        for (int row = 0 ; row <8 ;row++) {
            for (int col =0 ; col <8 ; col++) {
                Board2D[row] [col] = new Square(row , col , this);
            }
        }
        initialize();
    }
    private void createHashtable() {
        abcHashtable.put('a',0);
        abcHashtable.put('b',1);
        abcHashtable.put('c',2);
        abcHashtable.put('d',3);
        abcHashtable.put('e',4);
        abcHashtable.put('f',5);
        abcHashtable.put('g',6);
        abcHashtable.put('h',7);
    }
    public void initialize() {
        for (int col = 0; col < 8; col++) {
            new Pawn(WHITE, Board2D[6][col]);
            new Pawn(BLACK, Board2D[1][col]);
        }
        for (int col = 0; col < 8; col += 7) {
            new Rook(WHITE, Board2D[7][col]);
            new Rook(BLACK, Board2D[0][col]);
        }
        new Queen(WHITE, Board2D[7][3]);
        new King(WHITE, Board2D[7][4]);
        new Queen(BLACK, Board2D[0][3]);
        new King(BLACK, Board2D[0][4]);
        for (int col = 2; col < 7; col += 3) {
            new Bishop(WHITE, Board2D[7][col]);
        }
        for (int col = 2; col < 7; col += 3) {
            new Bishop(BLACK, Board2D[0][col]);
        }
        for (int col = 1; col < 7; col += 5) {
            new Knight(WHITE, Board2D[7][col]);
        }
        for (int col = 1; col < 7; col += 5) {
            new Knight(BLACK, Board2D[0][col]);
        }
        createHashtable();
    }



    public boolean isGameEnded() {
        return (NumOfWhites == 0 || NumOfBlacks ==0);
    }

    public boolean isWhitePlaying() {
        return isWhite;
    }

    public Piece getPieceAt(String from) {
        int [] coorDinates = getCoordinates(from);
        return Board2D[coorDinates[0]][coorDinates[1]].getPiece();
    }
    public int [] getCoordinates(String from) {
        from = from.toLowerCase();
        char colchar = from.charAt(0);
        char rowChar =from.charAt(1);

        int row = 8 - Integer.parseInt(String.valueOf(rowChar));
        int col = abcHashtable.get(colchar);
        return new int []{row , col};
    }


    public Square getSquareAt(String to) {
        int [] Coord = getCoordinates(to);
        return Board2D[Coord[0]] [Coord[1]];

    }

    public Square[] getSquaresBetween(Square location, Square targetLocation){
        Square [] btw;

        if(location.isDiagonal(targetLocation) || location.isAtSameColumn(targetLocation) || location.isAtSameRow(targetLocation)){

            int [] coordLoc = {location.getRow(),location.getCol()};
            int [] coordTar = {targetLocation.getRow(), targetLocation.getCol()};
            int x1 = coordLoc[0];
            int y1 = coordLoc[1];
            int x2 = coordTar[0];
            int y2 = coordTar[1];

            btw = new Square[Math.max(Math.abs(x1-x2),Math.abs(y1-y2))];
            Square [] squaresReversed = new Square[btw.length];

            if(location.isAtSameRow(targetLocation)){
                if(y2>y1){
                    btw = new Square[y2-y1];
                    int index = 0;
                    for(int i=y1+1; i<=y2; i++){
                        btw[index]=Board2D[x1][i];
                        index++;
                    }
                }else {
                    btw = new Square[y1-y2];
                    int index = 0;
                    for(int i=y1-1; i>=y2; i--){
                        btw[index]=Board2D[x1][i];
                        index++;
                    }
                }
            }
            else if (location.isAtSameColumn(targetLocation)){
                if(x2>x1){
                    btw = new Square[x2-x1];
                    int index = 0;
                    for(int i = x1+1; i<=x2;i++){
                        btw[index]=Board2D[i][y1];
                        index++;
                    }
                }else {
                    btw = new Square[x1-x2];
                    int index = 0;
                    for(int i=x1-1; i>=x2; i--){
                        btw[index]=Board2D[i][y1];
                        index++;
                    }
                }
            }
            else {
                if (x1>x2){
                    btw = new Square[x1-x2];
                    if (y2>y1){
                        x1 = x1-1;
                        y1 = y1+1;
                        for (int i=0; i<btw.length; i++){
                            btw[i] = Board2D[x1][y1];
                            x1--;
                            y1++;
                        }
                    }else {
                        x1 = x1-1;
                        y1 = y1-1;
                        for(int i = 0; i<btw.length;i++){
                            btw[i] = Board2D[x1][y1];
                            x1--;
                            y1--;
                        }
                    }
                }else {
                    btw = new Square[x2-x1];
                    if (y1 > y2) {
                        x1=x1+1;
                        y1=y1-1;
                        for(int i = 0; i<btw.length ; i++){
                            btw[i] = Board2D[x1][y1];
                            x1++;
                            y1--;
                        }
                    }else {
                        x1=x1+1;
                        y1=y1+1;
                        for(int i = 0; i<btw.length; i++){
                            btw[i] = Board2D[x1][y1];
                            x1++;
                            y1++;
                        }
                    }
                }
            }

            squaresReversed = new Square[btw.length];
            int j = btw.length;
            for(int t = 0; t<btw.length; t++){
                squaresReversed[j-1] = btw[t];
                j = j -1;
            }
            return squaresReversed;
        }
        return new Square[0];
    }

    private boolean isValidCoordinates(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /*
    In this implementation, the method first checks if the given row and column
     coordinates are within the valid range of the Board2D array.
     If the coordinates are valid, it retrieves the corresponding Square
      object from the array using Board2D[row][col] and returns it.
     */
    public Square getSquare(int row , int col) {
        if(isValidCoordinates(row , col)) {
            return Board2D [row][col];
        }else{
            return null;

        }
    }
    public Square[] getSquaresBetweenForKnight(Square location,  Square targetLocation){
        Square [] squares = new Square[2];

        int [] coordLoc = {location.getRow(),location.getCol()};
        int [] coordTar = {targetLocation.getRow(), targetLocation.getCol()};
        int locRow = coordLoc[0];
        int locCol = coordLoc[1];
        int tarRow = coordTar[0];
        int tarCol = coordTar[1];

        squares[1] = targetLocation;

        if (tarCol<locCol){
            if (tarRow<locRow)
                squares[0] = Board2D[tarRow][tarCol+1];
            if (tarRow>locRow)
                squares[0] = Board2D[tarRow-1][tarCol];
        }
        else {
            if(tarRow<locRow)
                squares[0] = Board2D[tarRow][tarCol-1];
            if(tarRow>locRow)
                squares[0] = Board2D[tarRow-1][tarCol];
        }
        return squares;
    }

    public void nextPlayer() {
        isWhite = !isWhite;
    }
     //Printing the board
     @Override
     public String toString(){

         String brdStr = "";
         brdStr += "    A   B   C   D   E   F   G   H  \n";
         brdStr += "  ---------------------------------\n";
         for (int row = 0; row < 8; ++row){
             brdStr += 8-row + " ";
             for (int col = 0; col < 8; ++col) {
                 brdStr += "|";
                 brdStr += " " + Board2D[row][col] + " ";
                 if (col == 7)
                     brdStr += "| ";
             }
             brdStr += 8 - row + " \n";
             brdStr += "  ---------------------------------\n";
         }
         brdStr += "    A   B   C   D   E   F   G   H  \n";

         return brdStr;
     }



}
