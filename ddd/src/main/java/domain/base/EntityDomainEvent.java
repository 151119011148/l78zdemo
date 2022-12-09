package domain.base;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:48 PM
 **/
public class EntityDomainEvent<ID> extends AggregateRootDomainEvent<ID> {


    private final ID entityId;

    public EntityDomainEvent(ID entityId) {
        super(entityId);
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
