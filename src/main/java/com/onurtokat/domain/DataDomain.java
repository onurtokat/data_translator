package com.onurtokat.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**DataDomain is used for Collection creation on Embedded MongoDB
 *
 * @author onurtokat
 */
@Document
public class DataDomain {

    //Fields for Collection
    @Id
    private String id;
    private List<String> details = new ArrayList<>();

    //Getter - Setters of fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return id + " " + details.toString();
    }
}
