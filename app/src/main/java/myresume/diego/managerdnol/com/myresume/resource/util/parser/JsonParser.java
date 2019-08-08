package myresume.diego.managerdnol.com.myresume.resource.util.parser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import myresume.diego.managerdnol.com.myresume.resource.pojo.Certificado;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Educacion;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Experiencia;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Hobbie;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Lenguaje;
import myresume.diego.managerdnol.com.myresume.resource.pojo.ListEducacion;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Perfil;
import myresume.diego.managerdnol.com.myresume.resource.pojo.Skill;
import myresume.diego.managerdnol.com.myresume.resource.util.constants.Constants;

public class JsonParser {

    public static Object parseObject(String result, int type) throws JSONException {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(result);
        switch (type){
            case 1:
                return gson.fromJson(result, Perfil.class);
            case 2:
                ArrayList<Educacion> arrayEducacion = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.EDUCACION_KEY).length(); i++) {
                    arrayEducacion.add(gson.fromJson(jsonObject.optJSONArray(Constants.EDUCACION_KEY).optJSONObject(i).toString(), Educacion.class));
                }
                return arrayEducacion;
            case 3:
                ArrayList<Experiencia> arrayExperiencia = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.EXPERIENCIA_KEY).length(); i++) {
                    arrayExperiencia.add(gson.fromJson(jsonObject.optJSONArray(Constants.EXPERIENCIA_KEY).optJSONObject(i).toString(), Experiencia.class));
                }
                return arrayExperiencia;
            case 4:
                ArrayList<Skill> arraySkill = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.SKILL_KEY).length(); i++) {
                    arraySkill.add(gson.fromJson(jsonObject.optJSONArray(Constants.SKILL_KEY).optJSONObject(i).toString(), Skill.class));
                }
                return arraySkill;
            case 5:
                ArrayList<Certificado> arrayCertificado = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.CERTIFICADO_KEY).length(); i++) {
                    arrayCertificado.add(gson.fromJson(jsonObject.optJSONArray(Constants.CERTIFICADO_KEY).optJSONObject(i).toString(), Certificado.class));
                }
                return arrayCertificado;
            case 6:
                ArrayList<Lenguaje> arrayLenguajes = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.LENGUAJE_KEY).length(); i++) {
                    arrayLenguajes.add(gson.fromJson(jsonObject.optJSONArray(Constants.LENGUAJE_KEY).optJSONObject(i).toString(), Lenguaje.class));
                }
                return arrayLenguajes;
            case 7:
                ArrayList<Hobbie> arrayHobbies = new ArrayList<>();
                for (int i = 0; i < jsonObject.getJSONArray(Constants.HOBBIES_KEY).length(); i++) {
                    arrayHobbies.add(gson.fromJson(jsonObject.optJSONArray(Constants.HOBBIES_KEY).optJSONObject(i).toString(), Hobbie.class));
                }
                return arrayHobbies;
        }
       return null;
    }

}
