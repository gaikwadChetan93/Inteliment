package com.inteliment.intelimentviewwizard.scenario2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inteliment.intelimentviewwizard.IntelimentApplication;
import com.inteliment.intelimentviewwizard.R;
import com.inteliment.intelimentviewwizard.interfaces.LocationApiInterface;
import com.inteliment.intelimentviewwizard.models.LocationInfo;
import com.inteliment.intelimentviewwizard.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chetan on 23/11/17.
 */
public class NavigationActivity extends AppCompatActivity implements NavigationContract
        ,AdapterView.OnItemSelectedListener{

    @Inject LocationApiInterface locationApiInterface;
    @BindView(R.id.tvTrain) TextView tvTrain;
    @BindView(R.id.tvCar) TextView tvCar;
    @BindView(R.id.spinnerLocation) Spinner spinnerLocation;

    private NavigationPresenter navigationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        ((IntelimentApplication) getApplication())
                .getmLocationComponent().inject(this);
        navigationPresenter = new NavigationPresenter(this, locationApiInterface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.isConnectedToInternet(this)) {
            navigationPresenter.getLocationData();
        } else {
            Toast.makeText(this, "Not connected to internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showData(List<LocationInfo> locations) {

        spinnerLocation.setAdapter(new LocationSpinnerAdapter(this, locations));
        spinnerLocation.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        LocationInfo locationInfo = (LocationInfo) parent.getItemAtPosition(position);
        showSelectedData(locationInfo);
    }

    /**
     * Resfresh the screen to display the proper data as per the location selected
     * @param locationInfo
     */
    private void showSelectedData(LocationInfo locationInfo) {
        if (locationInfo == null) {
            return;
        }
        String car = locationInfo.getFromCentral().getCar();
        String train = locationInfo.getFromCentral().getTrain();

        if (car != null && !car.isEmpty()) {
            tvCar.setVisibility(View.VISIBLE);
            tvCar.setText(getString(R.string.msg_car, car));
        } else {
            tvCar.setVisibility(View.GONE);
        }

        if (train != null && !train.isEmpty()) {
            tvTrain.setVisibility(View.VISIBLE);
            tvTrain.setText(getString(R.string.msg_train, train));
        } else {
            tvTrain.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        LocationInfo locationInfo = (LocationInfo) parent.getItemAtPosition(0);
        showSelectedData(locationInfo);
    }

    /**
     * Launch the maps application on tap of the navigate button for the selected options.
     */
    @OnClick(R.id.btnNavigate)
    public void onNavigateClicked() {

        LocationInfo selectedItem = (LocationInfo) spinnerLocation.getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        String uri = getString(R.string.direction_uri, selectedItem.getLocation().getLatitude(),
                selectedItem.getLocation().getLongitude());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, R.string.error_no_maps, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigationPresenter.removeView();
    }
}
