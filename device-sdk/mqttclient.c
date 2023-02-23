import java.io.*;
import java.net.*;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TcpToMqttBridge implements MqttCallback {
    private MqttClient mqttClient;

    public TcpToMqttBridge(String brokerUrl, String clientId) throws MqttException {
        mqttClient = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
        mqttClient.setCallback(this);
        mqttClient.connect();
    }

    public void start(String tcpHost, int tcpPort, String mqttTopic) throws IOException {
        Socket socket = new Socket(tcpHost, tcpPort);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while ((line = in.readLine()) != null) {
            byte[] payload = line.getBytes();
            MqttMessage message = new MqttMessage(payload);
            message.setQos(0);
            try {
                mqttClient.publish(mqttTopic, message);
            } catch (MqttException e) {
                System.out.println("Error sending message: " + e.getMessage());
            }
        }

        socket.close();
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost: " + cause.getMessage());
        System.exit(1);
    }

    public void messageArrived(String topic, MqttMessage message) {
        // do nothing
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        // do nothing
    }

    public static void main(String[] args) throws Exception {
        String brokerUrl = "tcp://localhost:1883";
        String clientId = "TcpToMqttBridge";
        String tcpHost = "localhost";
        int tcpPort = 12345;
        String mqttTopic = "my/topic";

        TcpToMqttBridge bridge = new TcpToMqttBridge(brokerUrl, clientId);
        bridge.start(tcpHost, tcpPort, mqttTopic);
    }
}
