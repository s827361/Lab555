package eecs1022.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Lab5Activity extends AppCompatActivity
{  Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
    }private void setContentsOfTextView(int id, String newContents) {
    View view = findViewById(id);
    TextView textView = (TextView) view;
    textView.setText(newContents);
}
    private String getItemSelectedById(int ID) {
        Spinner spinner = (Spinner) findViewById(ID);
        return spinner.getSelectedItem().toString();
    }

    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }
    private void setTextViewById(int ID, String text) {
        TextView view = (TextView) findViewById(ID);
        view.setText(text);
    }
    private String getInputById(int ID) {
        EditText view = (EditText) findViewById(ID);
        return view.getText().toString();
    }

    public void onCreateClient(View view) {
        String name = getInputById(R.id.inputNewName);
        float bal = Float.parseFloat(getInputById(R.id.inputNewBalance));
        Client c = new Client(name, bal);
        try {
            bank.addClient(c);
            setTextViewById(R.id.lableResult, "Updated Balances of Clients:\n" + bank.toString());
        }catch (Error e) {
            setTextViewById(R.id.lableResult, "Error:" + e.getMessage());
        }
    }

    public void onAction(View view) {
        String act = getItemSelectedById(R.id.spinnerService);
        String from = getInputById(R.id.inputFrom);
        String to = getInputById(R.id.inputTo);
        float amount = Float.parseFloat(getInputById(R.id.inputAmount));

        try {
            bank.doAction(act, from, to, amount);
            setTextViewById(R.id.lableResult, "Updated Balances of Clients:\n" + bank.toString());
        } catch (Error e) {
            setTextViewById(R.id.lableResult, "Error:" + e.getMessage());
        }
    }

    public void onCheckTransacion(View view) {
        String name = getInputById(R.id.inputName);

        Client c = bank.getClient(name);
        if (c == null) {
            setTextViewById(R.id.lableResult, String.format("Error: Client %s does not exist.", name));
        }
        setTextViewById(R.id.lableResult, String.format("Statement of client %s (current balance $%.2f):\n%s", name, c.getAmount(), c.printHistory()));

    }

}
