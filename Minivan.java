public class Minivan extends Vehicle{

    public Minivan(String description, int mpg, String vin){
        super(description,mpg,vin,2);
    }

    public String toString(){
        return this.getDescription() + " (SUV) MPG: " + this.getMpg() + " VIN: " + this.getVIN();
    }
}
