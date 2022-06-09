package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAPIMAP {

    @SerializedName("hints")
    @Expose
    private Hints hints;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("paths")
    @Expose
    private List<Path> paths = null;

    public ResponseAPIMAP(Hints hints,Info info ,List<Path> paths)
    {
        this.hints=hints;
        this.info=info;
        this.paths=paths;

    }

    public Hints getHints() {
        return hints;
    }

    public void setHints(Hints hints) {
        this.hints = hints;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
