package battleship;

public class Cruiser extends Ship{
    static final String ShipType = "cruiser";
    public Cruiser(){
        super(3);//3格巡洋舰
    }
    @Override
    public String getShipType() {
        return Cruiser.ShipType;
    }
}
