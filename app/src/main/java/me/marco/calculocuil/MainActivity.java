package me.marco.calculocuil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_Calcular = findViewById(R.id.btn_Calc);

        final EditText DNI = findViewById(R.id.txt_dni);

        final EditText Res = findViewById(R.id.txt_TuCuilEs);

        final Spinner s_Sexo = findViewById(R.id.D_Sexo);

        btn_Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String Sexo = s_Sexo.getSelectedItem().toString();

               if(DNI.getText().toString().isEmpty()){
                   Toast Errormsg = Toast.makeText(getApplicationContext(), "Ingresa tu DNI.", Toast.LENGTH_SHORT);
                   Errormsg.setGravity(Gravity.CENTER, 0, 0);
                   Errormsg.show();
               }else{
                   Calcular(DNI.getText().toString(),Sexo);
               }
            }
        });
    }

    private void Calcular(String DOC, String Sexo){

        //Msg
        final TextView Txt = findViewById(R.id.txt_Cuil2);
        //Txt for cuil
        final TextView CUIL = findViewById(R.id.txt_TuCuilEs);
        //Ints used for 20,23 or 27
        int X = 0;
        int y = 0;

        if(Sexo.equalsIgnoreCase("Masculino")){
            X = 2;
            y = 3;

            Toast msg2 = Toast.makeText(getApplicationContext(), "Si queres calcular el CUIL con el numero 20 usa Masculino 2", Toast.LENGTH_SHORT);
            msg2.setGravity(Gravity.CENTER, 0, 0);
            msg2.show();

        }else if(Sexo.equalsIgnoreCase("Femenino")){

            X = 2;
            y = 7;

        }else if (Sexo.equalsIgnoreCase("Masculino 2")){

            X = 2;
            y = 0;

        }else if(Sexo.equalsIgnoreCase("Femenino 2")){

            X = 2;
            y = 3;

        }else{

            Toast msg2 = Toast.makeText(getApplicationContext(), "Sexo invalido", Toast.LENGTH_SHORT);
            msg2.setGravity(Gravity.CENTER, 0, 0);
            msg2.show();

        }

        //Parse the int to a char array

        char[] CharsINDNI = DOC.toCharArray();

        int i = 0;

        int[] Schema = {3,2,7,6,5,4,3,2};


        int Total = 0;

        Total += X * 5;
        Total += y * 4;


        System.out.println("Pretotoal " + Total);
        while (i != CharsINDNI.length){

            System.out.println("x " + X + "y " + y);

            int Mult = Character.getNumericValue(CharsINDNI[i]) * Schema[i];


            Total = Total + Mult;


            i++;


        }

        int Div = Total/11;

        int PreDig = Total - (Div * 11);

        int Digit =  11 - PreDig;

        Txt.setVisibility(View.VISIBLE);

        CUIL.setVisibility(View.VISIBLE);
        CUIL.setText(String.valueOf(X) + String.valueOf(y) + "-" + DOC + "-" + Digit);
    }
}