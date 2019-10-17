import java.util.ArrayList;

public class Simulator {

    public static void main(String[] args){
        Road road1 = new Road(10, true);
        Road road2 = new Road(15, false);
        Map map = new Map();
        map.addRoad(road1);
        map.addRoad(road2);
        Car car = new Car();
        car.move(road1.getTLOnCurrentRoad());

    }

}
