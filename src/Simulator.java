import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class Simulator implements ActionListener, Runnable {
    private boolean running = false;
    private boolean roadExists = true;
    private JFrame frame = new JFrame("traffic sim");
    private TrafficLight light = new TrafficLight();
    Road road1 = new Road(10, light);

    //south container
    private JButton startSim = new JButton("start");
    private JButton exitSim = new JButton("exit");
    Container south = new Container();

    //west container
    Container west = new Container();
    JButton addSedan = new JButton("add sedan");
    JButton addBus = new JButton("add bus");

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

        west.setLayout(new GridLayout(2,1));
        west.add(addSedan);
        addSedan.addActionListener(this);
        west.add(addBus);
        addBus.addActionListener(this);
        frame.add(west, BorderLayout.WEST);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Map.trafficLights.add(light);
        frame.repaint();

    }

    public static void main(String[] args){
        new Simulator();
        Map map = new Map();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == startSim){
            if(running == false) {
                running = true;
                Thread t = new Thread(this);
                t.start();
            }
        }
        if(source == addBus){
            Bus bus = new Bus(road1);
            Map.cars.add(bus);
            for (int x = 0; x < bus.getRoadCarIsOn().getRoadLength()*50; x = x + 60) {
                bus.setCarXPosition(x);
                bus.setCarYPosition(bus.getRoadCarIsOn().getRoadYPos()+10);
                if(!bus.collision(x, bus)){
                    frame.repaint();
                    return;
                }
            }
        }
        if(source == addSedan){
            Sedan sedan = new Sedan(road1);
            Map.cars.add(sedan);
            sedan.setCarYPosition(sedan.getRoadCarIsOn().getRoadYPos()+10);
            for (int x = 0; x < sedan.getRoadCarIsOn().getRoadLength()*50; x = x + 60) {
                sedan.setCarXPosition(x);
                if(!sedan.collision(x, sedan)){
                    frame.repaint();
                    return;
                }

            }
        }
    }

    @Override
    public void run() {
        while (running) {
                try {
                    Thread.sleep(300);
                }
                catch (Exception ignored) {
                }
                for (int j = 0; j < Map.roads.size(); j++) {
                    Road r = Map.roads.get(j);
                    TrafficLight l = r.getTrafficLight();
                    if(l != null) {
                        l.operate();
                        if (l.getCurrentColor().equals("red")) {
                            r.setLightColor(Color.red);
                        }
                        else{
                            r.setLightColor(Color.GREEN);
                        }
                    }

                }
                for (int i = 0; i < Map.cars.size(); i++) {
                        Car currentCar = Map.cars.get(i);
                        if (currentCar.getRoadCarIsOn().getTrafficLight() != null && currentCar.getCarPosition() == currentCar.getRoadCarIsOn().getRoadLength()-1) {
                            System.out.println(currentCar.getRoadCarIsOn().getTrafficLight().getCurrentColor());
                        }
                        if(!currentCar.collision(currentCar.getCarXPosition()+50, currentCar)){
                            currentCar.move();
                        }


                }
                frame.repaint();

        }
    }
}
