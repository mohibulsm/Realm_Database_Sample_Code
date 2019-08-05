package com.mhpeash.test_realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import io.realm.Realm;

public class SaveActivity extends AppCompatActivity {


    EditText txtName, txtMobile, txtSalary;
    Button btnSave;
    private  Realm realm;
    private RealmController realmController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtName = findViewById(R.id.txtName);
        txtMobile = findViewById(R.id.txtMobile);
        txtSalary = findViewById(R.id.txtSalary);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
                startActivity(new Intent(SaveActivity.this,PersonList.class));
            }
        });

    }

   private  void Save(){
       Boolean isSaved=false;
       Date date = new Date();
       realm = Realm.getDefaultInstance();
       realmController=new RealmController(realm);
       try {
          isSaved= realmController.SavePerson(
                   txtName.getText().toString(),
                   txtMobile.getText().toString(),
                   txtSalary.getText().toString());
       } catch (Exception e) {
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
       }
       if(isSaved){
           Toast.makeText(this, "Save Successfully", Toast.LENGTH_LONG).show();
           txtName.setText("");
           txtMobile.setText("");
           txtSalary.setText("");
       }
       else
           Toast.makeText(this, "Save Failed", Toast.LENGTH_LONG).show();
   }
}
