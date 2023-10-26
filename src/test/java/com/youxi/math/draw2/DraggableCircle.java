package com.youxi.math.draw2;

import com.youxi.entity.Circle;
import com.youxi.entity.Dir;
import com.youxi.entity.OBB2D;
import com.youxi.entity.Position;
import com.youxi.math.GJK.GJKTest01;
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


        Affine affine = Rotate.affine(1, 0, 0, -1, width/2, height/2);
        graphRoot.getTransforms().addAll(affine, scale);


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

        Position rectangleCenter = new Position(137.9f,45.87f);
        Position rectangleDir = new Position(10f,10f);
        Position sub = rectangleCenter.sub(rectangleDir);

        Position hitV = new Position(117.719f,25.741f);
        double rotate = 351.265;


        MyCircle circle = new MyCircle(hitV.getX()/1000f,hitV.getZ()/1000f,10, this);

        MyRectangle rectangle = new MyRectangle(sub.getX()/1000f,sub.getZ()/1000f,rectangleDir.getX()/1000f * 2, rectangleDir.getZ()/1000f * 2,
                (int) rotate, this, false);


        circle.setOnMouseDragged(e -> {
            circle.setCenterX(e.getX() - xOffset);
            circle.setCenterY(e.getY() - yOffset);


            Position circlePos = new Position((float) circle.getCenterX(), (float) circle.getCenterY());
            int circleRadius = (int) (circle.getRadius() * 1000);
            GJKTest01 gjkTest01 = new GJKTest01();
            if(gjkTest01.GJK(new Circle(circlePos, circleRadius), new OBB2D(rectangle.getCenter(), rectangle.getSize(),rectangle.getRotate1()))) {
                circle.setFill(Color.BLUE);
            }else {
                circle.setFill(Color.WHITE);
            }

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
