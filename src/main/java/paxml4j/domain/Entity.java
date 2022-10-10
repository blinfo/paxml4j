package paxml4j.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.math.RoundingMode;

/**
 *
 * @author hakan
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public interface Entity {
    
    static final Integer SCALE = 2;
    static final RoundingMode ROUND = RoundingMode.HALF_UP;
    
}
