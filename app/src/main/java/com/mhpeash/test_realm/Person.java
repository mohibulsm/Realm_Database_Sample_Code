package com.mhpeash.test_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Person extends RealmObject {
    @PrimaryKey
    private int PersonID;

    private String Name;

    private  String Mobile;

    private  String Salary;


    public int getPersonID() { return PersonID; }
    public String getName() { return Name; }
    public String getMobile() { return Mobile; }
    public String getSalary() { return Salary; }


    public void setPersonID(int personID) { PersonID = personID; }
    public void setName(String name) { Name = name; }
    public void setMobile(String mobile) { Mobile = mobile; }
    public void setSalary(String salary) { Salary = salary; }
}
