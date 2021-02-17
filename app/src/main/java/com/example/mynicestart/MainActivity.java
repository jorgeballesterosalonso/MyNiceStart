package com.example.mynicestart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alespero.expandablecardview.ExpandableCardView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeLayout = null;
    TextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);
        ImageView fondo = findViewById(R.id.imageView2);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1606276969080-bf446209054a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80")
                .transition(DrawableTransitionOptions.withCrossFade(500))
                //.placeholder(new ColorDrawable(this.getResources()
                //.getColor(R.color.design_default_color_background)))
                .circleCrop()
                //.getDiskCacheStrategy(DiskCacheStrategy.NONE)
                .into(fondo);

        ImageView myContext = findViewById(R.id.imageView2);
        registerForContextMenu(myContext);

        tv = findViewById(R.id.tvMain);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

// *** EXPANDABLE CARD ***
        ExpandableCardView card = findViewById(R.id.profilee);
        card.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(MainActivity.this, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // *** SWIPEREFRESH con SNACKBAR ***
    //    construimos el Swipe y aplicamos un Listener que lanza un SnackBar y desactiva a continuación del Swipe la animación

    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {

        @Override
        public void onRefresh() {


            //    opción TOAST
            //
            //          Toast toast0 = Toast.makeText(MainContextActivity.this, "going swipeREFRESH", Toast.LENGTH_LONG);
            //          toast0.show();
            //   opción SNACKBAR
//           SUSTITUIR POR CONSTRAINT EN SU CASO
            final ConstraintLayout mLayout = (ConstraintLayout) findViewById(R.id.activityMainLayout);
//                final RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.activity_main_context);

            Snackbar snackbar = Snackbar
                    .make(mLayout, "fancy a Snack while you refresh?", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            Snackbar snackbar1 = Snackbar.make(mLayout, "Action is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();
            swipeLayout.setRefreshing(false);
            showAlertDialogButtonClicked(MainActivity.this);
        }
    };


    // *** MENU CONTEXTUAL ***
    //    inflamos el menu contextual con los items del menu xml correspondiente
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);

    }

    //    creamos una lista de eventos para los items del menus contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.camera:
                Toast toast = Toast.makeText(this, "going CONTEXT CAMERA", Toast.LENGTH_LONG);
                toast.show();
                //Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                //startActivity(intent);
                break;
            case R.id.action_settings:
                Toast toast2 = Toast.makeText(this, "going CONTEXT SETTINGS", Toast.LENGTH_LONG);
                toast2.show();
                //Intent intent2 = new Intent(MainActivity.this, AnyChartActivity.class);
                //startActivity(intent2);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.camera) {
            Toast toast = Toast.makeText(this, "going APPBAR CAMERA", Toast.LENGTH_LONG);
            toast.show();
        }
        return super.onOptionsItemSelected(item);
    }

    // *** DIALOGO MODAL ***
    public void showAlertDialogButtonClicked(MainActivity view) {
        // setup the alertbuilder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//el dialogo estandar tiene título/icono pero podemos sustituirlo por un XML a medida
        builder.setTitle("Cuidado!");
        builder.setMessage("Estás refrescando y todos los cambios se restaurarán");
        //        builder.setIcon(R.drawable.ic_action_name_dark);
        // un XML a medida para el diálogo
        builder.setView(getLayoutInflater().inflate(R.layout.alertdialog_view, null));

        // add the buttons
        builder.setPositiveButton("Postivo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                dialog.dismiss();
                tv.setText("Has aceptado el popUp");
            }
        });
        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// do something like...
                dialog.dismiss();
                tv.setText("Has rechazado el popUp");
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// do something like...
                dialog.dismiss();
                tv.setText("Has pulsado el botón neutral del popUp");
            }
        });// create and show the alertdialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


