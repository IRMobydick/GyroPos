package cenk.gun.gyropos;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textX, textY, textZ;
    TextView tv_accX, tv_accY, tv_accZ;
    SensorManager gyroManager, accManager;
    Sensor gyroSensor, accSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = gyroManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        accManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accSensor = accManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);

        tv_accX = (TextView) findViewById(R.id.accX);
        tv_accY = (TextView)findViewById(R.id.accY);
        tv_accZ = (TextView) findViewById(R.id.accZ);


    }

    public void onResume() {
        super.onResume();
        gyroManager.registerListener(gyroListener, gyroSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        accManager.registerListener(accListener, accSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        gyroManager.unregisterListener(gyroListener);
        accManager.unregisterListener(accListener);

    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("X : " + x + " rad/s");
            textY.setText("Y : " + y + " rad/s");
            textZ.setText("Z : " + z + " rad/s");
        }
    };

    public SensorEventListener accListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            tv_accX.setText("X Acc : " + x + " m/s2");
            tv_accY.setText("Y Acc: " + y + " m/s2");
            tv_accZ.setText("Z Acc: " + z + " m/s2");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}