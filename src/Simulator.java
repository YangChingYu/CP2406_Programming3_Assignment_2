public class Simulator {

    public static void main(String[] args){
        TrafficLight light = new TrafficLight();
        Road road1 = new Road(10, true);
        road1.addTrafficLight(light);
        Road road2 = new Road(15, false);
        Car car = new Car(road1);
        car.move(light);
        //car.setCurrentRoad(road2);
        //car.move();

    }
}
