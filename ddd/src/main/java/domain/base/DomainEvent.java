package domain.base;

import java.util.Date;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:45 PM
 **/
public interface DomainEvent {

    String eventId = new SnowflakeIdentity<String>().next();

    String occurredOn = new Date().toString();

}
