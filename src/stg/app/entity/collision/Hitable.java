package stg.app.entity.collision;

/**
 * 衝突判定ができるオブジェクト。
 */
public interface Hitable {
    boolean isHit(Hitable opponent);
    Collider getCollider();
}
