package com.namba.nambaspy1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import static android.app.Activity.RESULT_OK;

public class ReviewFullscreenDialogFragment extends DialogFragment {

    private RecyclerView rv;
    private ChoosenPhotoAdapter adapter;
    private List<Uri> choosenImages;

    private ImageButton addPhotoButton;

    public static final int RESULT_LOAD_IMG = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.full_screen_dialog, container, false);
         (rootView.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addPhotoButton =(rootView.findViewById(R.id.button_add_photo));
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });
        choosenImages = new ArrayList<>();
        rv = rootView.findViewById(R.id.choosen_photos_rv);
        adapter = new ChoosenPhotoAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv.setAdapter(adapter);
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    void getImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Log.e("Dialog", "its ok");
            final Uri imageUri = data.getData();
            choosenImages.add(imageUri);
            updateUI();
            adapter.notifyDataSetChanged();

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    void updateUI() {
        if (choosenImages.size() >= 4) {
            addPhotoButton.setVisibility(View.GONE);
        } else {
            addPhotoButton.setVisibility(View.VISIBLE);
        }
    }


    class ChoosenPhotoAdapter extends RecyclerView.Adapter<ChoosenPhotoAdapter.MyViewHolder> {

        LayoutInflater inflater;

        public ChoosenPhotoAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.choosen_photos_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Picasso.get()
                    .load(choosenImages.get(position))
                    .resize(72, 72)
                    .into(holder.mImage);
        }

        @Override
        public int getItemCount() {
            return choosenImages.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView deleteIcon;
            ImageView mImage;
            public MyViewHolder(View itemView) {
                super(itemView);
                deleteIcon = itemView.findViewById(R.id.deleteIcon);
                mImage = itemView.findViewById(R.id.choosenPhotoImageView);

                deleteIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Review fragment", getAdapterPosition() + " d");
                        choosenImages.remove(getAdapterPosition());
                        notifyDataSetChanged();
                        updateUI();
                    }
                });
            }
        }
    }
}

