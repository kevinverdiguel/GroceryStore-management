package sample;

import com.jfoenix.controls.JFXTextField;

public class Verificador
{
    public static int verificarTexto(JFXTextField campoTexto)
    {
        int valor=0;
        String texto=campoTexto.getText();
        texto=texto.replaceAll(" ", "");
        if(texto.length()==0){
            valor=1;
        }
        else
        {
            valor=0;
        }
        return valor;
    }
    public static int verificarString(String texto)
    {
        int valor=0;
        texto=texto.replaceAll(" ", "");
        if(texto.length()==0){
            valor=1;
        }
        else
        {
            valor=0;
        }
        return valor;
    }
}
