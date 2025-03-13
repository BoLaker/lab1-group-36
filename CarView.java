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

 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 
 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 
 public class CarView extends JFrame implements CarObserver {
     private static final int X = 800;
     private static final int Y = 800;
 
     
     private DrawPanel drawPanel = new DrawPanel(X, Y - 240);
     private JPanel controlPanel = new JPanel();
 
     private JSpinner gasSpinner;
 
    
     public CarView(String framename) {
          
         this.setTitle(framename);
         this.setPreferredSize(new Dimension(X, Y));
         this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
         this.add(drawPanel);
 
         
         gasSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));  
         controlPanel.setLayout(new GridLayout(2, 5)); 
         controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
         controlPanel.add(gasSpinner);  
         this.add(controlPanel);
 
         this.pack();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
         this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
 
     
     public int getGasAmount() {
         return (int) gasSpinner.getValue();  
     }
 
     
     public void addButtonToPanel(JButton button) {
         controlPanel.add(button);
     }
 
     @Override
     public void updateCars(ArrayList<Car> cars) {
         drawPanel.setCars(cars);
         drawPanel.repaint();
     }
 }
 
 
 

