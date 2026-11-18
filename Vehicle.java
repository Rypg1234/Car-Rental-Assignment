public abstract class Vehicle {
    private String description;
    private int mpg;
    private String vin;
    private int type;
    private ReservationDetails resv;
    private VehicleRates rates;
    private Exception ReservedVehicleException = new Exception();
    private Exception UnreservedVehicleException = new Exception();


    public Vehicle(String description, int mpg, String vin, int type){
        this.description = description;
        this.mpg = mpg;
        this.vin = vin;
        this.type = type;
        resv = null;
        rates = null;
    }

    public String getDescription(){
        return description;
    }

    public int getMpg(){
        return mpg;
    }

    public String getVIN(){
        return vin;
    }

    public ReservationDetails getReservation(){
        return resv;
    }

    public VehicleRates getQuotedRates(){
        return rates;
    }

    public int getType(){
        return type;
    }

    public boolean isReserved(){
        return (resv != null);
    }

    public void setReservation(ReservationDetails resv) throws Exception{
        if(this.resv == null){
            this.resv = resv;
        } else {
            throw ReservedVehicleException;
        }
    }

    public void setQuotedRates(VehicleRates cost){
        rates = cost;
    }

    public void cancelReservation() throws Exception{
        if(this.resv != null){
            this.resv = null;
        } else {
            throw UnreservedVehicleException;
        }
    }

    public abstract String toString();
}
