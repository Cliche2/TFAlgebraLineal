package com.example.user.hamming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Encode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
    }

    public void Gotodecifrar(View view) {

        String mensajeoriginal =  ((EditText) findViewById(R.id.Mensaje)).getText().toString();
        TextView respuesta = (TextView) findViewById(R.id.rpta);
        if(mensajeoriginal==""){
            respuesta.setText("Ingrese mensaje a decifrar");
            return;
        }
        int [] arrBinario = Cadena_a_Binario(mensajeoriginal,mensajeoriginal.length());
        String mensajeCodificado = FPrincipal_Codificar(arrBinario);
        respuesta.setText(mensajeCodificado);
    }

    public static int[] Cadena_a_Binario(String s, int n){
        n= n*8;
        int [] bits = new int[n];
        int cont=0;
        for(int i=0;i<s.length();i++){
            for(int j = 7; j >= 0; j--)
                bits[cont++] = (((1<<j) & s.charAt(i)) != 0 ? 1:0);
        }

        return bits;
    }

    public static int [] Codificar(int [] a){
        int[]arr = new int[7];
        int caux=0;
        for(int i=0;i<7;i++) arr[i] = (i==0 || i== 1 || i == 3)? 0 :a[caux++];
        //Matriz que representa las operaciones -1: no hay dato
        int [][] matrizhamming = new int[4][7];

        for(int i=0; i< 4;i++)
            for(int j=0;j<7;j++)
                matrizhamming[i][j] = (i == 0 && j != 0 && j!= 1 && j!= 4) ? arr[j] : -1;

        for(int i= 1; i<4;i++){
            for(int j=0;j<7;j++){
                if(i==1)
                    matrizhamming[i][j] = (j%2 == 0 && j!=0) ? arr[j]: matrizhamming[i][j];
                else if(i==2)
                    matrizhamming[i][j] = (j == 2 || j == 5 || j==6) ? arr[j]: -1;

                else if(i==3)
                    matrizhamming[i][j] = (j>3)? arr[j] : matrizhamming[i][j];
            }
        }

        int cont;
        for(int i= 1; i <4;i++){
            cont = 0;
            for(int j=0;j<7;j++)
                if(matrizhamming[i][j] == 1) cont++;
            if(i==1) matrizhamming[1][0] = cont%2;
            else if(i==2) matrizhamming[2][1] = cont%2;
            else if(i==3) matrizhamming[3][3] = cont%2;
        }


        //Bajamos los bits de paridad
        arr[0] = matrizhamming[1][0];
        arr[1] = matrizhamming[2][1];
        arr[3] = matrizhamming[3][3];

        return arr;
    }

    public static int[] Codificar_MensajeEntero(int [] arr){

        int n = (arr.length * 7)/4;
        int[] bits = new int[n];
        int pos_bits = 0;
        int[] bits_aux = new int[4];
        for(int i=0;i<arr.length;i = i + 4){
            for(int j=0;j<4;j++)
                bits_aux[j] = arr[j+i];
            int[] codificado = Codificar(bits_aux);
            for(int j=0;j<7;j++)
                bits[pos_bits++] = codificado[j];
        }

        return bits;
    }

    public static  String FPrincipal_Codificar(int [] arr){
        int [] MensajeCifradoBits = Codificar_MensajeEntero(arr);
        String msj = "";
        for(int i=0;i<MensajeCifradoBits.length;i++)
            msj = msj + MensajeCifradoBits[i];
        return  msj;
    }
}
