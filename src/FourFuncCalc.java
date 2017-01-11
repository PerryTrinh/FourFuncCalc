import java.awt.*;
import java.awt.event.*;

public class FourFuncCalc extends Frame {
    private Button[] btnNumbers;
    private String[] buttons;
    private TextField tfDisplay;
    private int calcHeight = 450;
    private int calcWidth = 350;
    private String count, prev;
    private String operator;

    public FourFuncCalc() {
        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        tfDisplay.setEditable(false);
        panelDisplay.add(tfDisplay);
        operator = "";
        prev = "0";
        count = "0";

        Panel panelButtons = new Panel(new GridLayout(4, 4, 5, 5));
        buttons = new String[] {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};
        btnNumbers = new Button[buttons.length];

        for(int i = 0; i < buttons.length; i++) {
            btnNumbers[i] = new Button(buttons[i]);
            panelButtons.add(btnNumbers[i]);
        }

        setLayout(new BorderLayout());
        add(panelDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        ButtonListener listener = new ButtonListener();
        for(Button i: btnNumbers) {
            i.addActionListener(listener);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setTitle("Four Function Calculator");
        setSize(calcWidth, calcHeight);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - calcWidth)/2;
        int y = (dim.height - calcHeight)/2;

        setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FourFuncCalc();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = ((Button) e.getSource()).getLabel();

            if(Solver.isNumeric(symbol)) {
                if(count.equals("0")) {
                    count = symbol;
                } else {
                    count += symbol;
                }
                tfDisplay.setText(count);
            } else if(symbol.equals("C")) {
                count = "0";
                prev = "0";
                operator = "";
                tfDisplay.setText(count);
            } else { //symbol is an operator
                if(operator.equals("")) {
                    prev = count;
                    count = "0";
                } else if(!operator.equals("=")){
                    prev = Solver.evaluate(operator, prev, count);
                    count = "0";
                    tfDisplay.setText(prev);
                }
                operator = symbol;
            }
        }
    }
}