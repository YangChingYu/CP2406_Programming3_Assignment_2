import java.awt.*;

public class Car {
    private Road road; // road that the car is on
    protected int yPos; // current position on map
    protected int xPos; // current position on map
    protected int  height;
    protected int width;
    public void paintMeHorizontal(Graphics g){
    }
    public void paintMeVertical(Graphics g){
    }
    Car(Road road){
        this.road = road;
        yPos = getRoadCarIsOn().roadYPos;
        xPos = getRoadCarIsOn().roadXPos;
    }

    public Road getRoadCarIsOn(){
        return road;
    }

    public int getCarXPosition(){ return xPos; }
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
        return (xPos+width >= road.getRoadLength()*25);
    }
    public boolean collision(int x, Car car){
        for (int i = 0; i < Map.cars.size(); i++){
            Car c = Map.cars.get(i);
                if(!car.equals(c)){ // if not checking current car
                    if(x < c.getCarXPosition() + c.getCarWidth() && //left side is left  of cars right side
                            x + c.getCarWidth() > c.getCarXPosition()){ // right side right of his left side
                        return true;
                    }
                }
            }
        return false;
    }
    private boolean canMoveForward(Road road){
        if(xPos+width >= road.getRoadLength()*25-25) {
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
                xPos += 25;
                if (checkIfAtEndOfRoad()) {
                    try {
                        setCurrentRoad(nextRoad());
                    }
                    catch(IndexOutOfBoundsException e){
                        xPos = road.getRoadXPos();
                        yPos = road.getRoadYPos();
                        System.out.println("end of road");
                    }

                }
            }

    }

}
