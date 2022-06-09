package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

public class Locations {
    private String direccion;
    private String DateTime;

    /**
     * *
     * @param direccion
     * @param DateTime
     */

    public Locations(String direccion,String DateTime)
    {
        super();
        this.direccion=direccion;
        this.DateTime=DateTime;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }


}
