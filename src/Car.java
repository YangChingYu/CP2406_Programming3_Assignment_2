public class Car {
    private int carPosition; // value that determines car position on road
    private Road currentRoad; // road that the car is on
    Car(){
        this.currentRoad = Map.roads.get(0); // car starts on first road created
    }

    public int getCarPosition(){ return carPosition; } // shows where the car is on the current road

    public void setCurrentRoad(Road road){
        this.currentRoad = road;
    } // changes the road that the car is on


    public void move(TrafficLight light) {
        for (int i = 0; i < currentRoad.getRoadLength(); i++) {
            if (currentRoad.checkForTrafficLight()) // checks whether road contains a traffic light
                light.operate();
            if (light.getCurrentColor().equals("red") && getCarPosition() == currentRoad.getRoadLength() - 1) {
                System.out.println("car stopped");
                i -= 1;
            } else
                carPosition += 1;
            if (carPosition == currentRoad.getRoadLength() && light.getCurrentColor().equals("green")) {
                setCurrentRoad(Map.roads.get(Road.nextRoad(currentRoad)));
                carPosition =  0;
                i = 0;
            }

            System.out.println("car position:" + getCarPosition() * 10 + "m");
        }
    }

}
