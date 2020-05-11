package code.learning.domain.object.string;

import code.learning.domain.object.DataObject;

public class StringObject extends DataObject<String> {
    public StringObject(String key, String data) {
        super(key, data);
    }

    @Override
    public void update(String newData) {

    }

    @Override
    public String to() {
        return this.value;
    }

    @Override
    protected <String> String transform(String data) {
        return data;
    }
}
