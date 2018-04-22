
package dictionarymodel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subsense {

    @SerializedName("definitions")
    @Expose
    private List<String> definitions = new ArrayList<String>();
    @SerializedName("id")
    @Expose
    private String id;

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
