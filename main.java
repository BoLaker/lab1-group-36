public class main {
    public static void main(String[] args) {
        
        carFactory factory = new carFactory();
        CarModel model = new CarModel(factory.creatCars());
        CarView view = new CarView("CarSim 1.0");
        model.addObserver(view);
        CarController controller = new CarController(model, view);
        
    }
}
