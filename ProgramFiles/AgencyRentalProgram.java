import java.util.*;

public class AgencyRentalProgram {

public static void main(String[ ] args) {

	// Provide Hard-coded Current Agency Rates
	CurrentRates agency_rates = new CurrentRates(new VehicleRates(24.95,169.95,514.95,0.16,14.95),  // cars
                                                 new VehicleRates(29.95,189.95,679.95,0.16,14.95),  // SUVs
                                                 new VehicleRates(36.95,224.95,687.95,0.21,19.95)); //minivans

	// Create an Initially Empty Vehicles Collection, and Populate
	Vehicles agency_vehicles = new Vehicles();
	populate(agency_vehicles);    // supporting private static method (to be added)

	// Create Initially Empty Transactions Object
	Transactions transactions = new Transactions();

	// Establish User Interface
	Scanner input = new Scanner(System.in);
	UserInterface ui;
	boolean quit = false;

	// Create Requested UI and Begin Execution 
	while(!quit) {  // (allows switching between Employee and Manager user interfaces while running)

		ui = getUI(input);

		if(ui == null)
			quit = true;
		else {
			// Init System Interface with Agency Data (if not already initialized)
			if(!SystemInterface.initialized())
			      SystemInterface.initSystem(agency_rates, agency_vehicles, transactions);

			// Start User Interface
			ui.start(input);
		}
	}
}

public static UserInterface getUI(Scanner input) {
	while(true) {
		System.out.print("1 – Employee, 2 – Manager, 3 – quit: ");
		int selection = input.nextInt();

		if(selection == 1) {
			return new EmployeeInterface();
		} else if(selection == 2) {
			return new ManagerInterface();
		} else if(selection == 3) {
			return null;
		} else
			System.out.println("Invalid selection – please reenter");
	}
}

public static void populate(Vehicles agency_vehicles){
	agency_vehicles.add(new Car("Toyota Prius", 57, "AED456"));
	agency_vehicles.add(new Car("Honda Insight", 55, "DEF123"));
	agency_vehicles.add(new Car("Hyundai Elantra Hybrid", 53, "JHK857"));
	
	agency_vehicles.add(new SUV("Toyota RAV4 Hybrid", 39, "DPF450"));
	agency_vehicles.add(new SUV("Ford Explorer Hybrid", 31, "WCH302"));
	agency_vehicles.add(new SUV("Honda Pilot Hybrid", 36, "KSB698"));
	agency_vehicles.add(new SUV("Lexus NX 450h+", 37, "GEK334"));

	agency_vehicles.add(new Minivan("Toyota Sienna", 36, "AGH890"));
	agency_vehicles.add(new Minivan("Chrysler Pacifica Hybrid", 82, "BFJ386"));
	agency_vehicles.add(new Minivan("Honda Odyssey", 22, "KCM341"));
	agency_vehicles.add(new Minivan("Kia Carnival", 22, "TSH580"));
}
}
