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

    public void move(){
        for(int i=0; i < currentRoad.numOfSegments; i++){
            carPosition = i;
            System.out.print(carPosition + "m" + " ");
        }
    }
}
