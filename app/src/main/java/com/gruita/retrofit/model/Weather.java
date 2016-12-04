

package com.gruita.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cgruita on 12/2/16.
 */

public class Weather {

    @SerializedName("id")
    private int id;

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String icon;


    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    @Override
    public String toString(){
        return "id|main|description|icon: " + id + "|" + main + "|" + description + "|" + icon;
    }

    /**
     * Created by cgruita on 12/2/16.
     */


}
