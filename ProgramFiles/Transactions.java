public class Transactions{
    private TransactionNode head;
    private TransactionNode current;
    private int count = 0;

    public Transactions(){
        head = current = null;
    }

    public Transactions(Transaction[] initial_values){
        for(Transaction trn: initial_values){
            add(trn);
            count++;
        }
    }

    public void add(Transaction tran){
        if(isEmpty()){
            head = new TransactionNode(tran, null);
            count++;
        } else {
            TransactionNode temp = head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new TransactionNode(tran, null));
            count++;
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

    public Transaction next(){
        Transaction next = current.getTransaction();
        current = current.getNext();
        return next;
    }
}