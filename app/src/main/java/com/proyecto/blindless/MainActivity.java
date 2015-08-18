package com.proyecto.blindless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.JavaCameraView;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    /* Referencia a nuestro control "org.opencv.android.NativeCameraView" */
    //private CameraBridgeViewBase mOpenCvCameraView;

    private JavaCameraView mOpenCvJavaCameraView;

    protected static final String TAG = "BLindLess";

    private BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    //mOpenCvCameraView.enableView();
                    mOpenCvJavaCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Al cargar la actividad cargamos el valor de nuestra superficie de trabajo */
        //mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.activity_main_surface_view);
        //mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        //mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvJavaCameraView = (JavaCameraView) findViewById(R.id.MainActivityCameraView);
        mOpenCvJavaCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvJavaCameraView.setCvCameraViewListener(this);
        mOpenCvJavaCameraView.enableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_6, this, mLoaderCallback);
        OpenCVLoader.initDebug();

    }

    /*@Override
    public void onPause() {
        super.onPause();
        if (mOpenCvJavaCameraView != null)
            mOpenCvJavaCameraView.disableView();
    }*/

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvJavaCameraView != null)
            mOpenCvJavaCameraView.disableView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return inputFrame.rgba();

    }

}
