package com.example.megamart;



import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecurityPrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_privacy);

        TextView privacyText = findViewById(R.id.tv_privacy_policy);

        String policy = "At PlogMate, your privacy is important to us.\n\n" +
                "We are committed to protecting any personal information you share with us. " +
                "This includes data like your location or files (if permission is granted), which are used only to enhance your app experience and provide the core features of Plogmate.\n\n" +
                "What we collect:\n" +
                "- Location Data: Only if you enable it, and only for features that require your current location.\n" +
                "- Storage Access: Used to save and read files related to your activity in the app.\n" +
                "- We do not collect personal identifiers like your name, email, or contact info unless entered by you.\n\n" +
                "How your data is protected:\n" +
                "- All data is stored securely on your device or on our secure servers (if online sync is used).\n" +
                "- We do not share your data with third parties without your consent.\n" +
                "- We do not sell your personal information.\n\n" +
                "Your Control:\n" +
                "- You can revoke app permissions anytime from your phone settings.\n" +
                "- You can clear app data or uninstall the app to delete all locally stored information.\n\n" +
                "If you have any concerns, feel free to contact our support team.";

        privacyText.setText(policy);
    }
}