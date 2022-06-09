package com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget;

public class CommandV2 {

    private int cve_routine;
    private int cve_device;
    private String routine;
    private String desc_routine;
    private String imei;
    private boolean isSelected;



    /**
     * @param cve_routine
     * @param cve_device
     * @param routine
     * @param desc_routine
     * @param imei
     */
    public CommandV2(int cve_routine, int cve_device, String routine, String desc_routine,String imei, boolean isSelected) {
        super();
        this.cve_routine = cve_routine;
        this.cve_device = cve_device;
        this.routine = routine;
        this.desc_routine = desc_routine;
        this.imei=imei;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCveRoutine() {
        return cve_routine;
    }

    public void setCveRoutine(int cve_routine) {
        this.cve_routine = cve_routine;
    }

    public int getCveDevice() {
        return cve_device;
    }

    public void setCveDevice(int cve_device) {
        this.cve_device = cve_device;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public String getDescRoutine() {
        return desc_routine;
    }

    public void setDescRoutine(String desc_routine) {
        this.desc_routine = desc_routine;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}
