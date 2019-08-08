package myresume.diego.managerdnol.com.myresume.resource.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import myresume.diego.managerdnol.com.myresume.R;
import myresume.diego.managerdnol.com.myresume.resource.util.listener.ParseLoadListener;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Perfil;
import myresume.diego.managerdnol.com.myresume.resource.util.asynctask.InfoLoadingTask;

public class ActivityPerfil extends AppCompatActivity implements View.OnClickListener {

    private final int GALLERY_REQUEST_CODE = 20;
    private Bitmap bitmapFoto;
    private ImageView fotoPerfil;
    private InfoLoadingTask asyncTask;
    private ParseLoadListener parseLoadListener;
    private TextView cargoText, direccionText, ciudadText, cpText, correoText, telText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        cargoText = findViewById(R.id.cargoTextView);
        direccionText = findViewById(R.id.direccionTextView);
        ciudadText = findViewById(R.id.ciudadTextView);
        cpText = findViewById(R.id.cpTextView);
        correoText = findViewById(R.id.correoTextView);
        telText = findViewById(R.id.telTextView);
        fotoPerfil = findViewById(R.id.fotoPerfil);

        fotoPerfil.setOnClickListener(this);

        asyncTask = new InfoLoadingTask(this);

        parseLoadListener = new ParseLoadListener() {
            @Override
            public void processFinish(Object output) {
                setInformationText((Perfil) output);
            }
        };

        asyncTask.parseLoadListener = parseLoadListener;
                asyncTask.execute(1);

    }

    private void setInformationText(Perfil perfil){
        cargoText.setText(perfil.getCargo());
        direccionText.setText(perfil.getDireccion());
        ciudadText.setText(perfil.getCiudad());
        cpText.setText(perfil.getCodigopostal());
        correoText.setText(perfil.getCorreo());
        telText.setText(perfil.getTelefono());

    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    try {
                        bitmapFoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fotoPerfil.setImageBitmap(bitmapFoto);

                    break;
            }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fotoPerfil:
                pickFromGallery();
                break;
        }
    }

}
