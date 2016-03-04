package temperature.control.entity.settings;

import java.util.Date;


public class Report {
    private byte temperatureLeft;
    private byte temperatureRight;
    private byte imitationTemperatureLeft;
    private byte imitationTemperatureRight;
    private byte outsideTemperature;

    private String checkingSystem;

    private String engeenerInfo;

    private Date date = new Date();

    private String directionOfMotion;

    public String getDirectionOfMotion() {
        return directionOfMotion;
    }

    public void setDirectionOfMotion(String directionOfMotion) {
        this.directionOfMotion = directionOfMotion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte getTemperatureLeft() {
        return temperatureLeft;
    }

    public void setTemperatureLeft(byte temperatureLeft) {
        this.temperatureLeft = temperatureLeft;
    }

    public byte getTemperatureRight() {
        return temperatureRight;
    }

    public void setTemperatureRight(byte temperatureRight) {
        this.temperatureRight = temperatureRight;
    }

    public byte getImitationTemperatureLeft() {
        return imitationTemperatureLeft;
    }

    public void setImitationTemperatureLeft(byte imitationTemperatureLeft) {
        this.imitationTemperatureLeft = imitationTemperatureLeft;
    }

    public byte getImitationTemperatureRight() {
        return imitationTemperatureRight;
    }

    public void setImitationTemperatureRight(byte imitationTemperatureRight) {
        this.imitationTemperatureRight = imitationTemperatureRight;
    }

    public byte getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(byte outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public String getCheckingSystem() {
        return checkingSystem;
    }

    public void setCheckingSystem(String checkingSystem) {
        this.checkingSystem = checkingSystem;
    }

    public String getEngeenerInfo() {
        return engeenerInfo;
    }

    public void setEngeenerInfo(String engeenerInfo) {
        this.engeenerInfo = engeenerInfo;
    }

    @Override
    public String toString() {
        return "Report{" +
                "temperatureLeft=" + temperatureLeft +
                ", temperatureRight=" + temperatureRight +
                ", imitationTemperatureLeft=" + imitationTemperatureLeft +
                ", imitationTemperatureRight=" + imitationTemperatureRight +
                ", outsideTemperature=" + outsideTemperature +
                ", checkingSystem='" + checkingSystem + '\'' +
                ", engeenerInfo='" + engeenerInfo + '\'' +
                ", date=" + date +
                ", directionOfMotion='" + directionOfMotion + '\'' +
                '}';
    }
}
