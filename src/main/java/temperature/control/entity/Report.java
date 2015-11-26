package temperature.control.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (temperatureLeft != report.temperatureLeft) return false;
        if (temperatureRight != report.temperatureRight) return false;
        if (imitationTemperatureLeft != report.imitationTemperatureLeft) return false;
        if (imitationTemperatureRight != report.imitationTemperatureRight) return false;
        if (outsideTemperature != report.outsideTemperature) return false;
        if (!checkingSystem.equals(report.checkingSystem)) return false;
        if (!engeenerInfo.equals(report.engeenerInfo)) return false;
        if (!date.equals(report.date)) return false;
        return directionOfMotion.equals(report.directionOfMotion);

    }

    @Override
    public int hashCode() {
        int result = (int) temperatureLeft;
        result = 31 * result + (int) temperatureRight;
        result = 31 * result + (int) imitationTemperatureLeft;
        result = 31 * result + (int) imitationTemperatureRight;
        result = 31 * result + (int) outsideTemperature;
        result = 31 * result + checkingSystem.hashCode();
        result = 31 * result + engeenerInfo.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + directionOfMotion.hashCode();
        return result;
    }
}
