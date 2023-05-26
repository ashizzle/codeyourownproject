import javax.swing.*;

public class CurrencyConverterFrame extends JFrame {
    public CurrencyConverterFrame() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        CurrencyConverterPanel panel = new CurrencyConverterPanel();
        add(panel);
    }
}