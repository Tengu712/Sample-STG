package stg.app.entity.collision;

/**
 * 衝突判定ができるオブジェクト。
 */
public interface Hitable {
    boolean hit(Hitable opponent);
    Collider getCollider();
}
