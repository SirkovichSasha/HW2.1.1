package com.example.paynetology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText mInputMoney;
    private EditText mInputInfo;
    private Button mBtnOk;
    private CheckBox mBankCardChkBx;
    private CheckBox mMobilePhoneChkBx;
    private CheckBox mCashAddressChkBx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = null;
                String inputInfoStr=mInputInfo.getText().toString();
                String inputMoneyStr=mInputMoney.getText().toString();
                String errorStr=getString(R.string.warning);
                try {
                    checkBox=checked();
                }catch(NullPointerException e)
                 {
                 Toast.makeText(MainActivity.this,errorStr, Toast.LENGTH_SHORT).show();
                  }
                 finally {
                       mInputInfo.getText().clear();
                       mInputMoney.getText().clear();
                       resetCheckBoxes();
                  }

                if (checkBox==null) {
                    Toast.makeText(MainActivity.this,errorStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String checkBoxName=checkBox.getText().toString();
                String payMessage=getString(R.string.pay_message,inputMoneyStr,inputInfoStr,checkBoxName);
                Toast.makeText(MainActivity.this, payMessage, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initViews(){
        mInputMoney = findViewById(R.id.inputMoney);
        mInputInfo = findViewById(R.id.inputInfo);
        mBtnOk = findViewById(R.id.btnOK);
        mBankCardChkBx = findViewById(R.id.bankCardChkBx);
        mMobilePhoneChkBx = findViewById(R.id.mobilePhoneChkBx);
        mCashAddressChkBx = findViewById(R.id.cashAddressChkBx);
    }

    private void resetCheckBoxes(){
        mBankCardChkBx.setChecked(false);
        mMobilePhoneChkBx.setChecked(false);
        mCashAddressChkBx.setChecked(false);
        mBankCardChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mMobilePhoneChkBx.setOnCheckedChangeListener(checkedChangeListener);
        mCashAddressChkBx.setOnCheckedChangeListener(checkedChangeListener);
    }

    private CheckBox checked()
    {
        if (mBankCardChkBx.isChecked()) return mBankCardChkBx;
        if (mMobilePhoneChkBx.isChecked()) return mMobilePhoneChkBx;
        if (mCashAddressChkBx.isChecked()) return mCashAddressChkBx;
        return null;
    }
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                switch (compoundButton.getId()) {
                    case R.id.bankCardChkBx:
                        resetCheckBoxes();
                        mBankCardChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case R.id.mobilePhoneChkBx:
                        resetCheckBoxes();
                        mMobilePhoneChkBx.setChecked(true);
                        mInputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    case R.id.cashAddressChkBx:
                        resetCheckBoxes();
                        mInputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                        mCashAddressChkBx.setChecked(true);
                        break;
                    default:
                }
            }
        }
    };
}
