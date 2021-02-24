package com.example.myapplication;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class home extends AppCompatActivity {
    Toolbar toolbar ;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Menu menu;
    ImageView imageicon;
    String StringArray[]={"clothing", "cosmetics", "kids", "home","kitchen"};
    private Object AdapterView;
    List<Book> listproduct;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_home);
//
//        FragmentManager fm=getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.drawer_layout,new GridFragment()).addToBackStack(null).commit();
//
//        listproduct=new ArrayList<>();
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//        listproduct.add(new Book("first", "category of first", "descrption of product", R.drawable.shoeone));
//        RecyclerView v = (RecyclerView) findViewById(R.id.recycle);
//        RecyclerViewAdapter myAdap = new RecyclerViewAdapter(this,listproduct);
//        v.setLayoutManager(new GridLayoutManager(this, 2));
//        v.setAdapter(myAdap);
        ViewPager mSlider=(ViewPager) findViewById(R.id.slideview);

        Slide2 slider=new Slide2(this);
        mSlider.setAdapter(slider);

        Spinner spi=(Spinner) findViewById(R.id.spinn);
ArrayAdapter<String> myadap=new ArrayAdapter<String>(home.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
myadap.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
spi.setAdapter(myadap);
spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(android.widget.AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getItemAtPosition(i).equals("home"))
        {
        }
        else{
            String item=adapterView.getItemAtPosition(i).toString();
            if(adapterView.getItemAtPosition(i).equals("clothing")){
                //Intent....
            }
        }
    }

    @Override
    public void onNothingSelected(android.widget.AdapterView<?> adapterView) {

    }
});
        Intent i=getIntent();
        String personName=i.getStringExtra("name");

        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);


        navigationView.bringToFront();
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.toggle));

        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iconcart:
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_home:
                Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(home.this,LoginApplication.class));
        }return  true;
    }
});
//        navigationView.setCheckedItem(R.id.nav_home);
//        menu = navigationView.getMenu();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutwo, menu);
        return true;

    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.action_settings:
//                Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.iconcart:
//                Toast.makeText(this,"cart",Toast.LENGTH_LONG).show();
//                break;
//
////            case android.R.id.home:
////            finish();
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//    public void onBackPressed(){
////        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else
//        {super.onBackPressed();
//        }
//    }



}