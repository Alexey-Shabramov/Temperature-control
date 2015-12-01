package temperature.control.entity;


public class Option {

    private static Option optionInstance;

    private String leftTemperature;
    private String rightTemperature;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (warmingOn != option.warmingOn) return false;
        if (!leftTemperature.equals(option.leftTemperature)) return false;
        if (!rightTemperature.equals(option.rightTemperature)) return false;
        if (!systemOption.equals(option.systemOption)) return false;
        return directionOfMotion.equals(option.directionOfMotion);

    }

    @Override
    public int hashCode() {
        int result = leftTemperature.hashCode();
        result = 31 * result + rightTemperature.hashCode();
        result = 31 * result + systemOption.hashCode();
        result = 31 * result + directionOfMotion.hashCode();
        result = 31 * result + (warmingOn ? 1 : 0);
        return result;
    }
}
