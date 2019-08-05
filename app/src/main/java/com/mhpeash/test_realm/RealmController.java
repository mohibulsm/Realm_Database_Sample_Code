package com.mhpeash.test_realm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmController {
    Realm realm;
    Number personID = 0;

    public RealmController(Realm rl) {
        this.realm = rl;
    }

    public boolean SavePerson(String name, String mobile, String salary) {

        personID = realm.where(Person.class).max("PersonID");
        int nextId = (personID == null) ? 1 : personID.intValue() + 1;
        Log.e("nextId", " nextId " + nextId);

        try {
            Person person = new Person();
            person.setPersonID(nextId);
            person.setName(name);
            person.setMobile(mobile);
            person.setSalary(salary);
            realm.beginTransaction();
            realm.copyToRealm(person);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            Log.e("Error", "" + e.getMessage());
            return false;
        }
    }

    public List<Person> getAllPerson() {
        List<Person> resultdata = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Person> results = realm.where(Person.class).findAll();
        results.sort("PersonID", Sort.DESCENDING);
        for (int i = 0; i < results.size(); i++) {
            Person person = new Person();
            person.setPersonID(results.get(i).getPersonID());
            person.setName(results.get(i).getName());
            person.setMobile(results.get(i).getMobile());
            person.setSalary(results.get(i).getSalary());
            resultdata.add(person);
        }
        realm.commitTransaction();
        Log.e("Controller", " all data " + resultdata.size());
        return resultdata;
    }

    public void DeletePersonById(final int position, final int id) {

        //  final RealmResults<Person> dataResult = realm.where(Person.class).equalTo("PersonID", id).findAll();
        final RealmResults<Person> dataResult = realm.where(Person.class).findAll();
        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove a single object
                Person person = dataResult.get(position);
                person.deleteFromRealm();
            }
        });
    }
}
