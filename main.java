public class main {
    public static void main(String[] args) {
        // Start a new view and send a reference of self
        carFactory factory = new carFactory();
        CarController controller = new CarController(factory.creatCars());
        CarView view = new CarView("CarSim 1.0", controller);
        controller.addObserver(view); // Koppla View som observer
    }
}
