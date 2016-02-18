package temperature.control.entity;


public class TemperatureOption {

    private String leftTemperature;
    private String rightTemperature;
    private String systemOption;
    private String directionOfMotion;
    private boolean warmingOn = false;

    public TemperatureOption() {
    }

    public String getLeftTemperature() {
        return leftTemperature;
    }

    public void setLeftTemperature(String leftTemperature) {
        this.leftTemperature = leftTemperature;
    }

    public String getRightTemperature() {
        return rightTemperature;
    }

    public void setRightTemperature(String rightTemperature) {
        this.rightTemperature = rightTemperature;
    }

    public String getSystemOption() {
        return systemOption;
    }

    public void setSystemOption(String systemOption) {
        this.systemOption = systemOption;
    }

    public String getDirectionOfMotion() {
        return directionOfMotion;
    }

    public void setDirectionOfMotion(String directionOfMotion) {
        this.directionOfMotion = directionOfMotion;
    }

    public boolean isWarmingOn() {
        return warmingOn;
    }

    public void setWarmingOn(boolean warmingOn) {
        this.warmingOn = warmingOn;
    }

    @Override
    public String toString() {
        return "Option{" +
                "leftTemperature=" + leftTemperature +
                ", rightTemperature=" + rightTemperature +
                ", systemOption='" + systemOption + '\'' +
                ", directionOfMotion='" + directionOfMotion + '\'' +
                ", warmingOn=" + warmingOn +
                '}';
    }
}
