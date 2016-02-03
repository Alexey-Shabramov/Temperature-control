package temperature.control.test;


import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class MainForTest {
    public static void main(String[] args) {
        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        SerialPort serialPort = new SerialPort("COM4");
        try {
            serialPort.openPort();
            while (serialPort.isOpened()){
                serialPort.setDTR(true);
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
