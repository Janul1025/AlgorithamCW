public class QItem {
    private int rowNumber;            // row number of the node
    private int columnNumber;         // column number of the node
    private QItem previous;    // node object to store its parent node
    private String move;

    public QItem(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public QItem getPrevious() {
        return previous;
    }

    public void setPrevious(QItem previous) {
        this.previous = previous;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
