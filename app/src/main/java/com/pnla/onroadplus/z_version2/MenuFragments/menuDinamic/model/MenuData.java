package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model;

public class MenuData {


    private int cve_object;
    private String obj_name;
    private boolean access_flag;

    /**
     * @param cve_object
     * @param obj_name
     * @param access_flag
     */

    public MenuData( int cve_object,String obj_name,boolean access_flag)
    {
        super();
        this.cve_object=cve_object;
        this.obj_name=obj_name;
        this.access_flag=access_flag;
    }



    public int getCve_object() {
        return cve_object;
    }

    public void setCve_object(int cve_object) {
        this.cve_object = cve_object;
    }

    public String getObj_name() {
        return obj_name;
    }

    public void setObj_name(String obj_name) {
        this.obj_name = obj_name;
    }

    public boolean isAccess_flag() {
        return access_flag;
    }

    public void setAccess_flag(boolean access_flag) {
        this.access_flag = access_flag;
    }
}
