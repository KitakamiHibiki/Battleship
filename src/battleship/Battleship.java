package battleship;

public class Battleship extends Ship {
    static final String ShipType = "battleship";
    public Battleship(){
        super(4);//4格战列舰
    }
    @Override
    public String getShipType() {
        return Battleship.ShipType;
    }
}
