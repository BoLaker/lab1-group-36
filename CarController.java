import javax.swing.JButton;  
import javax.swing.*; 
import java.awt.event.ActionListener;
public class CarController {
    private CarModel model;
    private CarView view;

    
    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");
    private JButton turboOnButton = new JButton("Saab Turbo On");
    private JButton turboOffButton = new JButton("Saab Turbo Off");
    private JButton liftBedButton = new JButton("Scania Lift Bed");
    private JButton lowerBedButton = new JButton("Lower Lift Bed");
    private JButton addCarButton = new JButton("Add Car");
    private JButton removeCarButton = new JButton("Remove Car");

    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;

        
        view.addButtonToPanel(gasButton);
        view.addButtonToPanel(brakeButton);
        view.addButtonToPanel(startButton);
        view.addButtonToPanel(stopButton);
        view.addButtonToPanel(turboOnButton);
        view.addButtonToPanel(turboOffButton);
        view.addButtonToPanel(liftBedButton);
        view.addButtonToPanel(lowerBedButton);
        view.addButtonToPanel(addCarButton);
        view.addButtonToPanel(removeCarButton);

        
        gasButton.addActionListener(e -> model.gas(view.getGasAmount()));
        brakeButton.addActionListener(e -> model.brake(view.getGasAmount()));
        startButton.addActionListener(e -> model.startAllCars());
        stopButton.addActionListener(e -> model.stopAllCars());
        turboOnButton.addActionListener(e -> model.setTurboOn());
        turboOffButton.addActionListener(e -> model.setTurboOff());
        liftBedButton.addActionListener(e -> model.raiseTrailer(70));
        lowerBedButton.addActionListener(e -> model.lowerTrailer(70));
        addCarButton.addActionListener(e -> model.addCar());
        removeCarButton.addActionListener(e -> model.removeCar());
    }
}

