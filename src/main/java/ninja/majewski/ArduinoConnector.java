package ninja.majewski;

import java.util.Arrays;

public class ArduinoConnector {

    public static void main(String[] args) {
        SerialPortAdapter serialPort = new SerialPortAdapter();
        serialPort.start("COM5");

        try {
            while (true) {
                while (!serialPort.bytesAvailable()) {
                    serialPort.writeRandomMessage();

                    Thread.sleep(1000);
                }

                byte[] readBuffer = serialPort.readBytes();
                String response = new String(readBuffer);
                System.out.printf("Read: [%s]. In %s bytes: %s]\n", response, readBuffer.length, Arrays.toString(readBuffer));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
