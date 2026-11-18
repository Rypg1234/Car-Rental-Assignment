public class TimePeriod {
    private String unit;
    private int quantity;

    public TimePeriod(String unit, int quantity){
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getUnit(){
        return unit;
    }

    public int getQuantity(){
        return quantity;
    }

    public String toString(){
        if(unit.equals("d")){
            return quantity + " days";
        } else if(unit.equals("w")){
            return quantity + " weeks";
        } else {
            return quantity + " months";
        }
    }
}
