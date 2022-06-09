package com.pnla.onroadplus.z_version2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private  int id;
    private String email;
    private  String employee_name;
    private boolean firstLogin;

}
