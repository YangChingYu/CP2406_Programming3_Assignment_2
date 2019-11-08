import java.awt.*;
import java.util.ArrayList;

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
        return (xPos+width >= (road.getRoadLength()*25) + road.getRoadXPos());
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
    private boolean canMoveForward(){
        if(xPos+width >= getRoadCarIsOn().getRoadLength()*25-25+getRoadCarIsOn().getRoadXPos()) {
            if (getRoadCarIsOn().getTrafficLight() == null) {
                return true;
            }
            else {
                TrafficLight light = getRoadCarIsOn().getTrafficLight();
                return light.getCurrentColor().equals("green");
            }
        }
        return true;
    }
    private int getIndexOfCurrentRoad(){
        return Map.roads.indexOf(road);
    }
    private Road nextRoad() {
        Road currentRoad = Map.roads.get(getIndexOfCurrentRoad());
        Road nextRoad = Map.roads.get(0);
        ArrayList<Integer> xPositions = new ArrayList<Integer>();
        for (int i = 0; i < Map.roads.size(); i++) {
            if(Map.roads.get(i) != currentRoad) {
                xPositions.add(Map.roads.get(i).getRoadXPos());
            }
        }
        int num;
        num = getCarXPosition(); //trying to find road with x position closest to this x position
        int index = 0;
        int difference_1 = 10000;
        for (int j = 0; j < xPositions.size(); j++) { // loops through every position
            int Difference_x = Math.abs(xPositions.get(j) - num);
            if (Difference_x < difference_1) { // checks if difference is getting smaller
                index = j;
                difference_1 = Difference_x;
            }
        }
        int closestXPosition = xPositions.get(index);
        for(int z = 0; z<Map.roads.size();z++){
            Road road = Map.roads.get(z);
            if(road.getRoadXPos() == closestXPosition && road != currentRoad){
                nextRoad = road;
            }
        }
        return nextRoad;
    }


    public void move() {
        if(canMoveForward()) {
            xPos += 25;
            if (checkIfAtEndOfRoad()) {
                try {
                    Road r = nextRoad();
                    setCurrentRoad(r);
                    xPos = r.getRoadXPos();
                    yPos = r.getRoadYPos() + 5;
                }
                catch (IndexOutOfBoundsException e){
                    setCurrentRoad(road);
                    xPos = road.getRoadXPos();
                    yPos = road.getRoadYPos() + 5;
                }
            }
        }

    }

}
