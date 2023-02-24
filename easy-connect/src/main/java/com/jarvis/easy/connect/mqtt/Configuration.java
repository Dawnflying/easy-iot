package com.jarvis.easy.connect.mqtt;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ClientAuth;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TrustOptions;
import lombok.Data;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lixiaofei
 */
@Data
public class Configuration {

    /**
     * 最大消息大小
     */
    private int maxMessageSize;
    /**
     * 是否是自动生成clientid
     */
    private boolean isAutoClientId;
    /**
     * 连接超时时间
     */
    private int timeoutOnConnect;

    private int port;
    private String host;
    private int acceptBacklog;
    private ClientAuth clientAuth;
    private boolean sni;
    private boolean tcpNoDelay;
    private boolean tcpKeepAlive;
    private int soLinger;
    private boolean usePooledBuffers;
    private int idleTimeout;
    private TimeUnit idleTimeoutUnit;
    private boolean ssl;
    private long sslHandshakeTimeout;
    private TimeUnit sslHandshakeTimeoutUnit;
    private KeyCertOptions keyCertOptions;
    private TrustOptions trustOptions;
    private Set<String> enabledCipherSuites;
    private ArrayList<String> crlPaths;
    private ArrayList<Buffer> crlValues;
    private boolean useAlpn;
    private SSLEngineOptions sslEngineOptions;
    private Set<String> enabledSecureTransportProtocols;
    private boolean tcpFastOpen;
    private boolean tcpCork;
    private boolean tcpQuickAck;

    private int sendBufferSize;
    private int receiveBufferSize;
    private int trafficClass;
    private boolean reuseAddress;
    private boolean logActivity;
    private boolean reusePort;

}
