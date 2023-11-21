package stg.app.entity;

/**
 * 更新可能なオブジェクト。エンティティ。
 */
public interface Updatable {
    /**
     * エンティティの更新。
     * 
     * @return エンティティが生きていればtrue、消えるべきであればfalseを返す。
     */
    public boolean update();
    public double getX();
    public double getY();
}
