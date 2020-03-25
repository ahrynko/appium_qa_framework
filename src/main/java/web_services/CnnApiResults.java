package web_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import page_object.model.ArticleDTO;
import java.util.List;

public class CnnApiResults {

    @JsonProperty("result")  // with JSON - REST
    private List<ArticleDTO> results;

    public List<ArticleDTO> getResults() {
        return results;
    }
}
