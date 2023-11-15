package stg.app.entity.collision;

public interface Hitable {
    boolean isHit(Hitable opponent);
    Collider getCollider();
}
