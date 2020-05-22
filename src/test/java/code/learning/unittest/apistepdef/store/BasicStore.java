package code.learning.unittest.apistepdef.store;

import java.util.HashMap;
import java.util.Map;

public class BasicStore<T> {
    private final Map<String, T> store = new HashMap<>();

    public void register(String id, T obj) {
        store.put(id, obj);
    }

    public T get(String id) {
        return store.get(id);
    }

    public void cleanup() {
        store.clear();
    }
}
