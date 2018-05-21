package com.senganipatel.gracia;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ContactUsFragment extends Fragment {
    private EditText commentsField;
    private String contactUs = "1/61, W.H.S. Kirti Nagar,\n New Delhi - 110015\nPhone - 011-41001024\nCustomer Care - 8826302277\nWeb - www.graciaveneers.com\nEmail - info@graciaveneers.com, graciaveneers@gmail.com \n";
    private EditText emailField;
    private EditText mobField;
    private EditText nameField;
    private Button resetButton;
    private ProgressDialog spinner;
    private Button submitButton;

    private String name;
    private String email;
    private String mobile;
    private String comments;

    private class FormPostTask extends AsyncTask<Void, Void, String> {
        private FormPostTask() {
        }

        protected String doInBackground(Void... params) {
            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://graciaveneers.com/classes/contactaap.php");
                List<NameValuePair> nameValuePair = new ArrayList(2);
                nameValuePair.add(new BasicNameValuePair("name", ContactUsFragment.this.name));
                nameValuePair.add(new BasicNameValuePair("email", ContactUsFragment.this.email));
                nameValuePair.add(new BasicNameValuePair("phone", ContactUsFragment.this.mobile));
                nameValuePair.add(new BasicNameValuePair("subject", "Sales/Support"));
                nameValuePair.add(new BasicNameValuePair("comments", ContactUsFragment.this.comments));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                HttpResponse response = httpClient.execute(httpPost);
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        reader.close();
                        return sb.toString();
                    }
                    sb.append(line);
                }
            } catch (Exception e) {
                ContactUsFragment.this.spinner.cancel();
                Toast.makeText(ContactUsFragment.this.getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                return null;
            }

        }

        protected void onPostExecute(String result) {
            ContactUsFragment.this.spinner.cancel();
            ContactUsFragment.this.spinner = null;
            super.onPostExecute(result);
            Toast.makeText(ContactUsFragment.this.getActivity(), Html.fromHtml(result), Toast.LENGTH_LONG).show();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contactus_fragment, container, false);
        ((TextView) rootView.findViewById(R.id.contact_us_content_comp_name)).setText("GRACIA VENEERS");
        LinearLayout formElements = (LinearLayout) rootView.findViewById(R.id.feed_back_form).findViewById(R.id.formElement);
        this.nameField = (EditText) formElements.findViewById(R.id.nameText);
        this.emailField = (EditText) formElements.findViewById(R.id.emailText);
        this.mobField = (EditText) formElements.findViewById(R.id.mobText);
        this.commentsField = (EditText) formElements.findViewById(R.id.queryText);
        LinearLayout buttons = (LinearLayout) formElements.findViewById(R.id.buttons);
        this.submitButton = (Button) buttons.findViewById(R.id.submitButton);
        this.resetButton = (Button) buttons.findViewById(R.id.resetButton);
        TextView contactUsContent = (TextView) rootView.findViewById(R.id.contact_us_content);
        contactUsContent.setText(this.contactUs);
        contactUsContent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContactUsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://gracia.senganipatel.com")));
            }
        });
        this.submitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContactUsFragment.this.sumbitForm();
            }
        });
        this.resetButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ContactUsFragment.this.resetForm();
            }
        });
        if (savedInstanceState != null) {
            this.nameField.setText(savedInstanceState.getString("nameField") == null ? "" : savedInstanceState.getString("nameField"));
            this.emailField.setText(savedInstanceState.getString("emailField") == null ? "" : savedInstanceState.getString("emailField"));
            this.mobField.setText(savedInstanceState.getString("mobField") == null ? "" : savedInstanceState.getString("mobField"));
            this.commentsField.setText(savedInstanceState.getString("commentsField") == null ? "" : savedInstanceState.getString("commentsField"));
        }
        return rootView;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString("nameField", this.nameField.getText().toString());
        outState.putString("emailField", this.emailField.getText().toString());
        outState.putString("mobField", this.mobField.getText().toString());
        outState.putString("commentsField", this.commentsField.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void sumbitForm() {
        if (!isConnectedToInternet()) {
            Toast.makeText(getActivity(), "Internet connection is unavailable", Toast.LENGTH_LONG).show();
        } else if (isFormValid()) {
            setFieldValues();
            this.spinner = new ProgressDialog(getActivity());
            this.spinner.setProgressStyle(0);
            this.spinner.setMessage("Sending Email");
            this.spinner.setIndeterminate(true);
            this.spinner.setCanceledOnTouchOutside(false);
            this.spinner.show();
            new FormPostTask().execute(new Void[0]);
        }
    }

    private void setFieldValues() {
        name = this.nameField.getText().toString();
        email = this.emailField.getText().toString();
        mobile = this.mobField.getText().toString();
        comments = this.commentsField.getText().toString();

    }

    private boolean isFormValid() {
        String validationMessage = "";
        boolean isValid = false;
        if (this.nameField.getText().toString().trim().equals("")) {
            validationMessage = "Please enter your name";
        } else if (this.emailField.getText().toString().trim().equals("")) {
            validationMessage = "Please enter your email";
        } else if (this.mobField.getText().toString().trim().equals("")) {
            validationMessage = "Please enter your mobile no.";
        } else if (this.commentsField.getText().toString().trim().equals("")) {
            validationMessage = "Please enter query/comments";
        } else {
            isValid = true;
        }
        if (!isValid) {
            Toast.makeText(getActivity(), validationMessage, 0).show();
        }
        return isValid;
    }

    private void resetForm() {
        this.nameField.setText("");
        this.emailField.setText("");
        this.mobField.setText("");
        this.commentsField.setText("");
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService("connectivity");
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo state : info) {
                    if (state.getState().equals(State.CONNECTED)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
