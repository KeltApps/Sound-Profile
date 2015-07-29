package com.keltapps.soundprofile;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ProfilesActivity extends AppCompatActivity{
    ArrayList<Profile> listProfileAdapters = null;
    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        context = this;

        if (listProfileAdapters == null)
            listProfileAdapters = new ArrayList<Profile>();
        Profile profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba2");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba3");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba4");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueb5");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba6");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba7");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba8");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba9");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba10");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba11");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba12");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba13");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba14");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba15");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba16");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba17");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba18");
        listProfileAdapters.add(profile);
        profile = new Profile();
        profile.setActivado(false);
        profile.setNombre("Prueba19");
        recyclerView = (RecyclerView) findViewById(R.id.profiles_recyclerview);
        recyclerView.setHasFixedSize(true);
        final ProfileAdapter profileAdapter = new ProfileAdapter(listProfileAdapters,recyclerView);
      profileAdapter.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if (event.getAction() == MotionEvent.ACTION_DOWN)
                  v.setBackgroundColor(getResources().getColor(R.color.white_dark));
              else if (event.getAction() == MotionEvent.ACTION_UP) {
                  v.setBackgroundColor(getResources().getColor(R.color.white));
              } else if (event.getAction() == MotionEvent.ACTION_MOVE)
                  v.setBackgroundColor(getResources().getColor(R.color.white));
              return true;
          }
      });
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());





/*
       Button btnInsertar = (Button)findViewById(R.id.BtnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = new Profile();
                profile.setActivado(false);
                profile.setNombre("Prueba");
                listProfileAdapters.add(profile);
                profileAdapter.notifyItemInserted(1);
            }
        });

       Button btnEliminar = (Button)findViewById(R.id.BtnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProfileAdapters.remove(1);
                profileAdapter.notifyItemRemoved(1);
            }
        });

        Button btnMover = (Button)findViewById(R.id.BtnMover);

        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Profile aux = listProfileAdapters.get(1);
                listProfileAdapters.set(1, listProfileAdapters.get(2));
                listProfileAdapters.set(2, aux);

                profileAdapter.notifyItemMoved(1, 2);
            }
        });*/


        /*ProfileAdapter profileAdapter = new ProfileAdapter(context, listProfileAdapters);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Activity)context).finish();
            }
        });*/
    /*    Snackbar.make(this.findViewById(R.id.profiles_coordinatorlayout), "Prueba", Snackbar.LENGTH_LONG)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();*/
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.profiles_floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /*        Profile profile = new Profile();
                profile.setActivado(false);
                profile.setNombre("Prueba");
                listProfileAdapters.add(profile);
                profileAdapter.notifyItemInserted(listProfileAdapters.size());

*/
                Profile aux = listProfileAdapters.get(1);
                listProfileAdapters.set(1, listProfileAdapters.get(2));
                listProfileAdapters.set(2, aux);

                profileAdapter.notifyItemMoved(1, 2);
            }
        });
       /* ImageButton imageButton = (ImageButton) findViewById(R.id.profiles_item_buttonMore);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profiles, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
