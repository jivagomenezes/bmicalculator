package com.example.myapplicationimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;  // Duplicado; removido para evitar redundância
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplicationimc.databinding.ActivityOptionmenuitemclickBinding;

public class Optionmenuitemclick extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityOptionmenuitemclickBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_optionmenuitemclick);


        // Encontrar e configurar a barra de ferramentas personalizada como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    // Método para criar o menu de opções
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    // Método para lidar com a seleção de itens do menu de opções
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            // Lidar com o clique no item "Sobre Nós" do menu
            Intent intent = new Intent(Optionmenuitemclick.this, Optionmenuitemclick.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.item0) {
            // Lidar com o clique no item "Início" do menu
            Intent intent = new Intent(Optionmenuitemclick.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else {
            // Chamar a implementação da superclasse se o item selecionado não for tratado aqui
            return super.onOptionsItemSelected(item);
        }
    }
}
