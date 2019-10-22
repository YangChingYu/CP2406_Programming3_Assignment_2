
public class Simulator {

    public static void main(String[] args){
        TrafficLight light = new TrafficLight();
        TrafficLight light2 = new TrafficLight();
        Road road1 = new Road(4, light);
        Road road2 = new Road(6, light2);
        Road road3 = new Road(5);
        Car car = new Car(road1, "ford Falcon");
        Car car2 = new Car(road1, "honda crv");
        Map map = new Map();
        map.addRoad(road1);
        map.addRoad(road2);
        map.addRoad(road3);
        map.addCar(car);
        for (int i = 0; i < Map.cars.size(); i++){
            try {
                Map.cars.get(i).move();
            }
            catch (Exception e){
                System.out.println("end of road");
            }
        }

    }

}
