package code.learning.domain.object.list;

import code.learning.domain.object.DataObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListObject extends DataObject<ArrayList> {
    private UpdateMode updateMode;
    public enum UpdateMode {
        remove, append
    }

    public void setUpdateMode(UpdateMode updateMode) {
        this.updateMode = updateMode;
    }

    private String popDirection;
    public void setPopDirection(String direction) {
        this.popDirection = direction;
    }
    public enum PopDirection {
        left, right
    }
    private Object removeValue;
    public Object getRemoveValue() {
        return removeValue;
    }

    public ListObject(String key, ArrayList data) {
        super(key, data);
    }

    @Override
    public void update(ArrayList newData) {
        if (UpdateMode.remove.equals(updateMode)) {
            int index = 0;
            if (PopDirection.right.toString().equals(popDirection)) {
                index = value.size() - 1;
            }

            this.removeValue = value.get(index);
            value.remove(index);
            if (value.isEmpty()) {
                this.remove(key);
            }

            dataKeeper.updateByKey(key, this);
        } else if (UpdateMode.append.equals(updateMode)) {
            newData.stream().map(e -> value.add(e)).collect(Collectors.toList());
            this.setter();
        }
    }

    @Override
    protected ArrayList to() {
        return this.value;
    }

    @Override
    protected <ArrayList> ArrayList transform(ArrayList data) {
        return data;
    }
}
