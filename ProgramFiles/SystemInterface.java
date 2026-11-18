public class SystemInterface {

	private static CurrentRates agency_rates;
	private static Vehicles agency_vehicles;
	private static Transactions transactions_history;

	// used to init static variables (in place of a constructor)
	public static void initSystem(CurrentRates r, Vehicles v, Transactions t) {
		agency_rates = r;
		agency_vehicles = v;
		transactions_history = t;
	}
	
	// used to check if SystemInterface initialized
	public static boolean initialized() {
		return agency_rates != null;
	}
	
	// Note that methods updateXXXRates, makeReservation and cancelReservation return an
	// acknowledgement of successful completion of the requested action (e.g. “Vehicle ABC123
	// successfully reserved”). Method processReturnedVehicle returns the final cost for the returned 
	// vehicle (e.g., “Total charge for VIN ABC123 for 3 days, 233 miles @  0.15/mile and daily
	// insurance @ 14.95/day = $xxx.xx.)

	// Current Rates Related Methods
	public static String[ ] getCarRates() {
		String[] message = new String[1];
		message[0] = agency_rates.getCarRates().toString();
		return message;
	}
	public static String[ ] getSUVRates() {
		String[] message = new String[1];
		message[0] = agency_rates.getSUVRates().toString();
		return message;
	}
	public static String[ ] getMinivanRates() {
		String[] message = new String[1];
		message[0] = agency_rates.getMinivanRates().toString();
		return message;
	}

	public static String[ ] updateCarRates(VehicleRates r) {
		String[] message = new String[1];
		agency_rates.setCarRates(r);
		message[0] = "Car rates updated to " + r.toString();
		return message;
	}
	public static String[ ] updateSUVRates(VehicleRates r) {
		String[] message = new String[1];
		agency_rates.setSUVRates(r);
		message[0] = "SUV rates updated to " + r.toString();
		return message;
	}
	public static String[ ] updateMinivanRates(VehicleRates r) {
		String[] message = new String[1];
		agency_rates.setMinivanRates(r);
		message[0] = "Minivan rates updated to " + r.toString();
		return message;
	}

    public static String[ ] calcRentalCost(RentalDetails details) {
		double cost = agency_rates.calcEstimatedCost(details);
		String[] message = new String[1];
		message[0] = "Estimated cost of " + cost;
		return message;
	}

	public static String[ ] processReturnedVehicle(String vin, String card_num, int num_miles, int num_days, boolean insurance) {
		String[] message = new String[1];
		Vehicle vehicle;
		agency_vehicles.reset();
		try{
			vehicle = agency_vehicles.getVehicle(vin);
		} catch (Exception VINNotFoundException){
			message[0] = "VIN Not Found";
			return message;
		}
		if(vehicle.getType() == 0){
			double cost = agency_rates.calcActualCost(agency_rates.getCarRates(), num_days, num_miles, insurance);
			if(insurance){
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getCarRates().getMileageCharge() + "/mile and daily insurance @ " + agency_rates.getCarRates().getDailyInsurance() + "/day = $" + cost; 
			} else {
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getCarRates().getMileageCharge() + "/mile without daily insurance = $" + cost; 
			}
		} else if(vehicle.getType() == 1){
			double cost = agency_rates.calcActualCost(agency_rates.getSUVRates(), num_days, num_miles, insurance);
			if(insurance){
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getSUVRates().getMileageCharge() + "/mile and daily insurance @ " + agency_rates.getSUVRates().getDailyInsurance() + "/day = $" + cost; 
			} else {
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getSUVRates().getMileageCharge() + "/mile without daily insurance = $" + cost; 
			}
		} else {
			double cost = agency_rates.calcActualCost(agency_rates.getMinivanRates(), num_days, num_miles, insurance);
			if(insurance){
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getMinivanRates().getMileageCharge() + "/mile and daily insurance @ " + agency_rates.getMinivanRates().getDailyInsurance() + "/day = $" + cost; 
			} else {
				message[0] = "Total charge for vehicle " + vin + " for " + num_days + " days, " + num_miles + " miles @ " + agency_rates.getMinivanRates().getMileageCharge() + "/mile without daily insurance = $" + cost; 
			}
		}
		return message;
	}

	// Note that the rates to be used are retrieved from the VehicleRates object stored in the specific rented
	// vehicle object, and the daily insurance option is retrieved from the Reservation object of the rented
	// vehicle

	// Vehicle Related Methods
	public static String[ ] getAvailCars() {
		String[] message = new String[agency_vehicles.getLength()];
		int messageLine = 0;
		int carCount = 0;
		int type;
		Vehicle currentVehicle;
		agency_vehicles.reset();
		while(carCount < agency_vehicles.getLength()){
			currentVehicle = agency_vehicles.next();
			type = currentVehicle.getType();
			if(!currentVehicle.isReserved() && type == 0){
				message[messageLine] = currentVehicle.toString();
				messageLine++;
			}
			carCount++;
		}
		return message;
	}
	public static String[ ] getAvailSUVs() {
		String[] message = new String[agency_vehicles.getLength()];
		int messageLine = 0;
		int carCount = 0;
		int type;
		Vehicle currentVehicle;
		agency_vehicles.reset();
		while(carCount < agency_vehicles.getLength()){
			currentVehicle = agency_vehicles.next();
			type = currentVehicle.getType();
			if(!currentVehicle.isReserved() && type == 1){
				message[messageLine] = currentVehicle.toString();
				messageLine++;
			}
			carCount++;
		}
		return message;
	}
	public static String[ ] getAvailMinivans() {
		String[] message = new String[agency_vehicles.getLength()];
		int messageLine = 0;
		int carCount = 0;
		int type;
		Vehicle currentVehicle;
		agency_vehicles.reset();
		while(carCount < agency_vehicles.getLength()){
			currentVehicle = agency_vehicles.next();
			type = currentVehicle.getType();
			if(!currentVehicle.isReserved() && type == 2){
				message[messageLine] = currentVehicle.toString();
				messageLine++;
			}
			carCount++;
		}
		return message;
	}
	public static String[ ] getAllVehicles() {
		String[] message = new String[agency_vehicles.getLength()];
		int messageLine = 0;
		Vehicle currentVehicle;
		agency_vehicles.reset();
		while(messageLine < agency_vehicles.getLength()){
			currentVehicle = agency_vehicles.next();
			message[messageLine] = currentVehicle.toString();
			messageLine++;
		}
		return message;
	}

	public static String[ ] makeReservation(ReservationDetails details) {
		String[] message = new String[1];
		Vehicle vehicle;
		String vin = details.getVIN();
		agency_vehicles.reset();
		try{
			vehicle = agency_vehicles.getVehicle(vin);
		} catch (Exception VINNotFoundException){
			message[0] = "VIN Not Found";
			return message;
		}
		try{
			vehicle.setReservation(details);
		} catch (Exception ReservedVehicleException){
			message[0] = "Vehicle Already Reserved";
			return message;
		}
		message[0] = "Vehicle " + vin + " Has Been Reserved";
		return message;
	}
	public static String[ ] cancelReservation(String vin) {
		String[] message = new String[1];
		Vehicle vehicle;
		try{
			vehicle = agency_vehicles.getVehicle(vin);
		} catch (Exception VINNotFoundException){
			message[0] = "VIN Not Found";
			return message;
		}
		try{
			vehicle.cancelReservation();
		} catch (Exception UnreservedVehicleException){
			message[0] = "Vehicle Not Reserved";
			return message;
		}
		message[0] = "Vehicle Reservation Has Been Canceled";
		return message;
	}
	public static String[ ] getReservation(String vin) {
		String[] message = new String[1];
		Vehicle vehicle;
		ReservationDetails resv;
		agency_vehicles.reset();
		try{
			vehicle = agency_vehicles.getVehicle(vin);
		} catch (Exception VINNotFoundException){
			message[0] = "VIN Not Found";
			return message;
		}
		resv = vehicle.getReservation();
		if(resv == null){
			message[0] = "No Reservation Found For " + vin;
			return message;
		} else {
			message[0] = resv.toString();
			return message;
		}
	}
	public static String[ ] getAllReservations() {
		String[] message = new String[agency_vehicles.getLength()];
		Vehicle currentVehicle;
		ReservationDetails resv;
		String vin;
		agency_vehicles.reset();
		for(int i = 0; i < agency_vehicles.getLength(); i++){
			currentVehicle = agency_vehicles.next();
			resv = currentVehicle.getReservation();
			vin = currentVehicle.getVIN();
			if(resv != null){
				message[i] = resv.toString();
			} else {
				message[i] = "No Reservation For " + vin;
			}
		}
		return message;
	}

	// transactions-related methods
	public static String[ ] addTransaction(String card_num, String vin, String name, int num_day_used, int num_miles, boolean insurance) {
		String[] message = new String[1];
		Vehicle vehicle;
		String vehicleType;
		int type;
		double cost;
		Transaction tran;
		agency_vehicles.reset();
		try{
			vehicle = agency_vehicles.getVehicle(vin);
		} catch (Exception VINNotFoundException){
			message[0] = "VIN Not Found";
			return message;
		}
		type = vehicle.getType();
		if(type == 0){
			vehicleType = "Car";
			cost = agency_rates.calcActualCost(agency_rates.getCarRates(), num_day_used, num_miles, insurance);
		} else if(type == 1){
			vehicleType = "SUV";
			cost = agency_rates.calcActualCost(agency_rates.getSUVRates(), num_day_used, num_miles, insurance);
		} else {
			vehicleType = "Minivan";
			cost = agency_rates.calcActualCost(agency_rates.getMinivanRates(), num_day_used, num_miles, insurance);
		}
		String days = String.valueOf(num_day_used);
		String miles = String.valueOf(num_miles);
		String totcost = String.valueOf(cost);
		tran = new Transaction(card_num, name, vehicleType, days, miles, totcost);
		transactions_history.add(tran);
		message[0] = "Transaction added";
		return message;
	}  
	public static String[ ] getAllTransactions() {
		String[] message = new String[transactions_history.getLength()];
		transactions_history.reset();
		int count = 0;
		Transaction currentTransaction;
		while(transactions_history.hasNext()){
			currentTransaction = transactions_history.next();
			message[count] = currentTransaction.toString();
			count++;
		}
		return message;
	}  

}