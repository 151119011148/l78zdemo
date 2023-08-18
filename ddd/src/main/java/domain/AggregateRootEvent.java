package domain;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:46 PM
 **/

import lombok.Getter;

/** A base class for a domain event of an AggregateRoot */
@Getter
public abstract class AggregateRootEvent<ID> implements Event {

    private ID aggregateRootId;
    private int aggregateRootVersion;

    protected AggregateRootEvent() {}

    protected AggregateRootEvent(ID aggregateRootId) {
        this(aggregateRootId, 0);
    }

    private AggregateRootEvent(ID aggregateRootId, int aggregateRootVersion) {
        this.aggregateRootId = aggregateRootId;
        this.aggregateRootVersion = aggregateRootVersion;
    }

    void setAggregateRootId(ID aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    void setAggregateRootVersion(int aggregateRootVersion) {
        this.aggregateRootVersion = aggregateRootVersion;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + aggregateRootId + ", version=" + aggregateRootVersion + "]";
    }
}
