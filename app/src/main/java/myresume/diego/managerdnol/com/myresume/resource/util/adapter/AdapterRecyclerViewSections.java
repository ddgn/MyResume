package myresume.diego.managerdnol.com.myresume.resource.util.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import myresume.diego.managerdnol.com.myresume.R;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Certificado;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Educacion;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Experiencia;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Hobbie;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Lenguaje;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Perfil;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Skill;

public class AdapterRecyclerViewSections extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_EDUCACION = 1;
    private static int TYPE_LABORAL = 2;
    private static int TYPE_SKILLS = 3;
    private static int TYPE_CERTIFICADO = 4;
    private static int TYPE_LENGUAJE = 5;
    private static int TYPE_HOBBIE = 6;
    private ArrayList<Object> arrayList;
    private Context context;

    public AdapterRecyclerViewSections(ArrayList<Object> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
            view = LayoutInflater.from(context).inflate(R.layout.ed_lab_cert_design_item, viewGroup, false);
            return new EdLabCerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_EDUCACION) {
            ((EdLabCerViewHolder) viewHolder).setEduDetails((Educacion) arrayList.get(position));
        } else if (getItemViewType(position) == TYPE_LABORAL){
            ((EdLabCerViewHolder) viewHolder).setLabDetails((Experiencia) arrayList.get(position));
        } else if (getItemViewType(position) == TYPE_SKILLS){
            ((EdLabCerViewHolder) viewHolder).setSkillDetails((Skill) arrayList.get(position));
        } else if (getItemViewType(position) == TYPE_CERTIFICADO){
            ((EdLabCerViewHolder) viewHolder).setCertificadoDetails((Certificado) arrayList.get(position));
        }else if (getItemViewType(position) == TYPE_LENGUAJE){
            ((EdLabCerViewHolder) viewHolder).setLenguajeDetails((Lenguaje) arrayList.get(position));
        }else if (getItemViewType(position) == TYPE_HOBBIE){
            ((EdLabCerViewHolder) viewHolder).setHobbieDetails((Hobbie) arrayList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(0) instanceof Educacion){
            return  TYPE_EDUCACION;
        }else if(arrayList.get(0) instanceof Experiencia){
            return TYPE_LABORAL;
        }else if (arrayList.get(0) instanceof Skill){
            return TYPE_SKILLS;
        }else if (arrayList.get(0) instanceof Certificado){
            return TYPE_CERTIFICADO;
        }else if (arrayList.get(0) instanceof Lenguaje){
            return TYPE_LENGUAJE;
        }else if (arrayList.get(0) instanceof Hobbie){
            return TYPE_HOBBIE;
        }

        return 0;
    }

}

class EdLabCerViewHolder extends RecyclerView.ViewHolder {

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private ImageView imageView;

    EdLabCerViewHolder(@NonNull View itemView) {
        super(itemView);
        txt1 = itemView.findViewById(R.id.textView1);
        txt2 = itemView.findViewById(R.id.textView2);
        txt3 = itemView.findViewById(R.id.textView3);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public void setEduDetails(Educacion educacion) {
        txt1.setText(educacion.getEscuela());
        txt2.setText(educacion.getPeriodo());
        txt3.setText(educacion.getGrado());
        imageView.setImageResource(R.mipmap.escuela);
    }
    public void setLabDetails(Experiencia experiencia) {
        txt1.setText(experiencia.getEmpresa());
        txt2.setText(experiencia.getPeriodo());
        txt3.setText(experiencia.getPuesto());
        imageView.setImageResource(R.mipmap.trabajo);
    }
    public void setSkillDetails(Skill skill) {
        txt1.setText(skill.getNombre());
        txt2.setText(skill.getTipo());
        txt3.setText("");
        imageView.setImageResource(R.mipmap.skill);
    }
   public void setCertificadoDetails(Certificado certificado) {
       txt1.setText(certificado.getNombre());
       txt2.setText(certificado.getId());
       txt3.setText(certificado.getEmpresa());
       imageView.setImageResource(R.mipmap.certificado);
    }
    public void setLenguajeDetails(Lenguaje lenguaje) {
        txt1.setText(lenguaje.getLenguaje());
        txt2.setText(lenguaje.getNivel());
        txt3.setText(String.format("Nativo: %s", lenguaje.getNativo()));
        imageView.setImageResource(R.mipmap.lenguaje);
    }
    public void setHobbieDetails(Hobbie hobbie) {
        txt1.setText(hobbie.getNombre());
        txt2.setText(hobbie.getPeriodicidad());
        txt3.setText("");
        imageView.setImageResource(R.mipmap.hobbie);
    }
}

