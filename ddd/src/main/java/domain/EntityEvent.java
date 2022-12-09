package domain;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:48 PM
 **/
public abstract class EntityEvent<ID> extends AggregateRootEvent<ID> {

    private final ID entityId;

    protected EntityEvent(ID entityId) {
        this.entityId = entityId;
    }

    public ID getEntityId() {
        return entityId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + entityId + ", " + "aggregateId=" + getAggregateRootId()
                + ", aggregateVersion=" + getAggregateRootVersion() + "]";
    }
}
