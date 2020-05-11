package code.learning.domain.object.set;

import code.learning.domain.object.DataObject;
import code.learning.exceptions.UserException;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SetObject extends DataObject<Set> {
    private UpdateMode updateMode;
    public enum UpdateMode {
        remove, append
    }

    public void setUpdateMode(UpdateMode updateMode) {
        this.updateMode = updateMode;
    }

    public SetObject(String key, Set data) {
        super(key, data);
    }

    @Override
    public void update(Set newData) throws Exception{
        if (UpdateMode.remove.equals(updateMode)) {
            Object invalidElement = newData.stream()
                    .filter(e -> !value.contains(e)).findFirst().orElse(null);

            if (invalidElement != null) {
                throw new UserException("badUserInput", "Bad values", HttpStatus.BAD_REQUEST);
            }

            newData.stream().map(e -> value.remove(e)).collect(Collectors.toSet());
            dataKeeper.updateByKey(key, this);
        } else if (UpdateMode.append.equals(updateMode)) {
            newData.stream().map(e -> value.add(e)).collect(Collectors.toSet());
            this.setter();
        }
    }

    @Override
    protected Set to() {
        return this.value;
    }

    @Override
    protected <Set> Set transform(Set data) {
        return data;
    }
}

