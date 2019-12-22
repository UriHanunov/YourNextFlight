package com.example.yournextflight;

        import android.app.DatePickerDialog;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class AllFlights extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "AddFlight";
    String  date;
    ListView listViewFlight;

    List<Flight> flightList;

    DatabaseReference DatabaseFlights;

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseFlights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                flightList.clear();

                for(DataSnapshot flightSnapshot : dataSnapshot.getChildren()){
                    Flight flight= flightSnapshot.getValue(Flight.class);

                    flightList.add(flight);
                }

                FlightList adapter = new FlightList(AllFlights.this, flightList);
                listViewFlight.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_flights);

        listViewFlight= (ListView) findViewById(R.id.listViewAllFlights);

        flightList= new ArrayList<>();

        DatabaseFlights = FirebaseDatabase.getInstance().getReference("flights");

    }

}
