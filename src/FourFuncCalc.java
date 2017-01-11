import java.awt.*;
import java.awt.event.*;

public class FourFuncCalc extends Frame {
    private Button[] btnNumbers;
    private String[] buttons;
    private TextField tfDisplay;
    private int calcWidth = 450;
    private int calcLength = 350;
    private int count;
    private String operator;
    
    public FourFuncCalc() {
        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        panelDisplay.add(tfDisplay);
        operator = "";

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
        setSize(calcLength, calcWidth);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w)/2;
        int y = (dim.height - h)/2;

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
                if(!operator.equals("")) {
                    count = Solver.evaluate(operator, count, Integer.parseInt(symbol));
                    operator = "";
                } else {
                    count = Integer.parseInt(symbol);
                }
                tfDisplay.setText(symbol + "");
            } else {
                if(symbol.equals("C")) {
                    count = 0;
                } else {
                    operator = symbol;
                }
                tfDisplay.setText(count + "");
            }
        }
    }
}