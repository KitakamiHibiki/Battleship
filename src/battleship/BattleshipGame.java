package battleship;

import java.util.Scanner;

public class BattleshipGame {
    public static void main(String[] args) {
        Ocean ocean = new Ocean();
        Scanner getnumber = new Scanner(System.in);
        ocean.placeAllShipsRandomly();
        while (!ocean.isGameOver()) {
            try {
                ocean.print();
                System.out.print("Enter row,column: ");
                int row, column;
                String str = getnumber.nextLine();
                row = Integer.parseInt(str.split(",")[0]);
                column = Integer.parseInt(str.split(",")[1]);
                ocean.shootAt(row, column);
                System.out.println("HitCount: " + ocean.getHitCount());
                System.out.println("ShipsSunk: " + ocean.getShipsSunk());
                System.out.println("ShotsFired: " + ocean.getShotsFired());
            } catch (Exception ex) {
            }
        }
        ocean.print();
        getnumber.close();
        System.out.println("HitCount: " + ocean.getHitCount());
        System.out.println("ShipsSunk: " + ocean.getShipsSunk());
        System.out.println("ShotsFired: " + ocean.getShotsFired());
    }
}
