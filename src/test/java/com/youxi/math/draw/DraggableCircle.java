package com.youxi.math.draw;

import com.youxi.dataStructure.collisionTree.AABB;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;
import com.youxi.math.OBBCircle;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
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

    Text text;

    @Override
    public void start(Stage primaryStage) {
        this.root = new Pane();
        for (Node node : list) {
            root.getChildren().add(node);
        }
        list.clear();
        Scene scene = new Scene(root, width, height);

     /*   PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        perspectiveCamera.setRotationAxis(Rotate.Y_AXIS);
        Point2D.ZERO
        scene.setCamera(perspectiveCamera);*/


/*
        Rotate rotate = Rotate.rotate(270, width/2, height/2);
        root.getTransforms().add(rotate);
*/

        Affine affine = Rotate.affine(1, 0, 0, -1, 0, height);
        root.getTransforms().addAll(affine);


        primaryStage.setTitle("Draggable Circle");
        primaryStage.setScene(scene);


        Line horizontalLine = new Line();
        horizontalLine.setStartX(0);
        horizontalLine.setStartY(height/2);
        horizontalLine.setEndX(width);
        horizontalLine.setEndY(height/2);
        horizontalLine.setStroke(Color.RED);

        Line verticalLine = new Line();
        verticalLine.setStartX(width / 2);
        verticalLine.setStartY(0);
        verticalLine.setEndX(width / 2);
        verticalLine.setEndY(height);
        verticalLine.setStroke(Color.BLUE);
        root.getChildren().addAll(horizontalLine, verticalLine);
        text = new Text(100, 100, "null");
        root.getChildren().add(text);

        drawDirection();
        /*
        Rectangle rectangle = new Rectangle(200,300,2, 3);

        rectangle.setRotate(40);*/
        MyRectangle rectangle = new MyRectangle(30, this);
        new MyRectangle(0, this);
        new MyRectangle(15, this);

        Position center = rectangle.getCenter();
        List<Position> points = rectangle.getPoints();
        for (Position point : points) {
            Position add = point.add(point.sub(center).normalize().multiply(300));
            Line line = new Line(center.getX() / 1000f, center.getZ() / 1000f, add.getX() / 1000f, add.getZ() / 1000f);
            root.getChildren().add(line);
        }


       /* Circle circle = new Circle(100, 100, 2);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);*/
        MyCircle circle = new MyCircle(300,300,2);
        circle.setOnMousePressed(e -> {
            xOffset = e.getSceneX() - circle.getCenterX();
            yOffset = e.getY() - circle.getCenterY();

            System.out.println("getSceneY  " + e.getSceneY());
            System.out.println("getScreenY  " + e.getScreenY());
            System.out.println("getY  " +e.getY());
        });

        circle.setOnMouseDragged(e -> {
            circle.setCenterX(e.getSceneX() - xOffset);
            circle.setCenterY(e.getY() - yOffset);


            Dir dir = new Dir((float) (rectangle.getWidth() / 2), (float) rectangle.getHeight() / 2);
            AABB colliderBox = new AABB(center.getX(), center.getZ(), dir);


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
        root.getChildren().add(circle);
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

    }

    private void drawDirection() {
        Text 左下角 = new Text(10, 10, "左下角");
        Text 左上角 = new Text(10, height-10, "左上角");
        Text 右下角 = new Text(width-100, 10, "右下角");
        Text 右上角 = new Text(width-100, height-100, "右上角");
        root.getChildren().addAll(左下角, 左上角,右下角,右上角);

       /* Text 上 = new Text(height-100, width/2, "上");
        Text 下 = new Text(100, width/2, "下");
        Text 左 = new Text(width/2, 100, "左");
        Text 右 = new Text(width/2, height-100, "右");*/


        Text 上 =  new Text(width/2, height-100, "上");
        Text 下 = new Text(width/2, 100, "下");
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
                root.getChildren().add(node1);

            }
        }
    }

}
