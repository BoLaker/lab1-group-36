public class main {
    public static void main(String[] args) {
        
        
        CarModel model = new CarModel(carFactory.createCars());
        CarView view = new CarView("CarSim 1.0");
        model.addObserver(view);
        CarController controller = new CarController(model, view);
        
    }
}
