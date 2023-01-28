package battleship;

public class Submarine extends Ship{
    static final String ShipType = "submarine";
    public Submarine(){
        super(1);//1格潜艇
    }
    @Override
    public String getShipType() {
        return Submarine.ShipType;
    }
}
