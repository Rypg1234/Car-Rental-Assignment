public class Car extends Vehicle {

    public Car(String description, int mpg, String vin){
        super(description,mpg,vin,0);
    }

    public String toString(){
        return this.getDescription() + " (Car) MPG: " + this.getMpg() + " VIN: " + this.getVIN();
    }
}
