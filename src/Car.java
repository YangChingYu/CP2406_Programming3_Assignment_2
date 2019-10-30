import java.awt.*;

public class Car {
    private Road road; // road that the car is on
    private int carPosition = 0; // position on current road
    protected int yPos; // current position on map
    protected int xPos; // current position on map
    protected int  height;
    protected int width;
    public void paintMe(Graphics g){
    }
    Car(Road road){
        this.road = road;
        yPos = getRoadCarIsOn().roadYPos+10;
        xPos = getRoadCarIsOn().roadXPos;
    }
    public Road getRoadCarIsOn(){
        return road;
    }
    public int getCarPosition(){ return carPosition; }
    private int getCarXPosition(){ return xPos; }
    public void setCarXPosition(int x){
        xPos = x;
    }
    private int getCarYPosition(){ return yPos; }
    public void setCarYPosition(int y){
        yPos = y;
    }
    public int getCarWidth(){return width;}

    private void setCurrentRoad(Road road){
        this.road = road;
    }
    private boolean checkIfAtEndOfRoad(){
        return (carPosition == road.getRoadLength());
    }
    public boolean collision(int x, int width, Car car){
        for (int i = 0; i < Map.cars.size(); i++){
            Car c = Map.cars.get(i);
                if(!car.equals(c)){ // if not checking current car
                    if(car.getCarXPosition() < c.getCarXPosition() + c.getCarWidth() && //left side is left  of cars right side
                            car.getCarXPosition() + car.getCarWidth() > c.getCarXPosition()){ // right side right of his left side
                        return true;
                    }
                }
            }
        return false;
    }
    private boolean canMoveForward(Road road){
        if(carPosition == road.getRoadLength()-1) {
            if (road.getTrafficLight() == null) {
                return true;
            } else {
                TrafficLight light = road.getTrafficLight();
                return light.getCurrentColor().equals("green");
            }
        }
        return true;
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
                xPos += 50;
                if (checkIfAtEndOfRoad()) {
                    carPosition = 0;
                    try {
                        setCurrentRoad(nextRoad());
                    }
                    catch(IndexOutOfBoundsException e){
                        xPos = 0;
                        System.out.println("end of road");
                    }

                }
            }

    }

}
