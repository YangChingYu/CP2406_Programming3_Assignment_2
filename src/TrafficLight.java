public class TrafficLight {
    private double rateOfChange = 0.3;
    private String currentColor = "green";

    public String getCurrentColor(){
        return currentColor;
    }

    public void operate(){
        double num = Math.random(); // gets random number between 0 and 1
        if(num < rateOfChange) {  // checks whether light should be green or red
            currentColor = "red";
        }
        else
            currentColor = "green";
    }
}
