public class SUV extends Vehicle{

    public SUV(String description, int mpg, String vin){
        super(description,mpg,vin,1);
    }

    public String toString(){
        return this.getDescription() + " (SUV) MPG: " + this.getMpg() + " VIN: " + this.getVIN();
    }
}
