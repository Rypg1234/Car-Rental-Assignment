import java.util.*;

public class EmployeeInterface implements UserInterface {
	
	// No constructor needed, calls static methods of the SystemInterface.
	// Method start begins a command loop that repeatedly: (a) displays a menu of options, 
      // (b) gets the selected option from the user, and (c) executes the corresponding command.

	private boolean quit = false;
 	public void start(Scanner input) {
		int selection;
		// command loop
		input.nextLine();
		while(!quit) {
			displayMenu();
			selection = getSelection(input);
			execute(selection, input);
		}
    }
	
     // ------- private methods

      private void execute(int selection, Scanner input){

        int veh_type, num_day_used, num_miles_driven;
		String vin, creditcard_num, name;  
 		String[] display_lines = {""};
		RentalDetails rental_details;  
		ReservationDetails reserv_details;
		boolean insurance;

		switch(selection) {

		// display rental rates
			case 1: veh_type = getVehicleType(input);
					switch(veh_type) {
				    	case 1: display_lines = SystemInterface.getCarRates(); break;
				    	case 2: display_lines = SystemInterface.getSUVRates(); break;
			      	    case 3: display_lines = SystemInterface.getMinivanRates(); break;
					}
					displayResults(display_lines);
					break;

		// display available vehicles
			case 2:	veh_type = getVehicleType(input);
				switch(veh_type) {
					    case 1: display_lines = SystemInterface.getAvailCars(); break;
					    case 2: display_lines = SystemInterface.getAvailSUVs(); break;
			      	    case 3: display_lines = SystemInterface.getAvailMinivans(); break;
					}
					displayResults(display_lines);
					break;

		// display estimated rental cost
			case 3:	rental_details = getRentalDetails(input);
					display_lines = SystemInterface.calcRentalCost(rental_details);
					displayResults(display_lines);
					break;
		 		
		// make a reservation
			case 4:	reserv_details = getReservationDetails(input);
					display_lines = SystemInterface.makeReservation(reserv_details);
					displayResults(display_lines);
					break;
		
		// cancel a reservation
			case 5:	vin = getVIN(input);
					display_lines = SystemInterface.cancelReservation(vin);
					displayResults(display_lines);
					break;

		// process returned vehicle
			case 6:	creditcard_num = getCreditCardNum(input);
					vin = getVIN(input);
					num_day_used = getDays(input);
					num_miles_driven = getMiles(input);
					insurance = wasInsured(input);
					name = getName(input);
					display_lines = SystemInterface.processReturnedVehicle(vin,creditcard_num,num_miles_driven,num_day_used,insurance);
					SystemInterface.addTransaction(creditcard_num, vin, name, num_day_used, num_miles_driven, insurance);
					displayResults(display_lines);
					break;

			// quit program
			case 7: quit = true;
		}
	}

	// displays the user options
	private void displayMenu() {
		System.out.println("1. Show Rental Rates \n2. Show Available Vehicles \n3. Display Estimated Rental Cost \n4. Make a Reservation \n5. Cancel a Reservation \n6. Process a Returned Vehicle \n7. Quit");
	}

	
	// prompts user for selection from menu (continues to prompt if selection < 1 or selection > 8)
	private int getSelection(Scanner input) {
		int selection = 0;
		while(selection < 1 || selection > 7){
			System.out.println("Please make a selection 1 - 7.");
			selection = input.nextInt();
			input.nextLine();
		}
		return selection;
	}


	// prompts user to enter VIN for a given vehicle (does not do any error checking on the input)
	private String getVIN(Scanner input) {
		System.out.println("Please enter VIN.");
		return input.nextLine();
	}

	private int getDays(Scanner input){
		System.out.println("How many days was the vehicle rented?");
		return input.nextInt();
	}

	private int getMiles(Scanner input){
		System.out.println("How many miles was the vehicle driven?");
		return input.nextInt();
	}

	private boolean wasInsured(Scanner input){
		System.out.println("Did you get the car insured? Y/N");
		String insured = input.next();
		if(insured.equals("Y")){
			return true;
		} else {
			return false;
		}
	}

	private String getName(Scanner input){
		System.out.println("What is the customers name?");
		return input.nextLine();
	}


	//prompts user to enter credit card number and will return that value as a string
	private String getCreditCardNum(Scanner input){
		System.out.println("Please enter Credit Card number.");
		return input.nextLine();
	}

	
	// prompts user to enter 1, 2, or 3, and returns (continues to prompt user if invalid input given)
	private int getVehicleType(Scanner input) {
		int type = 0;
		while(type < 1 || type > 3){
			System.out.println("Enter Vehicle Type, 1 for car, 2 for SUV, and 3 for Minivan");
			type = input.nextInt(); input.nextLine();
		}
		return type;
	}


	// prompts user to enter required information for an estimated rental cost (vehicle type, estimated  
 	// number of miles expected to be driven, expected rental period and optional insuranc, returning the
 	// result packaged as a RentalDetails object (to pass in method calls to the SystemInterface)
	private RentalDetails getRentalDetails(Scanner input) {
		System.out.println("Please enter the vehicle type, rental time unit (d for day, w for week, m for month), the length of rental, the number of miles expected, and a T or F for insurance.");
		String vehicle_type = input.nextLine();
		String time_unit = input.nextLine();
		int time_period = input.nextInt(); input.nextLine();
		int num_miles = input.nextInt(); input.nextLine();
		String insuranceSelection = input.next(); input.nextLine();
		boolean insurance;
		if(insuranceSelection.equals("T")){
			insurance = true;
		} else {
			insurance = false;
		}
		RentalDetails rent = new RentalDetails(vehicle_type, new TimePeriod(time_unit, time_period), num_miles, insurance);
		return rent;
	}
	

	// prompts user to enter required information for making a reservation (VIN of vehicle to reserve, 
 	// credit card num, rental period, and optional insurance), returning the result packaged as a 
 	// ReservationDetails object (to pass in method calls to the SystemInterface)
	private ReservationDetails getReservationDetails(Scanner input) {

		System.out.println("Please enter the vehicle vin, your name, credit card number, rental time unit (d for day, w for week, m for month), the length of rental, and a T or F for insurance.");
		String VIN = input.nextLine();
		String name = input.nextLine();
		String card_num = input.nextLine();
		String time_unit = input.next(); input.nextLine();
		int time_period = input.nextInt(); input.nextLine();
		String insuranceSelection = input.next(); input.nextLine();
		boolean insurance;
		if(insuranceSelection.equals("T")){
			insurance = true;
		} else {
			insurance = false;
		}
		ReservationDetails reserve = new ReservationDetails(name, card_num, new TimePeriod(time_unit, time_period), insurance, VIN);
		return reserve;
	}
	

	// displays the array of strings passed, one string per screen line
	private void displayResults(String[] lines) {
		for(int i = 0; i < lines.length; i++){
			if(!lines[i].isEmpty()){
				System.out.println(lines[i]);
			}
		}
	}


}