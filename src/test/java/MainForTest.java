import jssc.SerialPortList;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class MainForTest {
    private static StringBuilder stringBuilder = new StringBuilder();
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;

    public static void main(String[] args) {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
    }
}
