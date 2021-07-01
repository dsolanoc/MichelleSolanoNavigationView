package com.example.tareanv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tareanv.models.UserLogin;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Acceso extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    UserLogin userLogin;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    View view;
    TextView txt_userName,txtcorreo;
    CircleImageView img_foto;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);
        Bundle traerUsuario=this.getIntent().getExtras();
        userLogin=new UserLogin(traerUsuario.getString("username"),traerUsuario.getString("email"),traerUsuario.getString("password"),traerUsuario.getString("foto"));
        drawerLayout=findViewById(R.id.acceso_layout);
        navigationView=findViewById(R.id.navigation_container);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        view = navigationView.getHeaderView(0);
        txt_userName = view.findViewById(R.id.txt_userName);
        txt_userName.setText(userLogin.getUsername());
        img_foto = view.findViewById(R.id.img_foto);
        txtcorreo=view.findViewById(R.id.txt_correo);
        txtcorreo.setText(userLogin.getEmail());
        Glide.with(this).load(userLogin.getFoto()).centerCrop().into(img_foto);


        if(txt_userName.getText()==userLogin.getUsername()){
            Menu menu = navigationView.getMenu();

            MenuItem item = menu.findItem(R.id.amigos);
            item.setVisible(false);
        }



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId()==R.id.menu){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameContent,new PrimerFragment());
            fragmentTransaction.commit();
        }
        return true;
    }

}
