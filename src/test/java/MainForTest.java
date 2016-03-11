import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainForTest {
    private static StringBuilder stringBuilder = new StringBuilder();
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;

    public static void main(String[] args) {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
        SerialPort serialPort = new SerialPort("COM3");
        try {
            serialPort.openPort();
            serialPort.setDTR(false);
            serialPort.setRTS(false);
            serialPort.closePort();
            serialPort.openPort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    public static void redactSensor(String sensorMentalName, String newSensorId) {
        String oldFileName = new File("src/main/resources/settings/settings.txt").getPath();
        String tmpFileName = new File("src/main/resources/settings/settings_tmp.txt").getPath();
        String[] parts;
        try {
            br = new BufferedReader(new FileReader(oldFileName));
            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(sensorMentalName)) {
                    parts = line.split("[=]", 2);
                    stringBuilder.append(parts[0]).append("=").append(newSensorId).append(",");
                    bw.write(stringBuilder.toString() + "\n");
                } else {
                    bw.write(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stringBuilder.setLength(0);
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File oldFile = new File(oldFileName);
        oldFile.delete();
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);
    }

    public static void pathCheck() {
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath);
        System.out.println("PATH CHECK");
        String oldFileName = "settings/temp_control_settings.txt";
        String base = "/settings";
        Path pathAbsolute = Paths.get(oldFileName);
        System.out.println(new File(basePath).toURI().relativize(new File(oldFileName).toURI()).getPath());
        String path = new File("src/main/resources/settings/settings.txt").getAbsolutePath();
        System.out.println(path);
    }
}
