package ninja.majewski;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Arrays;
import java.util.UUID;

public class SerialPortAdapter {

    SerialPort serialPort;

    public void start(String port) {
        serialPort = SerialPort.getCommPort(port);

        serialPort.setBaudRate(9600);
        serialPort.setNumDataBits(8);
        serialPort.setParity(0);
        serialPort.setNumStopBits(1);
        serialPort.setFlowControl(0);
        serialPort.setRTS();

        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
        serialPort.openPort();

        System.out.printf("Opened serial port: %s\n", port);
    }

    public boolean bytesAvailable() {
        return serialPort.bytesAvailable() > 0;
    }

    public byte[] readBytes() {
        byte[] readBuffer = new byte[serialPort.bytesAvailable()];
        int readBytes = serialPort.readBytes(readBuffer, readBuffer.length);
        return Arrays.copyOf(readBuffer, readBytes);
    }

    public void write(byte[] bytes, int length) {
        System.out.printf("Writing bytes: %s\n", Arrays.toString(bytes));
        serialPort.writeBytes(bytes, length);
    }

    public void write(String message) {
        System.out.printf("Writing message: %s\n", message);
        serialPort.writeBytes(message.getBytes(), message.length());
    }

    public void writeRandomMessage() {
        String randomMessage = UUID.randomUUID().toString().substring(0, 8);
        write(randomMessage);
    }
}
