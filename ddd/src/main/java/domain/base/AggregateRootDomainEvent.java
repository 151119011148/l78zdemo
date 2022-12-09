package domain.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:46 PM
 **/
@Getter
@Setter
public class AggregateRootDomainEvent<ID> implements DomainEvent {

    private ID aggregateRootId;

    private int aggregateRootVersion;

    public AggregateRootDomainEvent(ID aggregateRootId) {
        this(aggregateRootId, 0);
    }

    private AggregateRootDomainEvent(ID aggregateRootId, int aggregateRootVersion) {
        this.aggregateRootId = aggregateRootId;
        this.aggregateRootVersion = aggregateRootVersion;
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + aggregateRootId + ", version=" + aggregateRootVersion + "]";
    }
}
