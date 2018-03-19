package eecs1022.lab5;

/**
 * Created by user on 3/18/18.
 */
public class Bank
{
    private Client[] clients;
    private int noc;
    private final static int MAX_CLIENT = 10;

    public Bank() {
        clients = new Client[MAX_CLIENT];
    }

    public Client getClient(String name) {
        for (int i = 0; i < noc; i++) {
            if (clients[i].getName().equals(name))
                return clients[i];
        }
        return null;
    }

    public void addClient(Client c) {
        if (noc >= MAX_CLIENT) throw new Error("Maximun Number of Clients Reached");
        if (getClient(c.getName()) != null) throw new Error(String.format("Client %s already exists",
                c.getName()));
        if (c.getAmount() <= 0) throw new Error("Non-Positive Initial Balance");

        clients[noc] = c;
        noc++;
    }

    public boolean doAction(String act, String from, String to, float amount) {
        Client t = getClient(to);
        Client f = getClient(from);
        switch (act) {
            case "deposit":
                if (t == null) throw new Error(String.format("Error: To-Client %s does not exist.", to));
                t.deposit(amount);
                return true;
            case "withdraw":
                if (f == null) throw new Error(String.format("Error: From-Client %s does not exist.", from));
                return f.withdarw(amount);
            case "transfer":
                if (f == null) throw new Error(String.format("Error: From-Client %s does not exist.", from));
                if (t == null) throw new Error(String.format("Error: To-Client %s does not exist.", to));
                return transfer(f, t, amount);
        }
        return false;
    }

//    public void deposit(Client to, float amount) {
//        to.deposit(amount);
//    }
//
//    public boolean withdraw(Client from, float amount) {
//        return from.withdarw(amount);
//    }

    public boolean transfer(Client from, Client to, float amount) {
        //if (from == null) return false; // ERROR
        //if (to == null) return false;
        if (amount <= 0) throw new Error("Non-Positive Amount"); // TODO: ERROR
        if (from.getAmount() < amount) throw new Error("Amount too large to transfer.");

        if (from.withdarw(amount, String.format("Transfer to %s", to.getName()))) {
            to.deposit(amount, String.format("Transfer from %s", from.getName()));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noc; i++) {
            sb.append(clients[i] + "\n");
        }
        return sb.toString();
    }
}
