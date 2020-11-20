package com.example.labsheeet12;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labsheeet12.Database.DBHandler;

import java.util.List;

public class Login extends AppCompatActivity {
    Button RegisterYeah;
    Button LoginYeah;
    EditText LoginUserName;
    EditText LoginPassword;
    String UserType;
    String StudentText = " Student";
    String TeacherText = " Teacher";
    String Subject,Message,User;
    final static String CHANNEL_ID = "channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RegisterYeah = findViewById(R.id.button5);
        LoginYeah = findViewById(R.id.buttonLogin);
        LoginUserName = findViewById(R.id.LoginUserName);
        LoginPassword = findViewById(R.id.LoginPassword);
        createNotificationChannel();
        


        //Create an explicit intent for an Activity in your app Intent intent = newIntent(this, Notification.class);intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);NotificationCompat.Builder builder = newNotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("My notification").setContentText("Hello World!").setPriority(NotificationCompat.PRIORITY_DEFAULT)//Set the intent that will fire when the user taps the notification.setContentIntent(pendingIntent).setAutoCancel(true);NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);//notificationId is a unique int for each notification that you must definenotificationManager.notify(0, builder.build());

        RegisterYeah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });

        LoginYeah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List answer = dbHandler.checkusernamepassword(LoginUserName.getText().toString(),LoginPassword.getText().toString());
                if(answer.isEmpty()){
                    Toast.makeText(Login.this, "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(Login.this, "Logging you " + answer.get(0).toString() + " In ! ", Toast.LENGTH_SHORT).show();
                    UserType = answer.get(0).toString();
                    if (UserType.equalsIgnoreCase("Student")) {
                        Toast.makeText(Login.this, "Logging you " + answer.get(0).toString() + " In ! ", Toast.LENGTH_SHORT).show();
                        String notificationMSG = "You have a new notification";
                        DBHandler dbHandler1 = new DBHandler(getApplicationContext());
                        List result = dbHandler1.GetMessagesLast();
                        Subject = result.get(0).toString();
                        Message = result.get(1).toString();
                        User = result.get(2).toString();
                        Log.d("Success",Subject + " " + Message);
                        //Toast.makeText(Login.this, "Subject: " + Subject + " Message: " + Message + " User: " + User, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),Student.class);
                        Intent intent2 = new Intent(getApplicationContext(),Message.class);
                        intent2.putExtra("Subject",Subject);
                        intent2.putExtra("Message",Message);
                        intent2.putExtra("User",User);


                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        PendingIntent pendingIntent = PendingIntent.getActivity(Login.this, 0, intent2, 0);
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("My notification")
                                .setContentText(notificationMSG)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)//Set the intent that will fire when the user taps the notification.setContentIntent(pendingIntent).setAutoCancel(true);NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);//notificationId is a unique int for each notification that you must definenotificationManager.notify(0, builder.build());
                                .setContentIntent(pendingIntent).setAutoCancel(true);

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Login.this);//notificationId is a unique int for each notification that you must definenotificationManager.notify(0, builder.build());
                        notificationManager.notify(1, builder.build());
                        startActivity(intent);
                    }
                    else if (UserType.equalsIgnoreCase("Teacher")){
                        Toast.makeText(Login.this, "Logging you " + answer.get(0).toString() + " In ! ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Teacher.class);
                        //String UserTeacher = answer.get(0).toString();
                        intent.putExtra("Teacher",LoginUserName.getText().toString());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description =getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);//Register the channel with the system; you can't change the importance//or other notification behaviors after this NotificationManager notificationManager =getSystemService(NotificationManager.class);notificationManager.createNotificationChannel(channel);}

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }


}