package planes;

/**
 * Created by zehra on 2/21/21.
 */
public class ClassRows {
    int firstRow;
    int lastRow;
    char leftSeat;
    char rightSeat;

    public ClassRows(int firstRow, int lastRow, char leftSeat, char rightSeat) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.leftSeat = leftSeat;
        this.rightSeat = rightSeat;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public char getRightSeat() {
        return rightSeat;
    }

    public char getLeftSeat() {
        return leftSeat;
    }

    public int getLastRow() {
        return lastRow;
    }
}
