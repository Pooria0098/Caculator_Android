package ir.maktabkhune.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;

    Button subtract;
    Button plus;
    Button division;
    Button multiplication;

    Button equal;
    Button clear;

    final Double[] num_1 = new Double[1];
    final Double[] num_2 = new Double[1];
    final Double[] result = new Double[1];
    final Character[] operator = new Character[1];

    final int[] count = {1};
    final int[] count_operator = {1};
    final int[] count_equal = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        Log.d("Numbers", "Value of num one is " + Arrays.toString(num_1));
        Log.d("Numbers", "Value of num two is " + Arrays.toString(num_2));

        operandHandler(btn_zero);
        operandHandler(btn_one);
        operandHandler(btn_two);
        operandHandler(btn_three);
        operandHandler(btn_four);
        operandHandler(btn_five);
        operandHandler(btn_six);
        operandHandler(btn_seven);
        operandHandler(btn_eight);
        operandHandler(btn_nine);
        operandHandler(btn_zero);

        operatorHandler(multiplication,'*');
        operatorHandler(division,'/');
        operatorHandler(plus,'+');
        operatorHandler(subtract,'-');

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_equal[0]++;
                Log.d("Numbers", "count_operator " + count_operator[0]);

                if (count[0] <= 1 || count_operator[0] > 2) {
                    if (count_equal[0] > 2)
                        num_1[0] = Double.parseDouble(textView.getText().toString());
                    else
                        num_2[0] = Double.parseDouble(textView.getText().toString());
                    count[0] += 1;
                } else
                    num_1[0] = Double.parseDouble(textView.getText().toString());//BUG

                switch (operator[0]) {
                    case '*':
                        result[0] = num_1[0] * num_2[0];
                        textView.setText(String.valueOf(result[0]));
                        break;
                    case '/':
                        result[0] = num_1[0] / num_2[0];
                        if (num_2[0] == 0.0)
                            textView.setText("NaN");
                        else
                            textView.setText(String.valueOf(result[0]));
                        break;
                    case '+':
                        result[0] = num_1[0] + num_2[0];
                        textView.setText(String.valueOf(result[0]));
                        break;
                    case '-':
                        result[0] = num_1[0] - num_2[0];
                        textView.setText(String.valueOf(result[0]));
                        break;
                }
                num_1[0] = null;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_1[0] = null;
                num_2[0] = null;
                operator[0] = null;
                count[0] = 1;
                count_operator[0] = 1;
                count_equal[0] = 1;
                textView.setText("");
            }
        });
    }

    private void findViews(){
        btn_zero = findViewById(R.id.zero);
        btn_one = findViewById(R.id.one);
        btn_two = findViewById(R.id.two);
        btn_three = findViewById(R.id.three);
        btn_four = findViewById(R.id.four);
        btn_five = findViewById(R.id.five);
        btn_six = findViewById(R.id.six);
        btn_seven = findViewById(R.id.seven);
        btn_eight = findViewById(R.id.eight);
        btn_nine = findViewById(R.id.nine);

        subtract = findViewById(R.id.subtract);
        plus = findViewById(R.id.plus);
        division = findViewById(R.id.division);
        multiplication = findViewById(R.id.multiplication);
        equal = findViewById(R.id.equal);

        clear = findViewById(R.id.clear);

        textView = findViewById(R.id.display);
    }

    private void operandHandler(final Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = btn.getText().toString();
                boolean valid = false;
                for (int i = 0; i < textView.getText().length(); i++) {
                    if (textView.getText().charAt(i) == '.') {
                        valid = false;
                        break;
                    } else
                        valid = true;
                }
                Log.d("Numbers", "Valid " + valid);
                if (valid)
                    textView.setText(textView.getText() + str);
                else {
                    textView.setText(str);
                }
            }
        });
    }

    private void operatorHandler(final Button btn,final char operators){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_2[0] == null && textView.getText()=="")
                    operator[0] = operators;
                else{
                    boolean exp = false;
                    if (num_1[0] != null) exp = true;

                    if (!exp) {
                        num_1[0] = Double.parseDouble(textView.getText().toString());
                        textView.setText("");
                    } else {
                        num_2[0] = Double.parseDouble(textView.getText().toString());
                        textView.setText("");
                        switch (operator[0]) {
                            case '*':
                                num_1[0] = num_1[0] * num_2[0];
                                textView.setText(String.valueOf(num_1[0]));
                                break;
                            case '/':
                                num_1[0] = num_1[0] / num_2[0];
                                if (num_2[0] == 0.0)
                                    textView.setText("NaN");
                                else
                                    textView.setText(String.valueOf(num_1[0]));
                                break;
                            case '+':
                                num_1[0] = num_1[0] + num_2[0];
                                textView.setText(String.valueOf(num_1[0]));
                                break;
                            case '-':
                                num_1[0] = num_1[0] - num_2[0];
                                textView.setText(String.valueOf(num_1[0]));
                                break;
                        }
                    }
                    operator[0] = operators;
                    count_operator[0]++;
                    count_equal[0] = 1;
                    Log.d("Numbers", "count_operator " + count_operator[0]);
                }
            }
        });
    }
}