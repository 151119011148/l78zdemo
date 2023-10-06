package domain;

import lombok.Getter;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:48 PM
 **/
@Getter
public abstract class EntityEvent<ID> extends AggregateRootEvent<ID> {

    private final ID entityId;

    protected EntityEvent(ID entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + entityId + ", " + "aggregateId=" + getAggregateRootId()
                + ", aggregateVersion=" + getAggregateRootVersion() + "]";
    }
}
