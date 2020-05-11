package code.learning.domain;

import code.learning.domain.object.DataObject;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class DataKeeper extends LinkedHashMap<String, DataObject> implements DataKeeperInterface {
    private static DataKeeper dataKeeper = new DataKeeper();
    DataKeeper(){}

    @Override
    public DataObject getByKey(String key) {
        return dataKeeper.get(key);
    }

    @Override
    public void setByKey(String key, DataObject data) {
        dataKeeper.put(key, data);
    }

    @Override
    public void removeByKey(String key) {
        dataKeeper.remove(key);
    }

    @Override
    public void updateByKey(String key, DataObject data) {
        dataKeeper.put(key, data);
    }

}
