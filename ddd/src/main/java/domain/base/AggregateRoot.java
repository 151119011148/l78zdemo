package domain.base;

import lombok.Getter;
import lombok.Setter;

import javax.management.ReflectionException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/20/22 5:44 PM
 **/
@Getter
public class AggregateRoot<ID> {

    @Setter
    private ID id;

    private int version;

    private final HashSet<Entity<ID>> entities;

    private final List<DomainEvent> pendingChanges;

    public AggregateRoot() {
        this.entities = new HashSet<>();
        this.pendingChanges = new ArrayList<>();
        this.version = 0;
    }

    public Stream<DomainEvent> getPendingChanges() {
        return pendingChanges.stream();
    }

    public void loadFromHistory(Stream<DomainEvent> events) {
        events.forEach(domainEvent -> publishEvent(domainEvent, false));
    }

    public void markPendingChangesAsCommitted() {
        pendingChanges.clear();
    }

    public void publishEvent(AggregateRootDomainEvent<ID> change) {
        if (change.getAggregateRootId() == null) {
            change.setAggregateRootId(id);
        }
        change.setAggregateRootVersion(version + 1);
        publishEvent(change, true);
    }

    private void publishEvent(DomainEvent change, boolean isNew) {
        if (change == null) {
            return;
        }
        applyEvent(change);
        if (isNew) {
            pendingChanges.add(change);
        }
        ++version;
    }

    private void applyEvent(DomainEvent domainEvent) {
        try {
            Method method = this.getClass().getDeclaredMethod("on", domainEvent.getClass());
            if (method != null) {
                method.setAccessible(true);
                method.invoke(this, domainEvent);
            } else {
                throw new ReflectionException(new NullPointerException("method 'on' not found for domainevent: " + domainEvent.getClass().getName()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean entityExists(ID id) {
        return getEntities().anyMatch(x -> x.identity().equals(id));
    }

    public <TEntity extends Entity<ID>> void addEntity(TEntity entity){
        addEntity(entity, true);
    }

    public <TEntity extends Entity<ID>> void addEntity(TEntity entity, boolean throwIfExists) throws IllegalArgumentException {
        Objects.requireNonNull(entity, "entityId must not be null");
        TEntity existing = findEntityById(entity.identity());
        if (existing != null) {
            if (throwIfExists) {
                throw new IllegalArgumentException("entity has already been added: " + entity.identity());
            } else {
                return;
            }
        }
        entities.add(entity);
    }

    public <TEntity extends Entity<ID>> void removeEntity(TEntity entity) {
        Objects.requireNonNull(entity, "entity must not be null");
        removeEntity(entity.identity(), false);
    }

    public void removeEntity(ID entityId, boolean throwIfNotExists) {
        Objects.requireNonNull(entityId, "entityId must not be null");
        Entity<ID> entity = findEntityById(entityId);
        if (entity != null) {
            entities.remove(entity);
        } else if (throwIfNotExists) {
            throw new IllegalArgumentException("entity has not been found: " + entityId);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity<ID>> T findEntityById(ID id) {
        Entity<ID> entity = getEntities().filter(x -> x.identity().equals(id)).findFirst().orElse(null);
        return entity != null ? (T) entity : null;
    }

    public Stream<Entity<ID>> getEntities() {
        return entities.stream();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        AggregateRoot<ID> that = (AggregateRoot<ID>) obj;
        return Objects.equals(this.id, that.id) && Objects.equals(this.version, that.version);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + version;
        return result;
    }
}
