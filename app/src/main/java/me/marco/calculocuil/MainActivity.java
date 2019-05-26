package me.marco.calculocuil;

import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.IdentityHashMap;

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

               Calcular(DNI.getText().toString(),Sexo);

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
        }else if(Sexo.equalsIgnoreCase("Femenino")){
            X = 2;
            y = 7;
        }

        //Parse the int to a char array

        char[] CharsINDNI = DOC.toCharArray();

        System.out.println("X" + X + "Y" + y );
        int i = 0;

        int[] Schema = {3,2,7,6,5,4,3,2};


        int Total = 0;

        Total += 2 * 5;
        Total += 3 * 4;

        while (i != CharsINDNI.length){


            int Mult = Character.getNumericValue(CharsINDNI[i]) * Schema[i];

            System.out.println("Mult de: " + CharsINDNI[i] + " por " + Schema[i] + " Segun esta mierda es" + Mult);


            Total = Total + Mult;


            i++;


            //System.out.println(Total);
        }

        int Div = Total/11;

        int PreDig = Total - (Div * 11);


        int Digit =  11 - PreDig;

        Txt.setVisibility(View.VISIBLE);

        CUIL.setVisibility(View.VISIBLE);
        CUIL.setText(String.valueOf(X) + String.valueOf(y) + "-" + DOC + "-" + Digit);

    }
}