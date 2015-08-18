package com.proyecto.blindless;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Maximiliano
 */
public class Matcher {
    public Matcher(){

    }
    public Mat compararImagen(Mat img1, Mat img2){

        // detecting keypoints
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB );// ORB);
        DescriptorExtractor descriptor = DescriptorExtractor.create(DescriptorExtractor.ORB );//ORB);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_SL2);// .BRUTEFORCE_HAMMING);
        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();

        //vector<KeyPoint> keyPoints1, keyPoints2;
        detector.detect(img1, keypoints1);
        descriptor.compute(img1, keypoints1, descriptors1);

        detector.detect(img2, keypoints2);
        descriptor.compute(img2, keypoints2, descriptors2);

        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(descriptors1, descriptors2, matches);

        Mat rgb =  new Mat();

        Features2d.drawMatches(img1, keypoints1, img2, keypoints2, matches, rgb);
        Mat outputImage =  new Mat();

        Imgproc.cvtColor(rgb, outputImage, Imgproc.COLOR_RGB2RGBA);
        return outputImage;

    }
}
