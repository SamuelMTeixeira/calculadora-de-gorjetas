package com.samu.calculadora_de_gorjetas;

import android.content.DialogInterface;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.seekbar = findViewById(R.id.seekBarPercent);
        this.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // ALTERA O VALOR DA PORCENTAGEM
                TextView txtPct = findViewById(R.id.txtPercent);
                txtPct.setText(i+"%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // PEGA O VALOR QUE DESEJA CALCULAR
                EditText tfValor = findViewById(R.id.tfValor);

                TextView tfGorjeta = findViewById(R.id.txtResultadoGorjeta);

                tfGorjeta.setText( setCalcularGorjeta(tfValor.getText().toString(), seekBar.getProgress()) );
            }
        });
    }

    String setCalcularGorjeta(String valor, int pct){

        try {
            float valorFinal = Float.parseFloat(valor) * ((float) pct/100);

            TextView tfValorFinal = findViewById(R.id.txtResultadoTotal);
            tfValorFinal.setText( "R$ "+ String.format("%.2f", valorFinal + Float.parseFloat(valor) ));

            return "R$ "+ String.format("%.2f", valorFinal);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Informe um valor númerico", Toast.LENGTH_SHORT);
            toast.show();
            EditText tfValor = findViewById(R.id.tfValor);
            tfValor.requestFocus();
            return "R$ 0";
        }

    }


}