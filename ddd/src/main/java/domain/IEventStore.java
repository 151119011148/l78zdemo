package domain;

import java.util.stream.Stream;

/**
 * @Description: TODO
 * @Author gaoying
 * @Date 3/20/22 5:50 PM
 **/
public interface IEventStore<ID> {

    void appendEventsToStream(ID streamId, Stream<Event> events);
    void appendEventsToStream(ID streamId, Stream<Event> events, long expectedLastPosition);

    Stream<Event> getStream(ID streamId);
}
