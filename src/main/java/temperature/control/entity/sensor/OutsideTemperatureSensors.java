package temperature.control.entity.sensor;

public class OutsideTemperatureSensors {
    private Sensor leftOutsideTemperatureSensor;
    private Sensor rightOutsideTemperatureSensor;

    public OutsideTemperatureSensors() {
        leftOutsideTemperatureSensor = new Sensor();
        rightOutsideTemperatureSensor = new Sensor();
    }

    public Sensor getLeftOutsideTemperatureSensor() {
        return leftOutsideTemperatureSensor;
    }

    public void setLeftOutsideTemperatureSensor(Sensor leftOutsideTemperatureSensor) {
        this.leftOutsideTemperatureSensor = leftOutsideTemperatureSensor;
    }

    public Sensor getRightOutsideTemperatureSensor() {
        return rightOutsideTemperatureSensor;
    }

    public void setRightOutsideTemperatureSensor(Sensor rightOutsideTemperatureSensor) {
        this.rightOutsideTemperatureSensor = rightOutsideTemperatureSensor;
    }
}
