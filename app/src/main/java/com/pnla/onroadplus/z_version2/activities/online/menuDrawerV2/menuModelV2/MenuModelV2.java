package com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2;

public class MenuModelV2 {

    private int imageIcon;
    private String menuName;
    private boolean hasChildren;
    private boolean isGroup;
    private int cveGroup;

    public MenuModelV2(int imageIcon, String menuName, boolean hasChildren, boolean isGroup, int cveGroup) {
        this.imageIcon = imageIcon;
        this.menuName = menuName;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
        this.cveGroup = cveGroup;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getCveGroup() {
        return cveGroup;
    }

    public void setCveGroup(int cveGroup) {
        this.cveGroup = cveGroup;
    }

}
