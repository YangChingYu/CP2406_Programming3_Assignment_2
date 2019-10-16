public class TrafficLight {
    private double rateOfChange = 0.3;
    private String currentColor;

    public void operate(){
        double num = Math.random(); // gets random number between 0 and 1
        double chanceOfChanging = Math.round(num); // rounds that number
        if(chanceOfChanging <= rateOfChange) {  // checks whether light should be green or red
            currentColor = "red";
        }
        else
            currentColor = "green";
    }
}
