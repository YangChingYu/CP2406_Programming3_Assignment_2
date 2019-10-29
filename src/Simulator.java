import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class Simulator implements ActionListener, Runnable {
    private boolean roadExists = true;
    private JFrame frame = new JFrame("traffic sim");
    private TrafficLight light = new TrafficLight();
    Road road1 = new Road(10, light);

    //south container
    private JButton startSim = new JButton("start");
    private JButton exitSim = new JButton("exit");
    Container south = new Container();

    private Simulator(){

        Map.roads.add(road1);
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.add(road1, BorderLayout.CENTER);

        south.setLayout(new GridLayout(1, 2));
        south.add(startSim);
        startSim.addActionListener(this);
        south.add(exitSim);
        exitSim.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Sedan car = new Sedan(road1, "ford Falcon");
        Map.cars.add(car);
        Map.trafficLights.add(light);
        frame.repaint();

    }

    public static void main(String[] args){
        new Simulator();
        Map map = new Map();

    }

    private void cycle(){
        while(roadExists){
            try{
                Thread.sleep(500);
            }
            catch(Exception ignored){}
            for(int j = 0; j < Map.trafficLights.size(); j++){
                Map.trafficLights.get(j).operate();
            }
            for (int i = 0; i < Map.cars.size(); i++) {
                try {
                    Car currentCar = Map.cars.get(i);
                    if(currentCar.getRoadCarIsOn().getTrafficLight() != null && currentCar.getCarPosition() == currentCar.getRoadCarIsOn().getRoadLength()-1) {
                        System.out.println(currentCar.getRoadCarIsOn().getTrafficLight().getCurrentColor());
                    }
                    currentCar.move();
                    frame.repaint();
                } catch (Exception e) {
                    roadExists = false;
                    System.out.println("end of road");
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == startSim){
            Thread t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        while(roadExists){
            try{
                Thread.sleep(500);
            }
            catch(Exception ignored){}
            for(int j = 0; j < Map.trafficLights.size(); j++){
                Map.trafficLights.get(j).operate();
            }
            for (int i = 0; i < Map.cars.size(); i++) {
                try {
                    Car currentCar = Map.cars.get(i);
                    if(currentCar.getRoadCarIsOn().getTrafficLight() != null && currentCar.getCarPosition() == currentCar.getRoadCarIsOn().getRoadLength()-1) {
                        System.out.println(currentCar.getRoadCarIsOn().getTrafficLight().getCurrentColor());
                    }
                    currentCar.move();
                } catch (Exception e) {
                    roadExists = false;
                    System.out.println("end of road");
                }
            }
            frame.repaint();
        }
    }
}
