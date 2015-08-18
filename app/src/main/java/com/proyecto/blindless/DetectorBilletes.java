package com.proyecto.blindless;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 *
 * @author Maximiliano
 */
public class DetectorBilletes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        /*INICIO - toda esta parte es para testear nomas*/
        try {
            Imagen imagen = new Imagen("/resources/images/4.jpg");
            Imagen imagen2 = new Imagen("/resources/TEST.jpg");
            Imagen imagen3 = new Imagen();

            Image imagenCargada = imagen.getImagen();
            Image imagenCargada2 = imagen2.getImagen();


            Mat imagenCargadaMat = imagen.getImagenMat();
            Mat imagenCargadaMat2 = imagen2.getImagenMat();
            Mat imagenComparadaMat;

            Matcher comparador = new Matcher();

            imagenComparadaMat = comparador.compararImagen(imagenCargadaMat, imagenCargadaMat2);


            int width = imagen3.convertir(imagenComparadaMat).getWidth(null);
            int height = imagen3.convertir(imagenComparadaMat).getHeight(null);


            Ventana ventana = new Ventana(imagenCargada);
            Ventana ventana2 = new Ventana(imagenCargada2);
            Ventana ventana3 = new Ventana(imagen3.convertir(imagenComparadaMat));

            ventana3.setSize(width,height);
            ventana.setSize(630,360);
            ventana2.setSize(630,360);
            //ventana3.setSize(630,360);

            ventana.setLocationRelativeTo(null);
            ventana2.setLocationRelativeTo(null);
            ventana3.setLocationRelativeTo(null);

            ventana.setVisible(true);
            ventana2.setVisible(true);
            ventana3.setVisible(true);

            String sDirectorio = "./resources/images/";

            Path ruta = Paths.get("resources//images//");
            System.out.println(ruta);
            File f = new File(ruta.toUri());

            System.out.println(f.getAbsoluteFile());

            System.out.println(Arrays.toString(f.listFiles()));


            // File[] ficheros = f.listFiles();
            //for (File fichero : ficheros) {
            //    System.out.println(fichero.getName());
            // }

        /*FIN - toda esta parte es para testear nomas*/
        }catch(Exception e) {
            //System.out.println("No se pudo abrir alguna de las imagenes\n");
            e.printStackTrace();
        }
    }
}
