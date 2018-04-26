
package dictionarymodel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sense {

    @SerializedName("definitions")
    @Expose
    private List<String> definitions = new ArrayList<String>();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subsenses")
    @Expose
    private List<Subsense> subsenses = new ArrayList<Subsense>();

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

    public List<Subsense> getSubsenses() {
        return subsenses;
    }

    public void setSubsenses(List<Subsense> subsenses) {
        this.subsenses = subsenses;
    }

}
