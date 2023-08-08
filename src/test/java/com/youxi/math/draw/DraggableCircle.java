package com.youxi.math.draw;

import com.example.demo1.util.vo.Vector2f;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;
import com.youxi.math.OBBCircle;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DraggableCircle extends Application  {

    private double xOffset = 0;
    private double yOffset = 0;

    private int width = 1000;
    private int height = 1000;


    public List<Node> list = new ArrayList<Node>();

    Pane root;
    Pane graphRoot;

    private int lastCount = 1;
    private int count = 1;
    Scale scale = Scale.scale(1, 1);
    private double x;
    private double y;


    Text text;

    @Override
    public void start(Stage primaryStage) {
        this.root = new Pane();
        graphRoot = new Pane();
        root.getChildren().add(graphRoot);
        Scene scene = new Scene(root, width, height);

        for (Node node : list) {
            graphRoot.getChildren().add(node);
        }

        list.clear();

     /*   PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        perspectiveCamera.setRotationAxis(Rotate.Y_AXIS);
        Point2D.ZERO
        scene.setCamera(perspectiveCamera);*/


/*
        Rotate rotate = Rotate.rotate(270, width/2, height/2);
        root.getTransforms().add(rotate);
*/

       // Affine affine = Rotate.affine(1, 0, 0, -1, width/2, height/2);
       // graphRoot.getTransforms().addAll(affine, scale);


        primaryStage.setTitle("Draggable Circle");
        primaryStage.setScene(scene);

        Line horizontalLine = new Line();
        horizontalLine.setStartX(-width/2);
        horizontalLine.setStartY(0);
        horizontalLine.setEndX(width/2);
        horizontalLine.setEndY(0);
        horizontalLine.setStroke(Color.RED);

        Line verticalLine = new Line();
        verticalLine.setStartX(0);
        verticalLine.setStartY(-height/2);
        verticalLine.setEndX(0);
        verticalLine.setEndY(height/2);
        verticalLine.setStroke(Color.BLUE);
        graphRoot.getChildren().addAll(horizontalLine, verticalLine);


        text = new Text(100, 100, "null");
        root.getChildren().add(text);

        drawDirection();
        /*
        Rectangle rectangle = new Rectangle(200,300,2, 3);

        rectangle.setRotate(40);*/
        Vector2f rectangleCenter = new Vector2f(137.9f,558.7f);
        Vector2f rectangleDir = new Vector2f(1.65f,0.25f);
        Vector2f sub = rectangleCenter.sub(rectangleDir);

        Vector2f hitV = new Vector2f(137.719f,45.741f);
        double rotate = 351.265;
        MyRectangle rectangle = new MyRectangle(sub.x, sub.y, rectangleDir.x * 2, rectangleDir.y * 2, (int) rotate, this, true);



       /* Circle circle = new Circle(100, 100, 2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);*/
        MyCircle circle = new MyCircle(hitV.x,hitV.y,2, this);
        circle.setOnMousePressed(e -> {
            xOffset = e.getX() - circle.getCenterX();
            yOffset = e.getY() - circle.getCenterY();
        });

        circle.setOnMouseDragged(e -> {
            circle.setCenterX(e.getSceneX() - xOffset);
            circle.setCenterY(e.getY() - yOffset);


            Position center = rectangle.getCenter();

            Dir dir = new Dir((float) (rectangle.getWidth() / 2), (float) rectangle.getHeight() / 2);
            Position circlePos = new Position((float) circle.getCenterX(), (float) circle.getCenterY());
            int circleRadius = (int) (circle.getRadius() * 1000);

            OBBCircle obbCircle = new OBBCircle();
            int i = obbCircle.pointOBBNearestSegment(circlePos, center, dir, (float) rectangle.getRotate());

           /* Position rotateDir = circlePos.sub(center).getRotateDir((float) rectangle.getRotate());
            MyCircle myCircle = new MyCircle(rotateDir.getX() / 1000f, rotateDir.getZ() / 1000f, 100);
            root.getChildren().add(myCircle);
*/
            String name = "在里面";
            switch (i) {
                case OBBCircle.Direction.UP:
                    name = "上";
                    break;
                case OBBCircle.Direction.DOWN:
                    name = "下";
                    break;
                case OBBCircle.Direction.LEFT:
                    name = "左";
                    break;
                case OBBCircle.Direction.RIGHT:
                    name = "右";
                    break;
            }
            text.setText("i-----  "+name);

           /* if(obbCircle.pointAABBNearestSegment(circlePos, colliderBox)) {
                circle.setFill(Color.BLUE);
            }else {
                circle.setFill(Color.WHITE);
            }*/

        });


        primaryStage.show();

        scene.setOnKeyPressed(a -> {
            for (Node child : root.getChildren()) {
                if(child instanceof KeyListener) {
                    ((KeyListener) child).onKeyPressed(a);
                }
            }

        });

        scene.setOnKeyReleased(a->{
            for (Node child : root.getChildren()) {
                if(child instanceof KeyListener) {
                    ((KeyListener) child).onKeyReleased(a);
                }
            }

        });


        graphRoot.setOnMousePressed(e->{
            x = e.getX();
            y = e.getY();
        });
        graphRoot.setOnMouseReleased(e->{
            x = 0;
            y = 0;
        });
        graphRoot.setOnScroll(e->{
            if(e.getDeltaY()> 0) {
                count++;
            }else {
                count--;
                count = Math.max(count,1);
            }



            scale.setX(count);
            scale.setY(count);
            scale.setPivotX(x);
            scale.setPivotY(y);
        });

    }

    private void drawDirection() {
      /*  Text 左下角 = new Text(10, 10, "左下角");
        Text 左上角 = new Text(10, height-10, "左上角");
        Text 右下角 = new Text(width-100, 10, "右下角");
        Text 右上角 = new Text(width-100, height-100, "右上角");
        root.getChildren().addAll(左下角, 左上角,右下角,右上角);*/

       /* Text 上 = new Text(height-100, width/2, "上");
        Text 下 = new Text(100, width/2, "下");
        Text 左 = new Text(width/2, 100, "左");
        Text 右 = new Text(width/2, height-100, "右");*/


        Text 上 =  new Text(width/2, height-100, "下");
        Text 下 = new Text(width/2, 100, "上");
        Text 左 =new Text(100, height/2, "左");
        Text 右 = new Text(height-100, width/2, "右");
        root.getChildren().addAll(上, 下,左,右);
    }

    public static void main(String[] args) {
        DraggableCircle draggableCircle = new DraggableCircle();
        draggableCircle.run();
    }


    public void run() {
        launch();
    }

    public void addNode(Node... node) {
        for (Node node1  : node) {
            if(root == null) {
                list.add(node1);
            } else {
                graphRoot.getChildren().add(node1);

            }
        }
    }

}
