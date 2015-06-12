package com.example.nolasco.mysensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class celsensores extends ActionBarActivity implements SensorEventListener {
    private TextView Salir;
    private Sensor sensor;
    private int accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celsensores);

        Salir = (TextView) findViewById(R.id.Salir);

        SensorManager SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listasensores = SensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : listasensores) {
            log(sensor.getName());
        }
    }
//metodo para iniciar los sensores

    public void inicioSensores() {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Sensor accelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);

        if (!listaSensores.isEmpty()) {
            Sensor giroscopioSensor = listaSensores.get(0);
            sensorManager.registerListener(this, giroscopioSensor, SensorManager.SENSOR_DELAY_UI);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);

        if (!listaSensores.isEmpty()) {
            Sensor temperatureSensor = listaSensores.get(0);
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    //metodo para detener el funcionamiento de los sensores
    public void pararSensores() {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if (!listaSensores.isEmpty()) {
            Sensor orientatioSensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(orientatioSensor.TYPE_ORIENTATION));
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Sensor accelerometerSensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(accelerometerSensor.TYPE_ACCELEROMETER));
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        if (!listaSensores.isEmpty()) {
            Sensor giroscopioSensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(giroscopioSensor.TYPE_GYROSCOPE));
        }
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
        if (!listaSensores.isEmpty()) {
            Sensor temperatureSensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(temperatureSensor.TYPE_TEMPERATURE));
        }
    }


    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:

                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    log("Acelerometro X:" + event.values[0]);
                    log("Acelerometro Y:" + event.values[1]);
                    log("Acelerometro Z:" + event.values[2]);
                    break;

                case Sensor.TYPE_GYROSCOPE:
                    log("Eje X:" + event.values[0]);
                    log("Eje Y:" + event.values[1]);
                    log("Eje z:" + event.values[2]);
                    break;
                default:

                    for (int i = 0;<event.values.length; i++){
                    log("temperatura" + i + ": " + event.values[i]);


                }

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void log(String string) {
        Salir.append(string + "\n");
    }

    private void nuevo() {
        Salir.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_celsensores, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.inicio:
                inicioSensores();
                return true;
            case R.id.parar:
                pararSensores();
                return true;
            case R.id.nuevo:
                nuevo();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

        }


}
