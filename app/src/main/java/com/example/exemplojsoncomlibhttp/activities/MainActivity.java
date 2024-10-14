package com.example.exemplojsoncomlibhttp.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplojsoncomlibhttp.Auxilia;
import com.example.exemplojsoncomlibhttp.Conexao;
import com.example.exemplojsoncomlibhttp.R;
import com.example.exemplojsoncomlibhttp.model.Contato;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private TextView textViewID;
    private final String URL = "https://my-json-server.typicode.com/apag-guimaraes/teste-myjsonserver/db";
    private StringBuilder builder = null;
    private Contato dadosBaixados = null;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewID = findViewById(R.id.dadosID);

        executorService = Executors.newSingleThreadExecutor();

        baixarDados();
    }//onCreate

    private void baixarDados(){
        executorService.execute(() -> {
            try {
                Conexao conexao = new Conexao();
                InputStream inputStream = conexao.obterRespostaHTTP(URL);
                Auxilia auxilia = new Auxilia();
                String textoJSON = auxilia.converter(inputStream);
                Log.i("JSON", "Dados recebidos: " + textoJSON);

                if (textoJSON != null){
                    Gson gson = new Gson();
                    Type type = new TypeToken<Contato>() {}.getType();
                    dadosBaixados = gson.fromJson(textoJSON, type);
                    runOnUiThread(() -> {
                        if (dadosBaixados != null)
                            textViewID.setText(dadosBaixados.toString());
                        else
                            Toast.makeText(getApplicationContext(), "Nenhum dado encontrado!", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Não foi possível obter JSON", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e){
                Log.e("Erro", "Erro ao baixar os dados", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null && !executorService.isShutdown())
            executorService.shutdown();
    }

}//class



