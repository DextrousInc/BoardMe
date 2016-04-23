package com.dextrous.hack.boardme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Object temp = getIntent().getSerializableExtra(BoardMeConstants.INTENT_PARAM_USER_ITEM_KEY);
        TextView fullNameText = (TextView) findViewById(R.id.fullNameValueLabel);
        TextView usernameText = (TextView) findViewById(R.id.usernameValueLabel);
        TextView emailText = (TextView) findViewById(R.id.emailValueLabel);
        TextView currencyTypeText = (TextView) findViewById(R.id.currencyTypeValueLabel);
        TextView walletAmountText = (TextView) findViewById(R.id.walletAmountValue);
        if(temp != null
                && temp instanceof User) {
            User userDetail = (User) temp;
            if (fullNameText != null
                    && usernameText != null
                    && emailText != null
                    && currencyTypeText != null
                    && walletAmountText != null) {
                fullNameText.setText(userDetail.getFullName());
                usernameText.setText(userDetail.getUsername());
                emailText.setText(userDetail.getEmail());
                currencyTypeText.setText(userDetail.getCurrencyType());
                walletAmountText.setText(String.valueOf(userDetail.getWallet()));
            }
        } else {
            temp = getIntent().getSerializableExtra(BoardMeConstants.INTENT_PARAM_USER_ITEM_ID_KEY);
            if (temp != null) {
                // Create a callback
                // send the input fields
                // execute the service function
                String BASE_URL = getResources().getString(R.string.base_api_url);
                Log.d("BASE_URL", BASE_URL);

                BoardMeAPIService apiService = RetrofitWrapper.build(BASE_URL);

            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
