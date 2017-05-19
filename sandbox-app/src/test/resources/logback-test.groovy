import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.WARN

appender("stdout", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d %p [%c] - %m%n"
  }
}

logger("br.com.petrobras", WARN)
logger("org.hibernate.SQL", WARN)
logger("org.hibernate.type", WARN)
logger("org.hibernate.tool.hbm2ddl", DEBUG)
logger("org.springframework.web", WARN)
root(WARN, ["stdout"])
