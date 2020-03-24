package be.webtechie.shark.elements;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BitingShark extends Group {

    private final static Image SHARK_1 =  new Image(BitingShark.class.getResource("/images/shark.png").toString());
//    private final static Image SHARK_2 =  new Image(BitingShark.class.getResource(".png").toString());
//    private final static Image SHARK_3 =  new Image(BitingShark.class.getResource("3.png").toString());
//    private final static Image SHARK_4 =  new Image(BitingShark.class.getResource("4.png").toString());
//    private final static Image SHARK_5 =  new Image(BitingShark.class.getResource("5.png").toString());

    public BitingShark() {

        final ImageView shark1 = new ImageView(SHARK_1);
        final ImageView shark2 = new ImageView(SHARK_1);
        final ImageView shark3 = new ImageView(SHARK_1);
        final ImageView shark4 = new ImageView(SHARK_1);
        final ImageView shark5 = new ImageView(SHARK_1);

        this.getChildren().add(shark1);
        shark1.setFitHeight(100);
        shark1.setFitWidth(100);
//        this.setTranslateX(0.1D);
//        this.setTranslateY(0.1D);
        //this.setTranslateX(300);
        //this.setTranslateY(450);
    }

}
