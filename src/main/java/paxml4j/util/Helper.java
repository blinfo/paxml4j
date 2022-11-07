package paxml4j.util;

import java.time.*;
import java.time.temporal.Temporal;
import java.util.Optional;
import java.util.regex.Pattern;
import xmlight.XmlNode;

/**
 *
 * @author hakan
 */
public class Helper {

    private static final String DATE = "\\d{4}-\\d{2}-\\d{2}",
            LOCAL_TIME = "\\d{2}\\:\\d{2}(\\:\\d{2}(\\.\\d+)?)?",
            OFFSET_TIME = LOCAL_TIME + "[-+]\\d{2}\\:\\d{2}";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE),
            LOCAL_TIME_PATTERN = Pattern.compile(LOCAL_TIME),
            OFFSET_TIME_PATTERN = Pattern.compile(OFFSET_TIME),
            LOCAL_DATE_TIME_PATTERN = Pattern.compile(DATE + "T" + LOCAL_TIME),
            OFFSET_DATE_TIME_PATTERN = Pattern.compile(DATE + "T" + OFFSET_TIME),
            ZONED_DATE_TIME_PATTERN = Pattern.compile(DATE + "T" + OFFSET_TIME + "\\[[a-zA-Z]+\\/[a-zA-Z]+\\]");

    private Helper() {
    }

    public static Optional<String> attrText(XmlNode parent, String attributeName) {
        return Optional.ofNullable(parent.getAttribute(attributeName));
    }

    public static Optional<String> nodeText(XmlNode parent, String nodeName) {
        return Optional.ofNullable(parent.hasChildNamed(nodeName) ? parent.getChild(nodeName).getText() : null);
    }

    public static Optional<XmlNode> childNode(XmlNode parent, String nodeName) {
        return Optional.ofNullable(parent.hasChildNamed(nodeName) ? parent.getChild(nodeName) : null);
    }

    public static Temporal temporalFromText(String text) {
        if (DATE_PATTERN.matcher(text).matches()) {
            return LocalDate.parse(text);
        }
        if (LOCAL_TIME_PATTERN.matcher(text).matches()) {
            return LocalTime.parse(text);
        }
        if (LOCAL_DATE_TIME_PATTERN.matcher(text).matches()) {
            return LocalDateTime.parse(text);
        }
        if (OFFSET_TIME_PATTERN.matcher(text).matches()) {
            return OffsetTime.parse(text);
        }
        if (OFFSET_DATE_TIME_PATTERN.matcher(text).matches()) {
            return OffsetDateTime.parse(text);
        }
        if (ZONED_DATE_TIME_PATTERN.matcher(text).matches()) {
            return ZonedDateTime.parse(text);
        }
        return null;
    }
}
