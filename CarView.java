import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

 public class CarView extends JFrame implements CarObserver {
    private static final int X = 800;
    private static final int Y = 800;
    
    // Lagra en referens till CarButtonListener
    CarButtonListener carButtonListener;

    DrawPanel drawPanel = new DrawPanel(X, Y - 240);
    private JPanel controlPanel = new JPanel();
    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private int gasAmount = 0;
    private JLabel gasLabel = new JLabel("Amount of gas");

    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton turboOnButton = new JButton("Saab Turbo on");
    private JButton turboOffButton = new JButton("Saab Turbo off");
    private JButton liftBedButton = new JButton("Scania Lift Bed");
    private JButton lowerBedButton = new JButton("Lower Lift Bed");
    private JButton addCarButton = new JButton("Add Car");
    private JButton removeCarButton = new JButton("Remove Car");

    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, CarButtonListener listener) {
        this.carButtonListener = listener;
        initComponents(framename);
    }

    // Metod för att initiera komponenterna
    public void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);

        // Spinner för gas
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner) e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 5));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton,7);
        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        // ActionListeners för knappar
        gasButton.addActionListener(e -> carButtonListener.gas(gasAmount));
        brakeButton.addActionListener(e -> carButtonListener.brake(gasAmount));
        liftBedButton.addActionListener(e -> carButtonListener.raiseTrailer(70));
        lowerBedButton.addActionListener(e -> carButtonListener.lowerTrailer(70));
        turboOnButton.addActionListener(e -> carButtonListener.setTurboOn());
        turboOffButton.addActionListener(e -> carButtonListener.setTurboOff());
        startButton.addActionListener(e -> carButtonListener.startAllCars());
        stopButton.addActionListener(e -> carButtonListener.stopAllCars());
        addCarButton.addActionListener(e -> carButtonListener.addCar());
        removeCarButton.addActionListener(e -> carButtonListener.removeCar());


        // Packa och centrera fönstret
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void updateCars(ArrayList<Car> cars) {
        drawPanel.setCars(cars);
        drawPanel.repaint();
    }
}
