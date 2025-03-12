import java.util.ArrayList;
public class carFactory{
    public carFactory(){
        
    }
    public ArrayList<Car> creatCars(){
        ArrayList<Car> cars = new ArrayList<>();

        Volvo240 volvo = new Volvo240();
        volvo.setPosition(0, 300);
        volvo.turnRight();

        Saab95 saab = new Saab95();
        saab.setPosition(0, 100);
        saab.turnRight();

        Scania scania = new Scania();
        scania.setPosition(0, 200);
        scania.turnRight();

        cars.add(volvo);
        cars.add(saab);
        cars.add(scania);

        return cars;
    }
}