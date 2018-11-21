package hk.hku.cs.drawertest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TalentActivity extends AppCompatActivity {

    private Button security_btn, multimedia_btn, financial_btn, general_btn, toapp_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent);

        security_btn = findViewById(R.id.security);
        multimedia_btn = findViewById(R.id.multimedia);
        financial_btn = findViewById(R.id.financial);
        general_btn = findViewById(R.id.general);

        security_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to cyber security activity
                Intent intent = new Intent(v.getContext(),SecurityActivity.class);
                startActivity(intent);
            }
        });

        multimedia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to multimedia activity
                Intent intent = new Intent(v.getContext(), MultimediaActivity.class);
                startActivity(intent);
            }
        });

        financial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to financial activity
                Intent intent = new Intent(v.getContext(), FinancialActivity.class);
                startActivity(intent);
            }
        });

        general_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to general activity
                Intent intent = new Intent(v.getContext(), GeneralActivity.class);
                startActivity(intent);
            }
        });
    }
}
