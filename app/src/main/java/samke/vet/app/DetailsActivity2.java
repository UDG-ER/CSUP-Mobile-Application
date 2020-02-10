package samke.vet.app;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class  DetailsActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private VeterinarskaKlinika vk;
    private TextView nameDetails;
    private TextView addressDetails;
    private TextView typeDetails;
    private ImageView imageDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        vk = (VeterinarskaKlinika) getIntent().getExtras().get("vk");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        init();
    }

    private void init() {
        nameDetails = findViewById(R.id.nameDetails);
        addressDetails = findViewById(R.id.addressDetails);

        nameDetails.setText(vk.getName());
        addressDetails.setText(vk.getAddress());

        typeDetails = findViewById(R.id.typeDetails);
        typeDetails.setText(vk.getTypesAsString(true));

        imageDetails = findViewById(R.id.image_details);
        imageDetails.setImageResource(Integer.parseInt(vk.getPicture()));
    }

    public void openDetails(View v) {
        final AlertDialog dialog;

        final View alertDialogView = LayoutInflater.from(DetailsActivity2.this).inflate
                (R.layout.dialog, null);

        dialog = new AlertDialog.Builder(DetailsActivity2.this)
                .setView(alertDialogView)
                .create();
        dialog.show();

        TextView phone1 = alertDialogView.findViewById(R.id.phone1);
        TextView phone2 = alertDialogView.findViewById(R.id.phone2);
        TextView phone3 = alertDialogView.findViewById(R.id.phone3);
        TextView phone4 = alertDialogView.findViewById(R.id.phone4);
        TextView phone5 = alertDialogView.findViewById(R.id.phone5);
        TextView phone6 = alertDialogView.findViewById(R.id.phone6);

        TextView[] phones = {phone1, phone2, phone3, phone4, phone5, phone6};
        List<String> description = vk.getDescription();
        for (int i = 0; i < description.size(); i++) {
            final String phone = description.get(i);
            phones[i].setText(phone);
            phones[i].setVisibility(View.VISIBLE);
            phones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phoneNumber = phone.substring(0, phone.indexOf("–") - 1);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng loc = new LatLng(vk.getLat(), vk.getLng());
        mMap.addMarker(new MarkerOptions().position(loc).title(vk.getName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(vk.getLat(), vk.getLng()), 15.0f));
    }

}
