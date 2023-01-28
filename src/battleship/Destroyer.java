package battleship;

public class Destroyer extends Ship{
    static final String ShipType = "destroyer";
    public Destroyer(){
        super(2);//两格驱逐舰
    }
    @Override
    public String getShipType() {
        return Destroyer.ShipType;
    }
}
