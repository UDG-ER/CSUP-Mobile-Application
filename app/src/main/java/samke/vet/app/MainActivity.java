package samke.vet.app;


import android.location.Location;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class  MainActivity extends AppCompatActivity {

    List<List<String>> allTypesList;

    private List<VeterinarskaKlinika> allVKlist;
    private List<VeterinarskaKlinika> veterinarskaKlinikaList;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;

    private VeterinarskaKlinikaAdapter mAdapter;



    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocation();

        this.veterinarskaKlinikaList = new ArrayList<>();
        this.allTypesList = new ArrayList<>();

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new VeterinarskaKlinikaAdapter(veterinarskaKlinikaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);


        prepareData();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLocation();
                prepareData();

                Collections.sort(veterinarskaKlinikaList, byDistance);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    public void prepareData() {
        if (this.veterinarskaKlinikaList.size() > 0) {
            this.veterinarskaKlinikaList.clear();
        }
        if (this.allTypesList.size() > 0) {
            this.allTypesList.clear();
        }
        Location currentLocation = new Location("");
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);


        //-----------------------------MONTVET------------------------//

        List<String> descriptionMontvet = new ArrayList<>();
        descriptionMontvet.add("tel - 020 662 578");
        descriptionMontvet.add("Samo za hitne slučajeve: 069 190 488");
        List<String> typeMontvet = new ArrayList<>();
        typeMontvet.add("Veterinarski program");
        typeMontvet.add("Hrana za mačke");
        typeMontvet.add("Hrana za pse");

        allTypesList.add(typeMontvet);

        Location vkMontvetLoc = new Location("");

        vkMontvetLoc.setLatitude(42.433158);
        vkMontvetLoc.setLongitude(19.243721);

        VeterinarskaKlinika vkMontvet = new VeterinarskaKlinika("PVU MONTVET", "ul. Ilije Plamenca Lamela 103 bb (preko puta Hemomonta)", "2131099754", 42.433158, 19.243721, currentLocation.distanceTo(vkMontvetLoc), descriptionMontvet, typeMontvet);
        veterinarskaKlinikaList.add(vkMontvet);


        //-----------------------------IBRIČEVINA------------------------//

        List<String> descriptionIbricevina = new ArrayList<>();
        descriptionIbricevina.add("tel - 020 645 300");
        List<String> typeIbricevina = new ArrayList<>();
        typeIbricevina.add("Klinički pregledi i terapije");
        typeIbricevina.add("Vakcinacije i aplikacija ljekova");
        typeIbricevina.add("Hirurgija i porodiljstvo");
        typeIbricevina.add("Dijagnostička ispitivanja");

        allTypesList.add(typeMontvet);

        Location vkIbricevinaLoc = new Location("");

        vkIbricevinaLoc.setLatitude(42.442666);
        vkIbricevinaLoc.setLongitude(19.277451);

        VeterinarskaKlinika vkIbricevina = new VeterinarskaKlinika("Veterinarska ambulanta Ibričevina", "5 Petra Lubarde, Podgorica", "2131099752", 42.442666, 19.277451, currentLocation.distanceTo(vkIbricevinaLoc), descriptionIbricevina, typeIbricevina);
        veterinarskaKlinikaList.add(vkIbricevina);


        //----------------------------- K9 Vet------------------------//

        List<String> descriptionK9Vet = new ArrayList<>();
        descriptionK9Vet.add("tel - 020 223 932");
        List<String> typeK9Vet = new ArrayList<>();
        typeK9Vet.add("Pregledi i liječenje");
        typeK9Vet.add("Vakcinacije i aplikacija ljekova");
        allTypesList.add(typeMontvet);

        Location vkK9VetLoc = new Location("");

        vkK9VetLoc.setLatitude(42.442666);
        vkK9VetLoc.setLongitude(19.229214);

        VeterinarskaKlinika vkK9Vet = new VeterinarskaKlinika("K9 Vet", "Cetinjski Put bb", "2131099770", 42.442666, 19.229214, currentLocation.distanceTo(vkK9VetLoc), descriptionK9Vet, typeK9Vet);
        veterinarskaKlinikaList.add(vkK9Vet);


        //-----------------------------Animavet------------------------//
        List<String> descriptionAnimavet = new ArrayList<>();
        descriptionAnimavet.add("tel - 020 290 296");
        List<String> typeAnimavet = new ArrayList<>();
        typeAnimavet.add("Pregledi i liječenje");
        typeAnimavet.add("Vakcinacije i aplikacija ljekova");
        typeAnimavet.add("Terenske usluge liječenja domaćih životinja");
        allTypesList.add(typeAnimavet);

        Location vkAnimavetLoc = new Location("");

        vkAnimavetLoc.setLatitude(42.436419);
        vkAnimavetLoc.setLongitude(19.238483);

        VeterinarskaKlinika vkAnimavet = new VeterinarskaKlinika("Animavet", "Marka Radovića, 18", "2131099753", 42.436419, 19.238483, currentLocation.distanceTo(vkAnimavetLoc), descriptionAnimavet, typeAnimavet);
        veterinarskaKlinikaList.add(vkAnimavet);

        //-----------------------------Royal vet------------------------//
        List<String> descriptionRoyalVet = new ArrayList<>();
        descriptionRoyalVet.add("tel - 020 675 296");
        List<String> typeRoyalVet = new ArrayList<>();
        typeRoyalVet.add("Pregledi i liječenje");
        typeRoyalVet.add("Dermatologija");
        typeRoyalVet.add("Oftalmologija");
        typeRoyalVet.add("Hirurgija");
        typeRoyalVet.add("Stomatologija");
        typeRoyalVet.add("Urologija");
        allTypesList.add(typeRoyalVet);

        Location vkRoyalVetLoc = new Location("");

        vkRoyalVetLoc.setLatitude(42.452342);
        vkRoyalVetLoc.setLongitude(19.239175);

        VeterinarskaKlinika vkRoyalVet = new VeterinarskaKlinika("Royal Vet", "Đoka Miraševića bb", "2131099767", 42.452342, 19.239175, currentLocation.distanceTo(vkRoyalVetLoc), descriptionRoyalVet, typeRoyalVet);
        veterinarskaKlinikaList.add(vkRoyalVet);




        Collections.sort(veterinarskaKlinikaList, byDistance);


        allVKlist = new ArrayList<>(veterinarskaKlinikaList);
        //Osvježi adapter
        mAdapter.notifyDataSetChanged();
    }

    public void getLocation() {
        gpsTracker = new GpsTracker(MainActivity.this);
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

    Comparator<VeterinarskaKlinika> byDistance = new Comparator<VeterinarskaKlinika>() {
        @Override
        public int compare(VeterinarskaKlinika left, VeterinarskaKlinika right) {
            return Float.compare(left.getDistance(), right.getDistance());
        }
    };

}
