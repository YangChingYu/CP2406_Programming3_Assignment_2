
public class Simulator {
    boolean roadExists = true;

    public static void main(String[] args){
        Simulator sim = new Simulator();
        TrafficLight light = new TrafficLight();
        TrafficLight light2 = new TrafficLight();
        Road road1 = new Road(4, light);
        Road road2 = new Road(6, light2);
        Road road3 = new Road(5);
        Car car = new Car(road1, "ford Falcon");
        Car car2 = new Car(road3, "honda crv");
        Map map = new Map();
        map.addRoad(road1);
        map.addRoad(road2);
        map.addRoad(road3);
        map.addTrafficLight(light);
        map.addTrafficLight(light2);
        map.addCar(car);
        //map.addCar(car2);
        sim.cycle();
    }
    private void cycle(){
        while(roadExists){
            for(int j = 0; j < Map.trafficLights.size(); j++){
                Map.trafficLights.get(j).operate();
            }
            for (int i = 0; i < Map.cars.size(); i++) {
                try {
                    Car currentCar = Map.cars.get(i);
                    if(currentCar.getRoadCarIsOn().getTrafficLight() != null && currentCar.getCarPosition() == currentCar.getRoadCarIsOn().getRoadLength()-1) {
                        System.out.println(currentCar.getRoadCarIsOn().getTrafficLight().getCurrentColor());
                    }
                    currentCar.move();
                } catch (Exception e) {
                    roadExists = false;
                    System.out.println("end of road");
                }
            }
        }
    }

}
