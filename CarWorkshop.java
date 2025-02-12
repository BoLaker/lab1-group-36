import java.util.ArrayList;

public class CarWorkshop<T extends Car>{
    private final int maxCars;
    private ArrayList<T> cars = new ArrayList<>();
    
    public CarWorkshop(int maxCars){
        this.maxCars = maxCars;
    }
    public void loadCar(T car){
        if (cars.size() < maxCars){
            cars.add(car);
        }
        else{
            throw new IllegalArgumentException("Verkstaden är full");
        }
    }
    public T unloadCar(T car){
        if (!cars.isEmpty()){
            cars.remove(car);
            return car;
        }
        throw new IllegalArgumentException("Ingen bil att hämta");
    }
    public ArrayList<T> getcars(){
        return cars;
    }

}