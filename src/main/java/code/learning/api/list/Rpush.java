package code.learning.api.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import code.learning.domain.DataKeeperInterface;
import code.learning.domain.object.list.ListObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController("rpush")
@RequestMapping(path = "/list/rpush")
public class Rpush {
    @Autowired
    DataKeeperInterface dataKeeper;

    @PostMapping
    public String exec(@RequestBody Request req) {
        ListObject value = (ListObject) dataKeeper.getByKey(req.key);
        ArrayList content;
        String message;
        if (value != null) {
            value.setUpdateMode(ListObject.UpdateMode.append);
            value.update(req.array);
            content = value.getter();
            Integer len = content.size();
            message = String.format("Append list to the end of existed list, new list has length = %s", len);
            return message;
        }

        content = req.array;
        Integer len = content.size();
        message = String.format("New list created with length = %s", len);
        ListObject listObject = new ListObject(req.key, content);
        listObject.setter();
        return message;
    }

    private static class Request {
        @JsonProperty("key")
        public String key;

        @JsonProperty("value")
        public ArrayList array;

    }
}
