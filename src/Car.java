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
    public int getCarYPosition(){ return yPos; }
    public void setCarYPosition(int y){
        yPos = y;
    }
    public int getCarWidth(){return width;}

    private void setCurrentRoad(Road road){
        this.road = road;
    }
    private boolean checkIfAtEndOfRoad(){
        if(road.getTrafficDirection().equals("east") || road.getTrafficDirection().equals("south")){
            return (xPos+width >= (road.getRoadLength()*25) + road.getRoadXPos());
        }
        else if(road.getTrafficDirection().equals("west") || road.getTrafficDirection().equals("north")){
            return (xPos <= (road.getRoadXPos()));
        }
        else
            return true;
    }
    public boolean collision(int x, Car car){
        String direction = getRoadCarIsOn().getTrafficDirection();
        for (int i = 0; i < Map.cars.size(); i++) {
            Car c = Map.cars.get(i);
            if (c.getRoadCarIsOn() == getRoadCarIsOn() && car.getCarYPosition() == c.getCarYPosition()) {
                int otherCarXPosition = c.getCarXPosition();
                int otherCarWidth = c.getCarWidth();
                if (!car.equals(c)) { // if not checking current car
                    if (x < otherCarXPosition + otherCarWidth && //left side is left  of cars right side
                            x + otherCarWidth > otherCarXPosition && (direction.equals("east") || direction.equals("south"))) { // right side right of his left side
                        return true;
                    }
                    else if (x < otherCarXPosition + otherCarWidth * 2 - 15 && x + car.getCarWidth() > otherCarXPosition &&
                            (direction.equals("west") || direction.equals("north"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean canMoveForward(){
        String direction = getRoadCarIsOn().getTrafficDirection();
        if(xPos+width >= getRoadCarIsOn().getRoadLength()*25-25+getRoadCarIsOn().getRoadXPos() && (direction.equals("east") || direction.equals("south"))
                || xPos <= getRoadCarIsOn().getRoadXPos()+25 && ( direction.equals("west") || direction.equals("north") )) {
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
            if(road.getTrafficDirection().equals("east") || road.getTrafficDirection().equals("south")) {
                xPos += 25;
            }
            else if(road.getTrafficDirection().equals("west") || road.getTrafficDirection().equals("north")){
                xPos -= 25;
            }
            if (checkIfAtEndOfRoad()) {
                try {
                    Road r = nextRoad();
                    setCurrentRoad(r);
                    if(road.getOrientation().equals("horizontal") && road.getTrafficDirection().equals("east") || road.getOrientation().equals("vertical") && road.getTrafficDirection().equals("south")) {
                        for (int x = getRoadCarIsOn().getRoadXPos(); x < getRoadCarIsOn().getRoadLength()*25; x = x + 30) {
                            setCarXPosition(x);
                            setCarYPosition(getRoadCarIsOn().getRoadYPos()+5);
                            if(!collision(x, this)){
                                return;
                            }
                        }
                    }
                    else if(road.getOrientation().equals("horizontal") && road.getTrafficDirection().equals("west") || road.getOrientation().equals("vertical") && road.getTrafficDirection().equals("north")){
                        for (int x = getRoadCarIsOn().getRoadXPos() + getRoadCarIsOn().getRoadLength()*25 - getCarWidth(); x > getRoadCarIsOn().getRoadXPos(); x = x - 30) {
                            setCarXPosition(x);
                            setCarYPosition(getRoadCarIsOn().getRoadYPos()+5);
                            if(!collision(x, this)){
                                return;
                            }
                        }
                    }
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
