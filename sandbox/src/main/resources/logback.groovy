import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import org.apache.commons.io.IOUtils

import static ch.qos.logback.classic.Level.WARN

def appProperties = loadProperties()
def logpath = appProperties.get("logpath")

//def UNDEFINED = "UNDEFINED"
//appender("logfile", RollingFileAppender) {
//  append = true
//  file = "${logpath}"
//  rollingPolicy(FixedWindowRollingPolicy) {
//    fileNamePattern = "${logpath}.%i"
//    maxIndex = "${maxfiles}"
//  }
//  triggeringPolicy(SizeBasedTriggeringPolicy) {
//    maxFileSize = "${maxfilesize}"
//  }
//  encoder(PatternLayoutEncoder) {
//    pattern = "%d{yyyy-MM-dd HH:mm:ss} %p [%c] - %m%n"
//  }
//}
//appender("stdout", ConsoleAppender) {
//  encoder(PatternLayoutEncoder) {
//    pattern = "%d %p [%c] - %m%n"
//  }
//}
//logger("org.hibernate.SQL", ${SEVERITY})

appender("stdout", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d %p [%c] - %m%n"
  }
}

root(WARN, ["stdout"])


def loadProperties(){
    InputStream appPropInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")
    try {
        def properties = new Properties()
        properties.load((InputStream)appPropInputStream)
        appPropInputStream.close()
        return properties
    }finally {
        IOUtils.closeQuietly(appPropInputStream)
    }
}