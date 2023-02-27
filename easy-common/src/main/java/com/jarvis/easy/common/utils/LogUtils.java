package com.jarvis.easy.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    public static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger("default");

    public static final Logger CONTROLLER_LOGGER = LoggerFactory.getLogger("controller");

    public static final Logger ERROR_LOGGER = LoggerFactory.getLogger("error");

    public static final Logger TS_LOGGER = LoggerFactory.getLogger("ts");

    public static final Logger MQ_LOGGER = LoggerFactory.getLogger("mq");

}
