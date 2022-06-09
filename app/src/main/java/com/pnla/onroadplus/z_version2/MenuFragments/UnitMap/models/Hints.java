package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hints {
    @SerializedName("visited_nodes.sum")
    @Expose
    private Integer visitedNodesSum;
    @SerializedName("visited_nodes.average")
    @Expose
    private Float visitedNodesAverage;


    public Hints(Integer visitedNodesSum,Float visitedNodesAverage)
    {
        this.visitedNodesSum=visitedNodesSum;
        this.visitedNodesAverage=visitedNodesAverage;

    }

    public Integer getVisitedNodesSum() {
        return visitedNodesSum;
    }

    public void setVisitedNodesSum(Integer visitedNodesSum) {
        this.visitedNodesSum = visitedNodesSum;
    }

    public Float getVisitedNodesAverage() {
        return visitedNodesAverage;
    }

    public void setVisitedNodesAverage(Float visitedNodesAverage) {
        this.visitedNodesAverage = visitedNodesAverage;
    }

}
