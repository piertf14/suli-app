package com.suli.suliapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.suli.suliapp.R;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.squareup.picasso.Picasso;
import com.suli.suliapp.R;
import com.suli.suliapp.data.models.CustodyChain;
import com.suli.suliapp.data.models.post.request.MeasurementValueRequest;
import com.suli.suliapp.data.models.post.response.CustodyChainResponse;
import com.suli.suliapp.data.models.post.response.MeasurementValueResponse;
import com.suli.suliapp.ui.callbacks.PostMeasurementValueCallback;
import com.suli.suliapp.ui.utils.Utils;

import java.util.ArrayList;

import br.com.packapps.retropicker.callback.CallbackPicker;
import br.com.packapps.retropicker.config.Retropicker;

public class AddMeasurementActivity extends AppCompatActivity {
    public static final String TAG = AddMeasurementActivity.class.getSimpleName();
    public static final String CUSTODY_CHAIN = "custodyChain";
    public static final int FILE_REQUEST_CODE = 10000;

    private EditText etMax;
    private EditText etMin;
    private EditText etAvg;
    private EditText etReferencePoint;
    private EditText etObservation;
    private EditText etTypeLighting;
    private Button btnAddImage;
    private Button btnAddValues;
    private ImageView ivPreview;


    private TextInputLayout tilMax;
    private TextInputLayout tilMin;
    private TextInputLayout tilAvg;
    private TextInputLayout tilReferencePoint;
    private TextInputLayout tilObservation;
    private TextInputLayout tilTypeLighting;

    private String imagePath;

    private Utils utils;

    private CustodyChainResponse custodyChain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);
        etMax = findViewById(R.id.etMax);
        etMin = findViewById(R.id.etMin);
        etAvg = findViewById(R.id.etAvg);
        etReferencePoint = findViewById(R.id.etReferencePoint);
        etObservation = findViewById(R.id.etObservation);
        etReferencePoint = findViewById(R.id.etReferencePoint);
        etTypeLighting = findViewById(R.id.etTypeLighting);
        btnAddImage = findViewById(R.id.btnAddImage);
        ivPreview = findViewById(R.id.ivPreview);
        btnAddValues = findViewById(R.id.btnAddValues);


        tilMax = findViewById(R.id.tilMax);
        tilMin = findViewById(R.id.tilMin);
        tilAvg = findViewById(R.id.tilAvg);
        tilReferencePoint = findViewById(R.id.tilReferencePoint);
        tilObservation = findViewById(R.id.tilObservation);
        tilReferencePoint = findViewById(R.id.tilReferencePoint);
        tilTypeLighting = findViewById(R.id.tilTypeLighting);

        setupToolbar((Toolbar) findViewById(R.id.toolbar));



        utils = new Utils(this);
        custodyChain = (CustodyChainResponse) getIntent().getExtras().getSerializable(CUSTODY_CHAIN);


        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(AddMeasurementActivity.this, FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                        .setShowImages(true)
                        .enableImageCapture(true)
                        .setMaxSelection(1)
                        .setSkipZeroSizeFiles(true)
                        .build());
                startActivityForResult(intent, FILE_REQUEST_CODE);*/
                Retropicker.Builder builder = new Retropicker.Builder(AddMeasurementActivity.this)
                        .setTypeAction(Retropicker.CAMERA_PICKER) //Para abrir a galeria passe Retropicker.GALLERY_PICKER
                        .setImageName("first_image.jpg") //Opicional
                        .checkPermission(true);

                builder.enquee(new CallbackPicker() {
                    @Override
                    public void onSuccess(Bitmap bitmap, String imagePath) {
                        AddMeasurementActivity.this.imagePath = imagePath;
                        ivPreview.setImageBitmap(bitmap); //ImageView do seu aplicativo onde quer exibir a imagem final
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        Log.e("TAG", "error: " + error.getMessage());
                        Log.e("TAG", "error toString: " + error.toString());
                    }
                });

                Retropicker retropicker = builder.create();
                retropicker.open();

            }


        });

        btnAddValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add(custodyChain);

            }
        });
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void Add(CustodyChainResponse custodyChain) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Agregando Evaluación");
        progressDialog.show();


        String max = etMax.getText().toString().trim();
        String min = etMin.getText().toString().trim();
        String avg = etAvg.getText().toString().trim();
        String referencePoint = etReferencePoint.getText().toString().trim();
        String observation = etObservation.getText().toString().trim();
        String typeLigthing = etTypeLighting.getText().toString().trim();

        Integer errors = 0;
        if (max.isEmpty()) {
            errors++;
            tilMax.setError("Ingrese un valor válido");
        }
        if (min.isEmpty()) {
            errors++;
            tilMin.setError("Ingrese un valor válido");
        }
        if (avg.isEmpty()) {
            errors++;
            tilAvg.setError("Ingrese un valor válido");
        }
        if (referencePoint.isEmpty()) {
            errors++;
            tilReferencePoint.setError("Ingrese un valor válido");
        }
        if (observation.isEmpty()) {
            errors++;
            tilObservation.setError("Ingrese un valor válido");
        }
        if (typeLigthing.isEmpty()) {
            errors++;
            tilTypeLighting.setError("Ingrese un valor válido");
        }
        if (imagePath.isEmpty()) {
            errors++;
            Toast.makeText(this, "Agregue una imagen", Toast.LENGTH_SHORT).show();
        }


        if (errors == 0) {
            MeasurementValueRequest request = new MeasurementValueRequest(
                    custodyChain.id, max, min, avg, referencePoint, observation, typeLigthing
            );


            utils.postMeasurementValue(new PostMeasurementValueCallback() {
                @Override
                public void onSuccess(MeasurementValueResponse measurementValueResponse) {
                    Toast.makeText(AddMeasurementActivity.this, "Evaluación agregada correctamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }

                @Override
                public void onFailure(String error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddMeasurementActivity.this, error, Toast.LENGTH_SHORT).show();

                }
            }, request, imagePath);
        } else {
            Toast.makeText(this, "Verifica los datos ingresados", Toast.LENGTH_SHORT).show();
        }


    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "onActivityResult: requestCode: "+requestCode+" - resultCode: "+resultCode);
        switch (requestCode){
            case FILE_REQUEST_CODE:
                ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
                Log.e(TAG, "onActivityResult: "+files);
                if(!files.isEmpty()){
                    //mediaFile = files.get(0);
                    //Picasso.with(this).load(mediaFile.getThumbnail()).into(ivPreview);
                }
                break;
        }
    }*/
}
