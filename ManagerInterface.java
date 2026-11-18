import java.util.*;

public class ManagerInterface implements UserInterface{

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

	private void execute(int selection, Scanner input){

		String[] display_lines = {""};
		VehicleRates r;
		int veh_type;

		switch(selection){
		//Display rates
			case 1: 
				display_lines = SystemInterface.getCarRates();
				displayResults(display_lines);
				display_lines = SystemInterface.getSUVRates();
				displayResults(display_lines);
				display_lines = SystemInterface.getMinivanRates();
				displayResults(display_lines);
			break;

		//Update rates
			case 2:
				veh_type = getVehicleType(input);
				switch(veh_type){
					case 1:
						r = getRates(input);
						display_lines = SystemInterface.updateCarRates(r);
					break;
					case 2:
						r = getRates(input);
						display_lines = SystemInterface.updateSUVRates(r);
					break;
					case 3:
						r = getRates(input);
						display_lines = SystemInterface.updateMinivanRates(r);
					break;
				}
				displayResults(display_lines);
			break;
		//Display all vehicles
			case 3:
				display_lines = SystemInterface.getAllVehicles();
				displayResults(display_lines);
			break;
		//Display all reservations
			case 4:
				display_lines = SystemInterface.getAllReservations();
				displayResults(display_lines);
			break;
		//Display all Transactions
			case 5:
				display_lines = SystemInterface.getAllTransactions();
				displayResults(display_lines);
			break;
		//Quit
			case 6: quit = true;
		}
	}

    private void displayMenu() {
		System.out.println("1. Display All Rates \n2. Update Rates \n3. Display All Vehicles \n4. Display All Reservations \n5. Display All Transactions \n6. Quit");
	}

    private int getSelection(Scanner input) {
		int selection = 0;
		while(selection < 1 || selection > 6){
			System.out.println("Please make a selection 1 - 6.");
			selection = input.nextInt();
			input.nextLine();
		}
		return selection;
	}

	private VehicleRates getRates(Scanner input){
		double daily, weekly, monthly, mile_charge, insurance;
		System.out.println("Enter the daily, weekly, monthly, mile charge, and insurance rate");
		daily = input.nextDouble(); input.nextLine();
		weekly = input.nextDouble(); input.nextLine();
		monthly = input.nextDouble(); input.nextLine();
		mile_charge = input.nextDouble(); input.nextLine();
		insurance = input.nextDouble(); input.nextLine();
		VehicleRates r = new VehicleRates(daily, weekly, monthly, mile_charge, insurance);
		return r;
	}

	private int getVehicleType(Scanner input) {
		int type = 0;
		while(type < 1 || type > 3){
			System.out.println("Enter Vehicle Type, 1 for car, 2 for SUV, and 3 for Minivan");
			type = input.nextInt(); input.nextLine();
		}
		return type;
	}

	private void displayResults(String[] lines) {
		for(int i = 0; i < lines.length; i++){
			if(!lines[i].isEmpty()){
				System.out.println(lines[i]);
			}
		}
	}

}