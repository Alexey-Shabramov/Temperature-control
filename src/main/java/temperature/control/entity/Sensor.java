package temperature.control.entity;

public class Sensor {
    private String sensorName;
    private boolean sensorOn;
    private String sensorId;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public boolean isSensorOn() {
        return sensorOn;
    }

    public void setSensorOn(boolean sensorOn) {
        this.sensorOn = sensorOn;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorName='" + sensorName + '\'' +
                ", sensorOn=" + sensorOn +
                ", sensorId='" + sensorId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (sensorOn != sensor.sensorOn) return false;
        if (!sensorName.equals(sensor.sensorName)) return false;
        return sensorId.equals(sensor.sensorId);

    }

    @Override
    public int hashCode() {
        int result = sensorName.hashCode();
        result = 31 * result + (sensorOn ? 1 : 0);
        result = 31 * result + sensorId.hashCode();
        return result;
    }
}
