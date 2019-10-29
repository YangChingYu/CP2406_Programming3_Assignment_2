import java.awt.*;

public class Car {
    private String carName;
    private Road road; // road that the car is on
    private int carPosition = 0; // position on current road
    protected int yPos; // current position on map
    protected int xPos = 0; // current position on map
    protected int  height;
    protected int width;
    public void paintMe(Graphics g){
    }
    Car(Road road, String name){
        this.road = road;
        carName = name;
        yPos = getRoadCarIsOn().roadYPos+10;
    }
    public Road getRoadCarIsOn(){
        return road;
    }
    public int getCarPosition(){ return carPosition; }
    private int getCarXPosition(){ return xPos; }

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
                xPos +=1;
                System.out.println(carName + " position:" + getCarXPosition() + "m");
                if (checkIfAtEndOfRoad()) {
                    setCurrentRoad(nextRoad());
                    carPosition = 0;

                }
            }

    }

}
