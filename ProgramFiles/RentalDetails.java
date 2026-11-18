public class RentalDetails {
    private String vehicle_type;
    private TimePeriod rental_period;
    private int num_miles_driven;
    private boolean insurance_selected;

    public RentalDetails(String vehicle_type, TimePeriod rental_period, int num_miles_driven, boolean insurance_selected){
        this.vehicle_type = vehicle_type;
        this.rental_period = rental_period;
        this.num_miles_driven = num_miles_driven;
        this.insurance_selected = insurance_selected;
    }

    public String getVehicleType(){
        return vehicle_type;
    }

    public TimePeriod getTimePeriod(){
        return rental_period;
    }

    public int getMilesDriven(){
        return num_miles_driven;
    }

    public boolean isInsured(){
        return insurance_selected;
    }

    public String toString(){
        return "Vehicle: " + vehicle_type + " Time: " + rental_period + " Miles Driven: " + num_miles_driven + " Insurance: " + insurance_selected;
    }
}
