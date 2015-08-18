package com.proyecto.blindless;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Maximiliano
 */
public class Imagen {

    protected int heigth;
    protected int width;
    protected String url;
    protected Mat imgMat;
    protected Image img;


    public Imagen(){

    }

    public Imagen(String path){
        this.url = cargarArchivo(path);
        this.imgMat = abrirImagen(this.url);
        this.img = convertir(this.imgMat);
    }

    public String cargarArchivo(String p_url){
        URL img_url = getClass().getResource(p_url);
        String ruta = img_url.getPath();
        if (ruta.startsWith("/")) {
            ruta = ruta.substring(1);
        }
        return ruta;
    }

    public Image getImagen(){
        return this.img;
    }

    public Mat getImagenMat(){
        return this.imgMat;
    }

    public Mat abrirImagen(String p_url2){
        //Image ImagenAbrir = null;
        Mat imagen;

        imagen = Imgcodecs.imread(p_url2,Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        if(imagen.empty()){
            return null;
        }
        /*else{
            ImagenAbrir = convertir(imagen);
            //Ventana ventana = new Ventana(ImagenAbrir);
            //ventana.setSize(width,height);
            //ventana.setLocationRelativeTo(null);
            //ventana.setVisible(true);
        }*/

        return imagen;
    }

    public Image convertir(Mat imagen) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", imagen, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;

        try {

            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
        }
        return (Image)bufImage;
    }


}
