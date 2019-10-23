public class Car {
    private String carName;
    private Road road; // road that the car is on
    private int carPosition = 0;
    Car(Road road, String name){
        this.road = road;
        carName = name;
    }
    public Road getRoadCarIsOn(){
        return road;
    }
    public int getCarPosition(){ return carPosition; } // shows where the car is on the current road

    private void setCurrentRoad(Road road){
        this.road = road;
    }
    private boolean checkIfAtEndOfRoad(){
        return (carPosition == road.getRoadLength());
    }
    private boolean canMoveForward(Road road){
        if(road.getTrafficLight() == null){
            return true;
        }
        else {
            TrafficLight light = road.getTrafficLight();
            return light.getCurrentColor().equals("green");
        }
    }

    private int getIndexOfCurrentRoad(){
        return Map.roads.indexOf(road);
    }
    private Road nextRoad(){
        return Map.roads.get(getIndexOfCurrentRoad() + 1);
    }


    public void move() {
            if(canMoveForward(road)) {
                carPosition += 1;
                System.out.println(carName + " position:" + getCarPosition() + "m");
                if (checkIfAtEndOfRoad()) {
                    setCurrentRoad(nextRoad());
                    carPosition = 0;

                }
            }

    }

}
