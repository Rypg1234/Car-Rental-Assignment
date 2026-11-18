public class TransactionNode {
    private Transaction transaction;
    private TransactionNode next;

    public TransactionNode(Transaction transaction, TransactionNode next){
        this.transaction = transaction;
        this.next = next;
    }

    public Transaction getTransaction(){
        return transaction;
    }

    public TransactionNode getNext(){
        return next;
    }

    public void setNext(TransactionNode next){
        this.next = next;
    }
}
