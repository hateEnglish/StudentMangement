package com.example.android.studentmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.studentmanagementsystem.Model.Studentinfo;

/**
 * is used for submittting data and send it back to display
 */
public class Main2Activity extends AppCompatActivity {
    private String mname;
    private String mschool;
    private String mrollno;
    private String mEmail;
    private boolean mchecked, isCheck = true;
    private Button button;
    private Studentinfo studentInfoobj;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.btnSubmitdata);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                boolean b = checkValidation();
                if (b == isCheck) {
                    Intent intent = new Intent();
                    studentInfoobj = new Studentinfo(mname, mschool, mrollno, mEmail);
                    intent.putExtra("infoobject", studentInfoobj);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(Main2Activity.this, "enter details again!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    /**
     * @return checks the validation
     */

    private boolean checkValidation() {

        EditText viewname = (EditText) findViewById(R.id.etname);
        mname = viewname.getText().toString();
        EditText viewschool = (EditText) findViewById(R.id.etschoolname);
        mschool = viewschool.getText().toString();
        EditText viewrollno = (EditText) findViewById(R.id.etrollno);
        mrollno = viewrollno.getText().toString();
        EditText viewEmail = (EditText) findViewById(R.id.etemail);
        mEmail = viewEmail.getText().toString();

        if (mname.isEmpty() || mschool.isEmpty() || mrollno.isEmpty()) {
            if (isValidEmail(mEmail)) {
                return true;
            }
            return false;
        }

        /**
         * returns if all condition satisfy
         */
        return true;

    }

    /**
     * @param email validation
     * @return return email
     */

    private boolean isValidEmail(final String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * @param view radio button validation
     */
    public void onRadioButtonClicked(final View view) {
        mchecked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbtnMale:
                if (mchecked) {
                    Toast.makeText(this, "Male", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.rbtnFemale:
                if (mchecked) {
                    Toast.makeText(this, "Female", Toast.LENGTH_LONG).show();

                }
                break;
            default:
                Toast.makeText(this, "check a button first", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
