package temperature.control.entity;

import jssc.SerialPort;

public class UnevenMovement {
    private volatile Sensor leftIronSensor;
    private volatile Sensor rightIronSensor;
    private volatile SerialPort unevenControlPort;

    public UnevenMovement() {
    }

    public Sensor getLeftIronSensor() {
        return leftIronSensor;
    }

    public void setLeftIronSensor(Sensor leftIronSensor) {
        this.leftIronSensor = leftIronSensor;
    }

    public Sensor getRightIronSensor() {
        return rightIronSensor;
    }

    public void setRightIronSensor(Sensor rightIronSensor) {
        this.rightIronSensor = rightIronSensor;
    }

    public SerialPort getUnevenControlPort() {
        return unevenControlPort;
    }

    public void setUnevenControlPort(SerialPort unevenControlPort) {
        this.unevenControlPort = unevenControlPort;
    }
}
