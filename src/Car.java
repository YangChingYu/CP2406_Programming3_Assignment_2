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
            moving(light, i);

        }
    }
    private void moving(TrafficLight light, int carPosition){
        if(light.getCurrentColor() == "green"){
            this.carPosition = carPosition;
        }
        else {
            for(int j=0; j<3; j++){ // red light timer
                if(carPosition == currentRoad.numOfSegments){
                    this.carPosition = carPosition-1;
                }
                this.carPosition = carPosition;
                System.out.print(this.carPosition * 10 + "m" + " ");
                System.out.print("redLight ");


            }
        }
        System.out.print(this.carPosition * 10 + "m" + " ");

    }
}
