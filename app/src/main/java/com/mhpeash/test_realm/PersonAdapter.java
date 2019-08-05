package com.mhpeash.test_realm;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;

public class PersonAdapter extends BaseAdapter {
    List<Person>personList;
    private Realm realm;
    private Context context;
    private RealmController realmController;

    public PersonAdapter (List<Person> mPerson, Context cntext1) {
        this.personList=mPerson;
        this.context=cntext1;
        Log.d("adapter", "size" + personList.size());
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personList.indexOf(getItem(position));
    }


    public void remove(int position,int deletedId) {
        realm = Realm.getDefaultInstance();
        realmController = new RealmController(realm);
        realmController.DeletePersonById(position,deletedId);
       // personList.remove(position);
        notifyDataSetChanged();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        MyHolder myHolder = null;
        Log.e("Adapter", " size " + personList.size());
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = layoutInflater.inflate(R.layout.person_list, parent, false);
            myHolder.txtPersonID = (TextView) convertView.findViewById(R.id.txtPersonID);
            myHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            myHolder.txtMobile = (TextView) convertView.findViewById(R.id.txtMobile);
            myHolder.txtSalary = (TextView) convertView.findViewById(R.id.txtSalary);
            myHolder.cardView = (CardView) convertView.findViewById(R.id.maincard);

            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        final Person person = personList.get(position);
       // myHolder.txtPersonID.setText(person.getPersonID());
        myHolder.txtName.setText(person.getName());
        myHolder.txtMobile.setText(person.getMobile());
        myHolder.txtSalary.setText(person.getSalary());
      //  final int id = Math.toIntExact(person.getPersonID());


//        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                context.startActivity(new Intent(context, UpdateActivity.class).putExtra("id", id).
//                        putExtra("fname", person.getFname()).putExtra("lname", person.getLname()).putExtra("contact", person.getContact()));
//            }
//        });


        return convertView;
    }

    public class MyHolder {
        TextView txtPersonID;
        TextView txtName;
        TextView txtMobile;
        TextView txtSalary;
        CardView cardView;
    }

}
