package eecs1022.lab5;

/**
 * Created by user on 3/18/18.
 */
public class Transaction
{  public enum Action {
    Deposit,
    Withdraw
}

    private Action action;
    private float amount;
    private String comment;



    public Transaction(Action action, float amount) {
        this.action = action;
        this.amount = amount;
        this.comment = null;
    }

    public Transaction(Action action, float amount, String comment) {
        this.action = action;
        this.amount = amount;
        this.comment = comment;
    }

    public float getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return comment == null ? String.format("Transaction %s: $%.2f", action, amount) :
                String.format("Transaction %s: $%.2f - %s", action, amount, comment);
    }
}
