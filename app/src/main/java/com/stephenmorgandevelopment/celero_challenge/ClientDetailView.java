package com.stephenmorgandevelopment.celero_challenge;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stephenmorgandevelopment.celero_challenge.database.WorkDatabase;
import com.stephenmorgandevelopment.celero_challenge.utils.SimpleImageLoader;

import java.net.URL;

public class ClientDetailView extends AppCompatActivity {
    public static final String TAG = ClientDetailView.class.getSimpleName();

    private TextView clientsName;
    private TextView addressLineOne;
    private TextView addressLineTwo;
    private TextView openInMaps;
    private TextView phoneNumber;
    private TextView serviceReason;
    private ImageView profilePicture;
    private LinearLayout problemsScroller;

    public static final String IDENTIFIER_TAG = "identifier_extra";
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_detail_view);

        clientsName = findViewById(R.id.clients_name);
        addressLineOne = findViewById(R.id.address_line_one);
        addressLineTwo = findViewById(R.id.address_line_two);
        openInMaps = findViewById(R.id.open_in_maps);
        phoneNumber = findViewById(R.id.phone_number);
        serviceReason = findViewById(R.id.service_reason);
        profilePicture = findViewById(R.id.profile_picture);
        problemsScroller = findViewById(R.id.problems_scroller);

        long identifier = -1;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            identifier = extras.getLong(IDENTIFIER_TAG);
        } else {
            Log.d(TAG, "No identifier sent with intent.");
            ClientDetailView.this.finish();
        }

        // I know I should have moved this cursor over to the WorkDatabase class and
        // built out the client model from there, but I was limited on the time
        // I had to work on this project, unfortunately.

        Cursor cursor = WorkDatabase.getInstance().getClientByIdentifier(identifier);
        if(cursor.moveToFirst()) {
            clientsName.setText(cursor.getString(2));
            addressLineOne.setText(cursor.getString(7));
            String cityStateZip = cursor.getString(8) + ", "
                + cursor.getString(9) + ", "  + cursor.getString(10);
            addressLineTwo.setText(cityStateZip);
            phoneNumber.setText(cursor.getString(3));
            serviceReason.setText(cursor.getString(14));

            latitude = cursor.getLong(12);
            longitude = cursor.getLong(13);

            // I wanted to use RxJava to load these images.  I know that my solution is not
            // optimal.  Again, this was faster for me to finish in the time allotted.

            new SimpleImageLoader(ClientDetailView.this, profilePicture, cursor.getString(4)).start();

            String[] problemPics = cursor.getString(15).split(" ");
//            ViewGroup.LayoutParams ivParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ViewGroup.LayoutParams ivParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            for(String pic : problemPics) {
                if (pic != null) {
                    ImageView imageView = new ImageView(ClientDetailView.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setLayoutParams(ivParams);
                    problemsScroller.addView(imageView);
                    new SimpleImageLoader(ClientDetailView.this, imageView, pic.trim()).start();
                }
            }
        }

        openInMaps.setOnClickListener(v -> {
            //TODO Launch the clients address in Maps.

        });
    }



}
