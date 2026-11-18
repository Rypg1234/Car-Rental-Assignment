public class CurrentRates {
    private VehicleRates[] rates = new VehicleRates[3];

    public CurrentRates(VehicleRates Cars, VehicleRates SUV, VehicleRates Minivan){
        rates[0] = Cars;
        rates[1] = SUV;
        rates[2] = Minivan;
    }

    public VehicleRates getCarRates(){
        return rates[0];
    }

    public VehicleRates getSUVRates(){
        return rates[1];
    }

    public VehicleRates getMinivanRates(){
        return rates[2];
    }

    public void setCarRates(VehicleRates r){
        rates[0] = r;
    }

    public void setSUVRates(VehicleRates r){
        rates[1] = r;
    }

    public void setMinivanRates(VehicleRates r){
        rates[2] = r;
    }

    public double calcEstimatedCost(RentalDetails rent){
        double cost = 0;
        String RentalUnit = rent.getTimePeriod().getUnit();
        int RentalLength = rent.getTimePeriod().getQuantity();
        int vehicleType;
        String vehicleCategory = rent.getVehicleType();
        boolean insurance = rent.isInsured();

        if(vehicleCategory.equals("Car")){
            vehicleType = 0;
        } else if(vehicleCategory.equals("SUV")){
            vehicleType = 1;
        } else {
            vehicleType = 2;
        }

        if(RentalUnit == "d"){
            cost = RentalLength * rates[vehicleType].getDailyRate();
            if(insurance){
                cost = cost + (rates[vehicleType].getDailyInsurance() * RentalLength);
            }
        } else if(RentalUnit == "w"){
            cost = RentalLength * rates[vehicleType].getWeeklyRate();
            if(insurance){
                cost = cost + (rates[vehicleType].getDailyInsurance() * RentalLength * 7);
            }
        } else {
            cost = RentalLength * rates[vehicleType].getMonthlyRate();
            if(insurance){
                cost = cost + (rates[vehicleType].getDailyInsurance() * RentalLength * 31);
            }
        }
        cost = cost + (rent.getMilesDriven() * rates[vehicleType].getMileageCharge());
        return cost;
    }

    public double calcActualCost(VehicleRates rates, int num_days_used, int NumMilesDriven, boolean insurance){
        double cost = 0;
        if(insurance){
            cost = cost + rates.getDailyInsurance() * num_days_used;
        }
        if(num_days_used >= 31){
            while(num_days_used >= 31){
                num_days_used = num_days_used - 31;
                cost = cost + rates.getMonthlyRate();
            }
        }
        if(num_days_used >=7){
            while(num_days_used >= 7){
                num_days_used = num_days_used - 7;
                cost = cost + rates.getWeeklyRate();
            }
        }
        if(num_days_used >= 1){
            while(num_days_used >= 1){
                num_days_used = num_days_used - 1;
                cost = cost + rates.getDailyRate();
            }
        }
        cost = cost + rates.getMileageCharge() * NumMilesDriven;
        return cost;
    }
}
