package temperature.control.entity;


public class Option {

    private static Option optionInstance;

    private byte leftTemperature;
    private byte rightTemperature;
    private String systemOption;
    private String directionOfMotion;
    private boolean warmingOn = false;

    private Option(){
    }

    public static Option getOptionInstance(){
        if(optionInstance == null){
            optionInstance = new Option();
        }
        return optionInstance;
    }

    public byte getLeftTemperature() {
        return leftTemperature;
    }

    public void setLeftTemperature(byte leftTemperature) {
        this.leftTemperature = leftTemperature;
    }

    public byte getRightTemperature() {
        return rightTemperature;
    }

    public void setRightTemperature(byte rightTemperature) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (leftTemperature != option.leftTemperature) return false;
        if (rightTemperature != option.rightTemperature) return false;
        if (warmingOn != option.warmingOn) return false;
        if (!systemOption.equals(option.systemOption)) return false;
        return directionOfMotion.equals(option.directionOfMotion);
    }

    @Override
    public int hashCode() {
        int result = (int) leftTemperature;
        result = 31 * result + (int) rightTemperature;
        result = 31 * result + systemOption.hashCode();
        result = 31 * result + directionOfMotion.hashCode();
        result = 31 * result + (warmingOn ? 1 : 0);
        return result;
    }
}
