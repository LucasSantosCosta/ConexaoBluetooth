package br.com.fiap.conexaobluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.OutputStream;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Spinner spDispositivos;
    EditText txtInformacao;

    BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    BluetoothSocket soquete = null;
    OutputStream saida = null;
    Set<BluetoothDevice> dispositivosPareados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spDispositivos = (Spinner) findViewById(R.id.spDispositivos);
        txtInformacao = (EditText) findViewById(R.id.txtInformacao);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("Selecione um dispositivo");

        //O aparelho tem bluetooth?
        if (bluetooth != null){

            // Bluetooth não está ativo?
            //Manda ativar...
            if (!bluetooth.isEnabled()){
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                int REQUEST_ENABLE_BT = 1;
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            }

            //Retorna todos os dispositivos pareados com o aparelho.
            dispositivosPareados = bluetooth.getBondedDevices();

            if ( dispositivosPareados.size() > 0 ) {
                for (BluetoothDevice item : dispositivosPareados) {
                    adapter.add(item.getName());
                }
            }
        }
        spDispositivos.setAdapter(adapter);

        }
    }

    public void enviar(View view) {



    }
}
