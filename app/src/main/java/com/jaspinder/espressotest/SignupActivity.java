package com.jaspinder.espressotest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener
{

	@BindView(R.id.name)
	EditText name;

	@BindView(R.id.email)
	EditText email;

	@BindView(R.id.password)
	EditText password;

	@BindView(R.id.confirmPass)
	EditText confirmPass;

	@BindView(R.id.signup)
	Button signup;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		ButterKnife.bind(this);

		signup.setOnClickListener(this);

	}
	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.signup)
		{
			if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(confirmPass.getText().toString()))
			{
				showDialog(getString(R.string.fill_all_fields));
			}
			else
			{
				if(!isEmailValid(email.getText().toString()))
				{
					showDialog(getString(R.string.invalid_email));
					return;
				}
				if(!password.getText().toString().equals(confirmPass.getText().toString()))
				{
					showDialog(getString(R.string.password_mismatch));
					return;
				}

				Intent i = new Intent(SignupActivity.this, HomeActivity.class);
				startActivity(i);
				finish();

			}
		}
	}

	private void showDialog(String message)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setPositiveButton(message,
				(dialog, which) -> dialog.dismiss());
		alertDialogBuilder.show();
	}

	public static boolean isEmailValid(String email) {
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
