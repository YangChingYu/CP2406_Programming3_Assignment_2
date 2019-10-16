public class Simulator {

    public static void main(String[] args){
        Road road1 = new Road(10, true);
        Road road2 = new Road(15, false);
        Car car = new Car(road1);
        car.move();
        car.setCurrentRoad(road2);
        car.move();

    }
}
