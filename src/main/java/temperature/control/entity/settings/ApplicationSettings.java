package temperature.control.entity.settings;

public final class ApplicationSettings {
    private String defaultAdapterAddress;
    private String defaultAdapterPort;

    private String evenRightIronSensorAddress;
    private String evenLeftIronSensorAddress;
    private String evenControlPortAddress;

    private String unevenRightIronSensorAddress;
    private String unevenLeftIronSensorAddress;
    private String unevenControlPortAddress;

    private String temperatureLeftSensorAddress;
    private String temperatureRightSensorAddress;

    public String getDefaultAdapterAddress() {
        return defaultAdapterAddress;
    }

    public void setDefaultAdapterAddress(String defaultAdapterAddress) {
        this.defaultAdapterAddress = defaultAdapterAddress;
    }

    public String getDefaultAdapterPort() {
        return defaultAdapterPort;
    }

    public void setDefaultAdapterPort(String defaultAdapterPort) {
        this.defaultAdapterPort = defaultAdapterPort;
    }

    public String getEvenRightIronSensorAddress() {
        return evenRightIronSensorAddress;
    }

    public void setEvenRightIronSensorAddress(String evenRightIronSensorAddress) {
        this.evenRightIronSensorAddress = evenRightIronSensorAddress;
    }

    public String getEvenLeftIronSensorAddress() {
        return evenLeftIronSensorAddress;
    }

    public void setEvenLeftIronSensorAddress(String evenLeftIronSensorAddress) {
        this.evenLeftIronSensorAddress = evenLeftIronSensorAddress;
    }

    public String getEvenControlPortAddress() {
        return evenControlPortAddress;
    }

    public void setEvenControlPortAddress(String evenControlPortAddress) {
        this.evenControlPortAddress = evenControlPortAddress;
    }

    public String getUnevenRightIronSensorAddress() {
        return unevenRightIronSensorAddress;
    }

    public void setUnevenRightIronSensorAddress(String unevenRightIronSensorAddress) {
        this.unevenRightIronSensorAddress = unevenRightIronSensorAddress;
    }

    public String getUnevenLeftIronSensorAddress() {
        return unevenLeftIronSensorAddress;
    }

    public void setUnevenLeftIronSensorAddress(String unevenLeftIronSensorAddress) {
        this.unevenLeftIronSensorAddress = unevenLeftIronSensorAddress;
    }

    public String getUnevenControlPortAddress() {
        return unevenControlPortAddress;
    }

    public void setUnevenControlPortAddress(String unevenControlPortAddress) {
        this.unevenControlPortAddress = unevenControlPortAddress;
    }

    public String getTemperatureLeftSensorAddress() {
        return temperatureLeftSensorAddress;
    }

    public void setTemperatureLeftSensorAddress(String temperatureLeftSensorAddress) {
        this.temperatureLeftSensorAddress = temperatureLeftSensorAddress;
    }

    public String getTemperatureRightSensorAddress() {
        return temperatureRightSensorAddress;
    }

    public void setTemperatureRightSensorAddress(String temperatureRightSensorAddress) {
        this.temperatureRightSensorAddress = temperatureRightSensorAddress;
    }

    @Override
    public String toString() {
        return "ApplicationSettings{" +
                "defaultAdapterAddress='" + defaultAdapterAddress + '\'' +
                ", defaultAdapterPort='" + defaultAdapterPort + '\'' +
                ", evenRightIronSensorAddress='" + evenRightIronSensorAddress + '\'' +
                ", evenLeftIronSensorAddress='" + evenLeftIronSensorAddress + '\'' +
                ", evenControlPortAddress='" + evenControlPortAddress + '\'' +
                ", unevenRightIronSensorAddress='" + unevenRightIronSensorAddress + '\'' +
                ", unevenLeftIronSensorAddress='" + unevenLeftIronSensorAddress + '\'' +
                ", unevenControlPortAddress='" + unevenControlPortAddress + '\'' +
                ", temperatureLeftSensorAddress='" + temperatureLeftSensorAddress + '\'' +
                ", temperatureRightSensorAddress='" + temperatureRightSensorAddress + '\'' +
                '}';
    }
}
