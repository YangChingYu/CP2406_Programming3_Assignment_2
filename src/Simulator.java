import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;

public class Simulator implements ActionListener, Runnable {
    private boolean running = false;
    private JFrame frame = new JFrame("traffic sim");
    private TrafficLight light = new TrafficLight();
    Road roadStart = new Road(6, "horizontal",0, 270, "east", light); // fixed starting road on map

    //south container
    private JButton startSim = new JButton("start");
    private JButton exitSim = new JButton("exit");
    private JButton removeRoad = new JButton("remove last road");
    private Container south = new Container();
    //west container
    private Container west = new Container();
    private JButton addSedan = new JButton("add sedan");
    private JButton addBus = new JButton("add bus");
    private JButton addRoad = new JButton("add road");
    //road orientation selection
    private ButtonGroup selections = new ButtonGroup();
    private JRadioButton horizontal = new JRadioButton("horizontal");
    private JRadioButton vertical = new JRadioButton("vertical");
    //has traffic light selection
    private ButtonGroup selections2 = new ButtonGroup();
    private JRadioButton hasLight = new JRadioButton("traffic light(true)");
    private JRadioButton noLight = new JRadioButton("traffic light(false)");
    //road length
    private JLabel label = new JLabel("Enter road length");
    private JTextField length = new JTextField();

    private Simulator(){

        Map.roads.add(roadStart);
        frame.setSize(1200,700);
        frame.setLayout(new BorderLayout());
        frame.add(roadStart, BorderLayout.CENTER);

        //buttons on the south side
        south.setLayout(new GridLayout(1, 3));
        south.add(startSim);
        startSim.addActionListener(this);
        south.add(exitSim);
        exitSim.addActionListener(this);
        south.add(removeRoad);
        removeRoad.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);

        //buttons on west side
        west.setLayout(new GridLayout(9,1));
        west.add(addSedan);
        addSedan.addActionListener(this);
        west.add(addBus);
        addBus.addActionListener(this);
        west.add(addRoad);
        addRoad.addActionListener(this);

        //radio buttons on west side
        selections.add(vertical);
        selections.add(horizontal);
        west.add(vertical);
        vertical.addActionListener(this);
        horizontal.setSelected(true);
        west.add(horizontal);
        horizontal.addActionListener(this);

        selections2.add(hasLight);
        selections2.add(noLight);
        west.add(hasLight);
        hasLight.addActionListener(this);
        west.add(noLight);
        noLight.addActionListener(this);
        noLight.setSelected(true);

        west.add(label);
        west.add(length);
        length.addActionListener(this);

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
        if(source == removeRoad){
            if(Map.roads.size()>1) {
                Map.roads.remove(Map.roads.size() - 1);
                frame.repaint();
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
            sedan.setCarYPosition(sedan.getRoadCarIsOn().getRoadYPos()+5);
            for (int x = roadStart.roadXPos; x < sedan.getRoadCarIsOn().getRoadLength()*50; x = x + 30) {
                sedan.setCarXPosition(x);
                if(!sedan.collision(x, sedan)){
                    frame.repaint();
                    return;
                }

            }
        }
        if(source == addRoad){
            Boolean incorrect = true;
            int roadLength = 5;
            String orientation = "horizontal";
            String direction = "";
            int xPos = 0;
            int yPos = 270;
            Boolean lightOnRoad = false;
            if(vertical.isSelected()){
                orientation = "vertical";
            }
            else if(horizontal.isSelected()){
                orientation = "horizontal";
            }
            if(hasLight.isSelected()){
                lightOnRoad = true;
            }
            else if(noLight.isSelected()){
                lightOnRoad = false;
            }
            try {
                roadLength = Integer.parseInt(length.getText());
                incorrect = false;
            }
            catch (Exception error) {
                JOptionPane.showMessageDialog(null, "road length needs an integer");
                length.setText("5");
            }
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
            if(lightOnRoad) {
                Road road = new Road(roadLength, orientation, xPos, yPos, direction, new TrafficLight());
                Map.roads.add(road);
            }
            else{
                Road road = new Road(roadLength, orientation, xPos, yPos, direction);
                Map.roads.add(road);
            }
            frame.repaint();

        }
    }

    @Override
    public void run() {
        boolean carCollision = false;
        ArrayList<Boolean> trueCases = new ArrayList<Boolean>();
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
                        String direction = currentCar.getRoadCarIsOn().getTrafficDirection();
                        if(!currentCar.collision(currentCar.getCarXPosition() + 30, currentCar) && (direction.equals("east") || direction.equals("south"))
                                || !currentCar.collision(currentCar.getCarXPosition(), currentCar) && (direction.equals("west") || direction.equals("north"))){
                            currentCar.move();
                        }
                        else{
                            for(int z=0; z< Map.cars.size(); z++) {
                                Car otherCar = Map.cars.get(z);
                                if (otherCar.getCarYPosition() != currentCar.getCarYPosition()) {
                                    if (currentCar.getCarXPosition() + currentCar.getCarWidth() < otherCar.getCarXPosition()) {
                                        trueCases.add(true); // safe to switch lane
                                    }
                                    else {
                                        trueCases.add(false); // not safe to switch lane
                                    }
                                }
                            }
                            for (int l = 0; l < trueCases.size(); l++) {
                                if (!trueCases.get(l)) {
                                    carCollision = true;
                                    break;
                                }
                            }
                            if(!carCollision){
                                currentCar.setCarYPosition(currentCar.getRoadCarIsOn().getRoadYPos() + 30);
                            }
                            for(int m =0; m<trueCases.size(); m++){
                                trueCases.remove(m);
                            }
                            carCollision = false;
                        }
                }
                frame.repaint();

        }
    }
}
