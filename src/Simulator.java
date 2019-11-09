import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class Simulator implements ActionListener, Runnable {
    private boolean running = false;
    private JFrame frame = new JFrame("traffic sim");
    private TrafficLight light = new TrafficLight();
    Road roadStart = new Road(6, "horizontal",0, 270, "east", light); // fixed starting road on map

    //south container
    private JButton startSim = new JButton("start");
    private JButton exitSim = new JButton("exit");
    Container south = new Container();

    //west container
    Container west = new Container();
    JButton addSedan = new JButton("add sedan");
    JButton addBus = new JButton("add bus");
    JButton addRoadWithLight = new JButton("add road");

    private Simulator(){

        Map.roads.add(roadStart);
        frame.setSize(1200,700);
        frame.setLayout(new BorderLayout());
        frame.add(roadStart, BorderLayout.CENTER);

        south.setLayout(new GridLayout(1, 2));
        south.add(startSim);
        startSim.addActionListener(this);
        south.add(exitSim);
        exitSim.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);

        west.setLayout(new GridLayout(3,1));
        west.add(addSedan);
        addSedan.addActionListener(this);
        west.add(addBus);
        addBus.addActionListener(this);
        west.add(addRoadWithLight);
        addRoadWithLight.addActionListener(this);
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
            Bus bus = new Bus(roadStart);
            Map.cars.add(bus);
            for (int x = roadStart.roadXPos; x < bus.getRoadCarIsOn().getRoadLength()*50; x = x + 30) {
                bus.setCarXPosition(x);
                bus.setCarYPosition(bus.getRoadCarIsOn().getRoadYPos()+5);
                if(!bus.collision(x, bus)){
                    frame.repaint();
                    return;
                }
            }
        }
        if(source == addSedan){
            Sedan sedan = new Sedan(roadStart);
            Map.cars.add(sedan);
            sedan.setCarYPosition(sedan.getRoadCarIsOn().getRoadYPos()+7);
            for (int x = roadStart.roadXPos; x < sedan.getRoadCarIsOn().getRoadLength()*50; x = x + 30) {
                sedan.setCarXPosition(x);
                if(!sedan.collision(x, sedan)){
                    frame.repaint();
                    return;
                }

            }
        }
        if(source == addRoadWithLight){
            Boolean incorrect = true;
            int length;
            String orientation;
            String direction = "";
            int xPos = 0;
            int yPos = 270;
            length = Integer.parseInt(JOptionPane.showInputDialog("enter road length"));
            orientation = JOptionPane.showInputDialog("enter road orientation\n vertical or horizontal").toLowerCase();
            if (orientation.equals("horizontal")){
                yPos = Integer.parseInt(JOptionPane.showInputDialog("enter road y Position"));
                xPos = Integer.parseInt(JOptionPane.showInputDialog("enter road x Position"));
            }
            else{
                xPos = Integer.parseInt(JOptionPane.showInputDialog("enter road y Position"));
                yPos = Integer.parseInt(JOptionPane.showInputDialog("enter road x Position"));
            }
            while(incorrect){
                direction = (JOptionPane.showInputDialog("enter direction of traffic")).toLowerCase();
                if(direction.equals("east") && orientation.equals("horizontal") || direction.equals("west") && orientation.equals("horizontal")){
                    incorrect = false;
                }
                else if(direction.equals("north") && orientation.equals("vertical") || direction.equals("south") && orientation.equals("vertical")){
                    incorrect = false;
                }
                else
                    JOptionPane.showMessageDialog(null, "incorrect input ");
            }
            Road road = new Road(length, orientation,xPos, yPos, direction, new TrafficLight());
            Map.roads.add(road);
            frame.repaint();

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
                        if(!currentCar.collision(currentCar.getCarXPosition()+30, currentCar)){
                            currentCar.move();
                        }


                }
                frame.repaint();

        }
    }
}
