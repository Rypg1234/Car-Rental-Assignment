public class ReservationDetails {
    private String customer_name;
    private String credit_card_num;
    private TimePeriod rental_period;
    private boolean inurance_selected;
    private String VIN;

    public ReservationDetails(String customer_name, String credit_card_num, TimePeriod rental_period, boolean insurance_selected, String VIN){
        this.customer_name = customer_name;
        this.credit_card_num = credit_card_num;
        this.rental_period = rental_period;
        this.inurance_selected = insurance_selected;
        this.VIN = VIN;
    }

    public String getName(){
        return customer_name;
    }

    public String getCreditCardNum(){
        return credit_card_num;
    }

    public TimePeriod getTimePeriod(){
        return rental_period;
    }

    public boolean isInsured(){
        return inurance_selected;
    }

    public String getVIN(){
        return VIN;
    }

    public String toString(){
        return "Name: " + customer_name + " Time: " + rental_period.toString() + " VIN: " + VIN;
    }
}
