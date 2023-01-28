package battleship;

import java.util.Random;

public class Ocean {
    private Ship[][] ships = new Ship[10][10];// 海域
    private int shotsFired;// 开火次数
    private int hitCount;// 命中次数
    private int shipsSunk;// 沉没船只数
    private int whichShip = 0;
    private Random RandomNumber = new Random();

    public Ocean() {
        for (int a = 0; a < 10; a += 1) {
            for (int b = 0; b < 10; b += 1) {
                ships[a][b] = new EmptySea();
            }
        }
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }

    void placeAllShipsRandomly() {
        // 将船随机放置于海洋，先放大船，再放小船
        // 无限循环，不断将row，column和Horizontal值重设为随机数
        // 每循环一次调用一次this.okToPlaceShipAt()方法，
        // 若返回true退出循环，false则重新随机复赋值循环上述过程
        this.whichShip += 1;
        int BowRow, BowColumn;
        Boolean Horizontal;
        Ship newShip;
        if (this.whichShip < 2) {
            // 放战列舰
            newShip = new Battleship();
        } else if (this.whichShip < 4) {
            // 放巡洋舰
            newShip = new Cruiser();
        } else if (this.whichShip < 7) {
            // 放驱逐舰
            newShip = new Destroyer();
        } else if (this.whichShip < 11) {
            // 放潜艇
            newShip = new Submarine();
        } else
            return;

        do {
            BowColumn = RandomNumber.nextInt(0, 10);
            BowRow = RandomNumber.nextInt(0, 10);
            Horizontal = RandomNumber.nextBoolean();
        } while (!newShip.okToPlaceShipAt(BowRow, BowColumn, Horizontal, this));
        newShip.setBowRow(BowRow);
        newShip.setBowColumn(BowColumn);
        newShip.setHorizontal(Horizontal);
        // 根据Ship.length和Ship.isHorizontal属性放置船只
        newShip.placeShipAt(BowRow, BowColumn, Horizontal, this);
        if (this.whichShip < 11)
            this.placeAllShipsRandomly();
    }

    boolean isOccupied(int row, int column) {
        // 若该位置有一艘船，则返回true，否则返回false
        if (this.ships[row][column].getShipType() == "Empty") {
            return false;
        }
        return true;
    }

    boolean shootAt(int row, int column) {
        // 若给定位置有一艘船且未沉没，返回true，否则false，方法会更新已开炮的次数和命中次数。
        this.shotsFired += 1;
        if (this.ships[row][column].shootAt(row, column)) {
            this.hitCount += 1;
            if (this.ships[row][column].isSunk()) {
                this.shipsSunk += 1;
            }
            return true;
        }
        return false;
    }

    int getShotsFired() {
        // 返回开火次数
        return shotsFired;
    }

    int getHitCount() {
        // 返回命中次数
        return hitCount;
    }

    int getShipsSunk() {
        // 返回沉船数
        return shipsSunk;
    }

    boolean isGameOver() {
        // 若所有船只沉没则返回true，否则返回false
        if (getShipsSunk() == 10)
            return true;
        return false;
    }

    Ship[][] getShipArray() {
        // 返回10x10的Ship数组。
        return this.ships;
    }

    void print() {
        // 左边缘为行号，上边缘为列号，数字从0-9。左上方区域为(0,0)。'x'已开火并击中，'-'已开火未击中,'s'沉船,'.'未开火位置
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                if (x == 0) {
                    System.out.print("  0 1 2 3 4 5 6 7 8 9");
                    break;
                }
                if (y == 0) {
                    System.out.print((x - 1) + " ");
                    y += 1;
                }
                if (this.ships[x - 1][y - 1].getShipType() != "Empty") {
                    if (this.ships[x - 1][y - 1].isHorizontal()) {
                        // 船水平放置
                        if (this.ships[x - 1][y - 1].getHit()[this.ships[x - 1][y - 1].getBowColumn() - y + 1]) {
                            System.out.print(this.ships[x - 1][y - 1].toString() + " ");
                        } else {
                            System.out.print(". ");
                        }
                    } else {
                        // 船垂直放置
                        if (this.ships[x - 1][y - 1].getHit()[this.ships[x - 1][y - 1].getBowRow() - x + 1]) {
                            System.out.print(this.ships[x - 1][y - 1].toString() + " ");
                        } else {
                            System.out.print(". ");
                        }
                    }
                } else {
                    System.out.print(this.ships[x - 1][y - 1].toString() + " ");
                }
            }
            System.out.println();
        }
    }
}
