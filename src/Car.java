public class Car {
    private String carName;
    private Road road; // road that the car is on
    private int carPosition = 0;
    Car(Road road, String name){
        this.road = road;
        carName = name;
    }

    private int getCarPosition(){ return carPosition; } // shows where the car is on the current road

    private void setCurrentRoad(Road road){
        this.road = road;
    }
    private boolean checkIfAtEndOfRoad(){
        return (carPosition == road.getRoadLength());
    }
    public boolean canMoveForward(Road road){
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
    public Road nextRoad(){
        return Map.roads.get(getIndexOfCurrentRoad() + 1);
    }


    public void move() {
        for (int i = 1; i <= road.getRoadLength(); i++) {
            if(carPosition == road.getRoadLength()-1) {
                road.getTrafficLight().operate();  // fix for non TL
                System.out.println(road.getTrafficLight().getCurrentColor());
            }
            if(canMoveForward(road)) {
                carPosition += 1;
                System.out.println(carName + " position:" + getCarPosition() + "m");
                if (checkIfAtEndOfRoad()) {
                    setCurrentRoad(nextRoad());
                    System.out.println("new road");
                    i = -1;
                    carPosition = 0;

                }
            }
            else
                i--;

        }
    }

}
