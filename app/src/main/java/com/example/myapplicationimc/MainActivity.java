package com.example.myapplicationimc;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

// Classe principal MainActivity
public class MainActivity extends AppCompatActivity {

    // Declaração de elementos de interface do usuário
    private TextView textResultado;
    private EditText editPeso;
    private EditText editAltura;
    private TableLayout tableLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontrar e configurar a barra de ferramentas personalizada como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar elementos de interface do usuário
        textResultado = findViewById(R.id.textResultado);
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
        tableLayout = findViewById(R.id.tableLayout);
        drawerLayout = findViewById(R.id.drawerLayout);

        // Inicialmente, ocultar a tabela
        tableLayout.setVisibility(View.GONE);

        // Definir ouvinte de clique para o botão "Limpar"
        Button buttonLimparResultado = findViewById(R.id.buttonLimpar);
        buttonLimparResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparResultado(v);
            }
        });

        // Configurar o ActionBarDrawerToggle para a gaveta de navegação
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    // Método para adicionar uma linha à tabela de classificação
    private void addRowToTable(String imcRange, String classification) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        TextView imcTextView = new TextView(this);
        imcTextView.setText(imcRange);
        imcTextView.setGravity(Gravity.CENTER);
        row.addView(imcTextView);

        TextView classificationTextView = new TextView(this);
        classificationTextView.setText(classification);
        classificationTextView.setGravity(Gravity.CENTER);
        row.addView(classificationTextView);

        tableLayout.addView(row);
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
            Intent intent = new Intent(MainActivity.this, Optionmenuitemclick.class);
            startActivity(intent);


            return true;
        } else if (item.getItemId() == R.id.item0) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            // Lidar com o clique no botão de alternância da gaveta de navegação
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Método para calcular o IMC
    public void calcularImc(View view) {
        // Obter o texto dos campos de edição de peso e altura
        String pesoStr = editPeso.getText().toString();
        String alturaStr = editAltura.getText().toString();

        // Verificar se algum dos campos de edição está vazio
        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            // Exibir uma mensagem de erro ou lidar adequadamente (por exemplo, mostrar um Toast)
            Toast.makeText(this, "Please, enter your weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double resultado = peso / (altura * altura);
        String mensagem;

        // Ocultar o teclado virtual
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Determinar a classificação do IMC
        if (resultado < 19) {
            mensagem = "Under weight";
        } else if (resultado < 25) {
            mensagem = "Normal weight";
        } else if (resultado < 30) {
            mensagem = "Overweight";
        } else if (resultado < 40) {
            mensagem = "Type 1 obesity";
        } else {
            mensagem = "Type 2 obesity";
        }

        // Limpar a tabela antes de adicionar novas linhas
        tableLayout.removeAllViews();

        // Exibir a tabela de classificação
        tableLayout.setVisibility(View.VISIBLE);

        // Adicionar cabeçalhos à tabela
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        TextView headerIMC = new TextView(this);
        headerIMC.setText("BMI");
        headerIMC.setGravity(Gravity.CENTER);
        headerRow.addView(headerIMC);

        TextView headerClassificacao = new TextView(this);
        headerClassificacao.setText("Classification");
        headerClassificacao.setGravity(Gravity.CENTER);
        headerRow.addView(headerClassificacao);

        tableLayout.addView(headerRow);

        // Adicionar dados à tabela
        addRowToTable("Less than 18.5", "Under weight");
        addRowToTable("18.5 - 24.9", "Normal weight");
        addRowToTable("25.0 - 29.9", "Overweight");
        addRowToTable("30.0 - 39.9", "Type 1 obesity");
        addRowToTable("40.0 or more", "Type 2 obesity");

        // Exibir o resultado
        textResultado.setText("Your BMI is " + resultado + "\nClassificação: " + mensagem);
    }

    // Método para limpar o resultado
    public void limparResultado(View view) {
        // Limpar o resultado
        textResultado.setText("");

        // Verificar se a tabela contém pelo menos uma linha antes de tentar removê-las
        if (tableLayout.getChildCount() > 0) {
            // Limpar a tabela
            tableLayout.removeAllViews();
        }

        // Ocultar a tabela
        tableLayout.setVisibility(View.GONE);
    }

    // Método para sincronizar o estado do ActionBarDrawerToggle
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sincronizar o estado do ActionBarDrawerToggle após onRestoreInstanceState ter ocorrido.
        actionBarDrawerToggle.syncState();
    }

    // Adicionar outros métodos e lógica conforme necessário
}

