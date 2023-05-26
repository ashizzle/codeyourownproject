import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterPanel extends JPanel {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JButton convertButton;
    private JLabel resultLabel;

    public CurrencyConverterPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 5);

        fromCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP"});
        add(new JLabel("From:"), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(fromCurrencyComboBox, gbc);

        gbc.gridx = 2;
        add(new JLabel("To:"), gbc);

        gbc.gridx = 3;
        toCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP"});
        add(toCurrencyComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        amountTextField = new JTextField(15);
        add(amountTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(15, 10, 5, 10);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        add(convertButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        add(new JLabel("Result:"), gbc);

        gbc.gridy = 4;
        resultLabel = new JLabel();
        add(resultLabel, gbc);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
            String amountText = amountTextField.getText();

            try {
                double amount = Double.parseDouble(amountText);
                double result = convertCurrency(fromCurrency, toCurrency, amount);
                resultLabel.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CurrencyConverterPanel.this, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        // Add your currency conversion logic here
        // Example exchange rates as of September 2021
        double usdToEur = 0.85;
        double usdToGbp = 0.72;
        double eurToUsd = 1.18;
        double eurToGbp = 0.85;
        double gbpToUsd = 1.38;
        double gbpToEur = 1.17;

        double result = amount;
        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) {
                result *= usdToEur;
            } else if (toCurrency.equals("GBP")) {
                result *= usdToGbp;
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                result *= eurToUsd;
            } else if (toCurrency.equals("GBP")) {
                result *= eurToGbp;
            }
        } else if (fromCurrency.equals("GBP")) {
            if (toCurrency.equals("USD")) {
                result *= gbpToUsd;
            } else if (toCurrency.equals("EUR")) {
                result *= gbpToEur;
            }
        }

        return result;
    }
}