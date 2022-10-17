package paxml4j.domain;

import com.fasterxml.jackson.annotation.*;
import java.math.RoundingMode;

/**
 *
 * @author hakan
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Entity {

    static final Integer SCALE = 2;
    static final RoundingMode ROUND = RoundingMode.HALF_UP;

}
