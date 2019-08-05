package com.mhpeash.test_realm;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.util.List;

import io.realm.Realm;

public class PersonList extends ListActivity {

    RealmController realmController;
    Realm realm;
    ListView listView;
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        realmController = new RealmController(realm);
        listView = findViewById(android.R.id.list);
        listView=getListView();
        final List<Person> allPerson = realmController.getAllPerson();
        personAdapter = new PersonAdapter(allPerson, this);
        listView.setAdapter(personAdapter);



        swap_to_delete();
    }

    private void swap_to_delete(){
        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }
                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {

                                personAdapter.remove(position,(int) personAdapter.getItemId(position));
                                // textAdapter.remove(ITEMID);
                                Log.d("Selected ID",""+(int) personAdapter.getItemId(position));
                                final List<Person> personList = realmController.getAllPerson();
                                personAdapter = new PersonAdapter(personList,getApplicationContext());
                                listView.setAdapter(personAdapter);
                            }
                        });

        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
