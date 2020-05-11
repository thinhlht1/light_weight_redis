package code.learning.domain.object;

import code.learning.domain.DataKeeper;
import code.learning.domain.DataKeeperInterface;
import code.learning.domain.SpringContainer;

public abstract class DataObject<T> {
    protected final String key;
    protected T value;
    protected DataKeeperInterface dataKeeper;
    public DataObject(String key, T data) {
        this.key = key;
        this.value = transform(data);
        this.dataKeeper = SpringContainer.ctx().getBean(DataKeeper.class);
    }

    public <T> T getter() {
        return (T) this.to();
    }

    public void setter() {
        dataKeeper.setByKey(this.key, this);
    }

    public void remove(String key) {
        dataKeeper.removeByKey(key);
    }

    public abstract void update(T newData) throws Exception;

    protected abstract T to();
    protected abstract <T> T transform(T data);
}
