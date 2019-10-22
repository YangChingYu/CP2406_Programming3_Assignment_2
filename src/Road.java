
public class Road {
    private TrafficLight light;
    private int numOfSegments; //length of road

    Road(int numOfSegments){
        this.numOfSegments = numOfSegments;
    }
    Road(int numOfSegments, TrafficLight light){
        this.numOfSegments = numOfSegments;
        this.light = light;

    }
    public TrafficLight getTrafficLight(){
        return light;
    }
    public int getRoadLength(){
        return numOfSegments;
    }

}
