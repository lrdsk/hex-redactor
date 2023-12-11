package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentDecimalByteForm {
    private final JFrame jFrame;
    private final JLabel jLabel;
    private String hexCell;
    private JButton buttonToDecimalSigned;
    private JButton buttonToDecimalUnsigned;

    public CurrentDecimalByteForm(String hexCell) {
        this.jLabel = new JLabel(hexCell);
        this.buttonToDecimalSigned = new JButton("В десятичное со знаком");
        this.buttonToDecimalUnsigned = new JButton("В десятичное без знака");
        this.jFrame = new JFrame("Представление байта в десятичной системе");
        this.hexCell = hexCell;
    }
    public void create(){
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(new GridBagLayout());
        jFrame.add(jLabel, new GridBagConstraints(0,0,2,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.CENTER,
                new Insets(1,1,1,1),0,0));
        jFrame.add(buttonToDecimalSigned, new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,
                new Insets(1,1,1,1),0,0));
        jFrame.add(buttonToDecimalUnsigned, new GridBagConstraints(1,1,1,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.CENTER,
                new Insets(1,1,1,1),0,0));

        buttonToDecimalSigned.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int decimalNumber = Integer.parseInt(hexCell, 16);
                if ((decimalNumber & 0x8000) != 0) {
                    decimalNumber -= 0x10000;
                }
                jLabel.setText(String.valueOf(decimalNumber));
            }
        });

        buttonToDecimalUnsigned.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel.setText(String.valueOf(Integer.parseUnsignedInt(hexCell,16)));
            }
        });

        jFrame.pack();
        jFrame.setVisible(true);
        System.out.println(hexCell);
    }
}
