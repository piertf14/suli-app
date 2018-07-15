package com.suli.suliapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.suli.suliapp.R;
import com.suli.suliapp.data.models.MiningUnit;
import com.suli.suliapp.data.models.ProjectResponse;
import com.suli.suliapp.data.models.post.request.CustodyChainRequest;
import com.suli.suliapp.data.models.post.response.AgentResponse;
import com.suli.suliapp.data.models.post.response.CustodyChainResponse;
import com.suli.suliapp.data.models.post.response.InstrumentResponse;
import com.suli.suliapp.ui.callbacks.GetAgentsCallback;
import com.suli.suliapp.ui.callbacks.GetInstrumentsCallback;
import com.suli.suliapp.ui.callbacks.PostCustodyChainCallback;
import com.suli.suliapp.ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddCustodyChainActivity extends AppCompatActivity {
    public static final String PROYECTO = "proyecto";
    public static final String UNITY = "unity";


    private EditText etProject;
    private EditText etUnity;
    private EditText etJobPosition;
    private EditText etArea;
    private EditText etStartHour;
    private EditText etEndHour;
    private EditText etWorkerName;
    private EditText etWorkerLastName;
    private EditText etDescription;
    private EditText etDate;

    private TextInputLayout tilProject;
    private TextInputLayout tilUnity;
    private TextInputLayout tilJobPosition;
    private TextInputLayout tilArea;
    private TextInputLayout tilStartHour;
    private TextInputLayout tilEndHour;
    private TextInputLayout tilWorkerName;
    private TextInputLayout tilWorkerLastName;
    private TextInputLayout tilDescription;
    private TextInputLayout tilDate;

    private Spinner spAgents;
    private Spinner spInstruments;


    private Button btnAddCustodyChain;
    private Utils utils;

    private List<AgentResponse> agents = new ArrayList<>();
    private List<InstrumentResponse> instruments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custody_chain);
        etProject = findViewById(R.id.etProjet);
        etUnity = findViewById(R.id.etUnity);
        etJobPosition = findViewById(R.id.etJobPosition);
        etArea = findViewById(R.id.etArea);
        etStartHour = findViewById(R.id.etStartHour);
        etEndHour = findViewById(R.id.etEndHour);
        etWorkerName = findViewById(R.id.etWorkerName);
        etWorkerLastName = findViewById(R.id.etWorkerLastName);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);


        tilJobPosition = findViewById(R.id.tilJobPosition);
        tilArea = findViewById(R.id.tilArea);
        tilStartHour = findViewById(R.id.tilStartHour);
        tilEndHour = findViewById(R.id.tilEndHour);
        tilWorkerName = findViewById(R.id.tilWorkerName);
        tilWorkerLastName = findViewById(R.id.tilWorkerLastName);
        tilDescription = findViewById(R.id.tilDescription);
        tilDate = findViewById(R.id.tilDate);


        spAgents = findViewById(R.id.spAgents);
        spInstruments = findViewById(R.id.spInstruments);
        btnAddCustodyChain = findViewById(R.id.btnAddCustodyChain);
        setupToolbar((Toolbar) findViewById(R.id.toolbar));


        utils = new Utils(this);
        final ProjectResponse project = (ProjectResponse) getIntent().getExtras().getSerializable(PROYECTO);
        final MiningUnit unity = (MiningUnit) getIntent().getExtras().getSerializable(UNITY);
        if (project != null) {
            etProject.setText(project.observations);
        }
        if (unity != null) {
            etUnity.setText(unity.name);
        }
        getValues();
        btnAddCustodyChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agents.isEmpty() || instruments.isEmpty()) {
                    Toast.makeText(AddCustodyChainActivity.this, "Aún no se han descargado las listas de agentes o instrumentos", Toast.LENGTH_SHORT).show();
                } else {
                    addCustodyChain(project, unity);
                }
            }
        });
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getValues() {
        utils.getAgents(new GetAgentsCallback() {
            @Override
            public void onSuccess(List<AgentResponse> agents) {
                AddCustodyChainActivity.this.agents = agents;
                AddCustodyChainActivity.this.agents.add(0, new AgentResponse("Seleccionar"));
                ArrayAdapter<AgentResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.check_item, agents);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAgents.setAdapter(adapter);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(AddCustodyChainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        utils.getInstruments(new GetInstrumentsCallback() {
            @Override
            public void onSuccess(List<InstrumentResponse> instruments) {
                AddCustodyChainActivity.this.instruments = instruments;
                AddCustodyChainActivity.this.instruments.add(0, new InstrumentResponse("Seleccionar"));
                ArrayAdapter<InstrumentResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.check_item, instruments);
                adapter.setDropDownViewResource(R.layout.check_item);
                spInstruments.setAdapter(adapter);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(AddCustodyChainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addCustodyChain(ProjectResponse project, MiningUnit miningUnit) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Agregando Cadena de Custodia");
        progressDialog.show();


        tilWorkerName.setError(null);
        tilWorkerLastName.setError(null);
        tilJobPosition.setError(null);
        tilArea.setError(null);
        tilDescription.setError(null);
        tilStartHour.setError(null);
        tilEndHour.setError(null);
        tilDate.setError(null);


        Integer errors = 0;
        String name = etWorkerName.getText().toString().trim();
        String lastName = etWorkerLastName.getText().toString().trim();
        String jobPosition = etJobPosition.getText().toString().trim();
        String area = etArea.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String start = etStartHour.getText().toString().trim();
        String end = etEndHour.getText().toString().trim();


        Integer startHour = Integer.parseInt(start.isEmpty() ? "0" : start);
        Integer endHour = Integer.parseInt(end.isEmpty() ? "0" : end);
        @Nullable AgentResponse agentR = (AgentResponse) spAgents.getSelectedItem();
        @Nullable InstrumentResponse instrumentR = (InstrumentResponse) spInstruments.getSelectedItem();

        Log.e("agent: ", "" + agentR.id);
        Log.e("instrument: ", "" + instrumentR.id);

        if (name.isEmpty()) {
            errors++;
            tilWorkerName.setError("Ingrese un nombre válido");
        }
        if (lastName.isEmpty()) {
            errors++;
            tilWorkerLastName.setError("Ingrese un apellido válido");
        }
        if (jobPosition.isEmpty()) {
            errors++;
            tilJobPosition.setError("Ingrese un puesto válido");
        }
        if (area.isEmpty()) {
            errors++;
            tilArea.setError("Ingrese una área válida");
        }
        if (description.isEmpty()) {
            errors++;
            tilDescription.setError("Ingrese una descripción válida");
        }
        if (date.isEmpty()) {
            errors++;
            tilDate.setError("Ingrese una fecha válida");
        }
        if (startHour == 0) {
            errors++;
            tilStartHour.setError("Ingrese una hora de inicio válida");
        }
        if (endHour == 0) {
            errors++;
            tilEndHour.setError("Ingrese una hora de fin válida");
        }
        try {
            if (agentR.id == 0) {
                errors++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            errors++;
        }
        try {
            if (instrumentR.id == 0) {
                errors++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            errors++;
        }
        Log.e("sdasdas", "sdasd"+errors);

        if (errors == 0) {
            final CustodyChainRequest custodyChain = new CustodyChainRequest(name, lastName, jobPosition, project.id, area, agentR.getId(), instrumentR.getId(), date, description, startHour, endHour, true);
            utils.postCustodyChain(new PostCustodyChainCallback() {
                @Override
                public void onSuccess(CustodyChainResponse custodyChainResponse) {
                    Intent intent = new Intent(AddCustodyChainActivity.this, AddMeasurementActivity.class);
                    intent.putExtra(AddMeasurementActivity.CUSTODY_CHAIN, custodyChainResponse);
                    startActivity(intent);
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(String error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddCustodyChainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }, custodyChain);
        } else {
            progressDialog.dismiss();
            Toast.makeText(AddCustodyChainActivity.this, "Comprueba que la información ingresada sea correcta", Toast.LENGTH_SHORT).show();
        }
    }

}
