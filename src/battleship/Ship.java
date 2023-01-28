package battleship;

public abstract class Ship {
    private int bowRow; // 船头所在行号
    private int bowColumn; // 船头所在列号
    private int length; // 船的长度
    private boolean horizontal; // true为水平放置，false为垂直放置
    private boolean[] hit; // 一组布尔值，表示该部分是否被击中。true为击中，false为未击中部分

    public Ship(int length) {
        this.length = length;
        hit = new boolean[length];
        // 设定船的长度属性，并根据长度初始化命中数组
    }

    public int getLength() {
        return length;
    }

    public int getBowRow() {
        return bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public boolean[] getHit() {
        return hit;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setBowRow(int row) {
        this.bowRow = row;
    }

    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public abstract String getShipType();

    boolean okToPlaceShipAt(int row, int column, boolean Horizontal, Ocean ocean) {
        if (Horizontal) {
            // 水平放置
            for (int a = column; a > column - this.length; a -= 1) {
                // 看能否写入表中
                if (a < 0 || a > 9 || ocean.isOccupied(row, a)) {
                    return false;
                }
            }
            for (int nRow = row + 1; nRow > row - 2; nRow -= 1) {
                for (int ncolumn = column + 1; ncolumn > column - this.length - 1; ncolumn -= 1) {
                    if (!(nRow == row && ncolumn > column - this.length && ncolumn < column + 1)) {
                        if (nRow > -1 && nRow < 10 && ncolumn > -1 && ncolumn < 10)
                            if (ocean.isOccupied(nRow, ncolumn))
                                return false;
                    }
                }
            }
        } else {
            // 垂直放置
            for (int a = row; a > row - this.length; a -= 1) {
                // 看能否写入表中
                if (a < 0 || a > 9 || ocean.isOccupied(a, column))
                    return false;
            }
            for (int ncolumn = column + 1; ncolumn > column - 2; ncolumn -= 1) {
                for (int nRow = row + 1; nRow > row - this.length - 1; nRow -= 1) {
                    if (!(ncolumn == column && nRow > row - this.length && nRow < row + 1)) {
                        if (nRow > -1 && nRow < 10 && ncolumn > -1 && ncolumn < 10)
                            if (ocean.isOccupied(nRow, ncolumn))
                                return false;
                    }
                }
            }
        }
        return true;
    }

    void placeShipAt(int row, int column, boolean Horizontal, Ocean ocean) {
        if (this.isHorizontal()) {
            for (int a = column; a > column - this.getLength(); a -= 1) {
                ocean.getShipArray()[row][a] = this;
            }
        } else {
            for (int a = row; a > row - this.getLength(); a -= 1) {
                ocean.getShipArray()[a][column] = this;
            }
        }
    }

    boolean shootAt(int row, int column) {
        if (!this.isSunk()) {
            if (this.horizontal) {
                this.hit[this.bowColumn - column] = true;
                return true;
            } else {
                this.hit[this.bowRow - row] = true;
                return true;
            }
        }
        return false;
    }

    boolean isSunk() {
        for (int a = 0; a < this.length; a += 1) {
            if (!hit[a])
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.isSunk())
            return "s";
        return "x";
    }
}
