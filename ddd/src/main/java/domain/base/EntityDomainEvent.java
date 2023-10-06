package domain.base;

import lombok.Getter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:48 PM
 **/
@Getter
public class EntityDomainEvent<ID> extends AggregateRootDomainEvent<ID> {


    private final ID entityId;

    public EntityDomainEvent(ID entityId) {
        super(entityId);
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + entityId + ", " + "aggregateId=" + getAggregateRootId()
                + ", aggregateVersion=" + getAggregateRootVersion() + "]";
    }
}
