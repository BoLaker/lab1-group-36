import java.util.ArrayList;
public class carFactory {

    private carFactory() {
        
    }

    public static ArrayList<Car> createCars() {
        ArrayList<Car> cars = new ArrayList<>();

        cars.add(createVolvo());
        cars.add(createSaab());
        cars.add(createScania());

        return cars;
    }

    public static Volvo240 createVolvo() {
        Volvo240 volvo = new Volvo240();
        volvo.setPosition(0, 300);
        volvo.turnRight();
        return volvo;
    }

    public static Saab95 createSaab() {
        Saab95 saab = new Saab95();
        saab.setPosition(0, 100);
        saab.turnRight();
        return saab;
    }

    public static Scania createScania() {
        Scania scania = new Scania();
        scania.setPosition(0, 200);
        scania.turnRight();
        return scania;
    }
}
