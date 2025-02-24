public interface CarButtonListener {
    void gas(int amount);
    void brake(int amount);
    void startAllCars();
    void stopAllCars();
    void raiseTrailer(int amount);
    void lowerTrailer(int amount);
    void setTurboOn();
    void setTurboOff();
}

