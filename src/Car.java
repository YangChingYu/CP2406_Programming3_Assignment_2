public class Car {
    private int carPosition; // value that determines car position on road
    private Road currentRoad;
    Car(){
        this.currentRoad = Map.roads.get(0);;
    }

    public int getCarPosition(){ return carPosition; } // shows where the car is on the current road

    public void setCurrentRoad(Road road){
        this.currentRoad = road;
    } // changes the road that the car is on

    public void move(){
        for(int i=0; i< currentRoad.getRoadLength(); i++) {
            carPosition += 1;
        }
    }

    public void move(TrafficLight light){
        for(int i=0; i< currentRoad.getRoadLength(); i++) {
            light.operate();
            if (light.getCurrentColor().equals("red") && getCarPosition() == currentRoad.getRoadLength() - 1) {
                System.out.println("car stopped");
            }
            else
                carPosition += 1;
            System.out.println("car position:" + getCarPosition() * 10 + "m");
        }
    }

}
