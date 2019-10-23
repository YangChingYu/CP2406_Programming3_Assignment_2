import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    public void testMove(){
        Road road = new Road(8);
        Car car = new Car(road, "hum v");
        car.move();
    }

}