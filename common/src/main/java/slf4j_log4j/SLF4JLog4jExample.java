package slf4j_log4j;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JLog4jExample
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SLF4JLog4jExample.class);
    
    public static void main(String[] args)
    {
        PropertyConfigurator.configure("config/log4j.properties"); // 指定log4j的配置文件地址
        LOGGER.info("Hello World");
        LOGGER.info("some {}", 3);
    }

}
