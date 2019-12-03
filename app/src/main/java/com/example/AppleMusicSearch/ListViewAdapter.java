package com.example.AppleMusicSearch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.AppleMusicSearch.Itunes.Result;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Result> results;
    private Context context;
    private View finalView;


    public ListViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }


    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_detalle_lista2, viewGroup, false);
        }

        final ImageView imgFoto = (ImageView) view.findViewById(R.id.imgFoto);
        TextView tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        final Result thisresult = results.get(position);
        tvTitulo.setText(thisresult.getCollectionName());
        tvDescripcion.setText(thisresult.getArtistName());

        if (thisresult.getArtworkUrl100() != null && thisresult.getArtworkUrl100().length() > 0) {
           Picasso.get().load(thisresult.getArtworkUrl100()).placeholder(R.drawable.placeholder).into(imgFoto);


        } else {
            Toast.makeText(context, "sin imagen", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.placeholder).into(imgFoto);
        }
        //
        final View finalView = view;
        final View finalView1 = view;
        view.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final View vista = LayoutInflater.from(context).inflate(R.layout.imagen, viewGroup, false);
                final ImageView imageView = (ImageView) vista.findViewById(R.id.imageView);
                Picasso.get().load(thisresult.getArtworkUrl100()).placeholder(R.drawable.placeholder).into(imageView);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setView(vista);
                TextView txtTitulo = (TextView) vista.findViewById(R.id.txtTitulo);
                txtTitulo.setText (thisresult.getTrackName());
                VideoView videoView = (VideoView) vista.findViewById(R.id.vidMain);
                videoView.setVideoPath(thisresult.getPreviewUrl());
                MediaController mediaController = new MediaController(context);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
                //
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();

            }

        });
        //

        return finalView;
    }



}


