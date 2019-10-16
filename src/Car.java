public class Car {
    private int carPosition; // value that determines car position on road
    private Road currentRoad;
    Car(Road currentRoad){
        this.currentRoad = currentRoad;
    }

    public int getCarPosition(){ return carPosition; } // shows where the car is on the current road

    public void setCurrentRoad(Road road){
        this.currentRoad = road;
    } // changes the road that the car is on

    public void move(TrafficLight light){
        for(int i=0; i < currentRoad.numOfSegments; i++){
            if(i >= currentRoad.numOfSegments-2)
                light.operate();
            carPosition = i;
            if(light.getCurrentColor().equals("red") && carPosition == currentRoad.numOfSegments-1) {
                i = i-1; //stops car position
            }
            if(light.getCurrentColor() == "green")
                System.out.println("car position:" + carPosition * 10 + "m" + " green light");
            else
                System.out.println("car position:" + carPosition * 10 + "m" + " red light");
            //moving(light, i);

        }
    }
}
