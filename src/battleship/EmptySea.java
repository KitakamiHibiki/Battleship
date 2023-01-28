package battleship;

public class EmptySea extends Ship{
    static final String ShipType = "Empty";
    private boolean Sunk = false;
    public EmptySea(){
        super(1);//1格空海域
    }
    @Override
    public String getShipType() {
        return EmptySea.ShipType;
    }
    @Override
    boolean shootAt(int row, int column) {
        this.Sunk = true;
        return false;
    }
    @Override
    boolean isSunk() {
        return this.Sunk;
    }
    @Override
    public String toString() {
        if(this.isSunk()){
           return "-";
        }else{
            return ".";
        }
    }
}
