public class Vehicles {
    private VehicleNode head;
    private VehicleNode current;
    private Exception VINNotFoundException = new Exception();
    private int count = 0;

    public Vehicles(Vehicle[] initial){
        for(Vehicle vehicle: initial){
            add(vehicle);
            count++;
        }
    }

    public Vehicles(){
        head = current = null;
    }

    public void add(Vehicle vehicle){
        if(isEmpty()){
            head = new VehicleNode(vehicle, null);
            count++;
        } else {
            VehicleNode temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new VehicleNode(vehicle, null));
            count++;
        }
    }

    public Vehicle getVehicle(String VIN) throws Exception{
        if(isEmpty()){
            throw VINNotFoundException;
        }
        VehicleNode temp = head;
        while(temp != null && !temp.getVehicle().getVIN().equals(VIN)){
            temp = temp.getNext();
        }
        if(temp.getVehicle().getVIN().equals(VIN)){
            return temp.getVehicle();
        } else {
            throw VINNotFoundException;
        }
    }

    public int getLength(){
        return count;
    }

    public void reset(){
        current = head;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public boolean hasNext(){
        return current != null;
    }

    public Vehicle next(){
        Vehicle next = current.getVehicle();
        current = current.getNext();
        return next;
    }
}
