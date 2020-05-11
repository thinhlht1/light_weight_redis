package code.learning.domain;

import code.learning.domain.object.DataObject;

public interface DataKeeperInterface {
    DataObject getByKey(String key);
    void setByKey(String key, DataObject data);
    void removeByKey(String key);
    void updateByKey(String key, DataObject data);
}
