package eecs1022.lab5;

/**
 * Created by user on 3/18/18.
 */
public class Client
{
    private String name;
    private float amount;
    private Transaction[] history;
    private int noc;
    private final static int MAX_TRANSACTION = 10;

    public Client(String name, float amount) {
        this.name = name;
        this.amount = amount;
        this.history = new Transaction[MAX_TRANSACTION];
    }

    public String getName() {
        return name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public boolean withdarw(float amount) {
        return withdarw(amount, null);
    }

    public boolean withdarw(float amount, String comment) {
        if (amount <= 0) throw new Error("Non-Positive Amount"); // TODO: ERROR
        if (this.amount < amount) throw new Error("Amount too large to withdraw.");

        this.amount -= amount;

        //history
        history[noc] = new Transaction(Transaction.Action.Withdraw, amount, comment);
        noc++;
        return true;
    }

    public void deposit(float amount) {
        deposit(amount,null);
    }

    public void deposit(float amount, String comment) {
        if (amount <= 0) throw new Error("Non-Positive Amount");

        this.amount += amount;
        // history
        history[noc] = new Transaction(Transaction.Action.Deposit, amount, comment);
        noc++;
    }

    public Transaction[] getTransactions() {
        return history;
    }

    public String printHistory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noc; i++) {
            sb.append(history[i]+"\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        //TODO: Parse string
        return String.format("%s: $%.2f", name, amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client)
            return name == ((Client)obj).name;
        return false;
    }
}
