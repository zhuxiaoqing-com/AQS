package com.youxi.math.draw2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends Circle  implements Crash {
	private double xOffset = 0;
	private double yOffset = 0;

	public int oldCount = 1;
	public int count = 1;

	DraggableCircle root;


	public MyCircle(double centerX, double centerY, double radius, DraggableCircle root) {
		super(centerX, centerY, radius);
		this.root = root;
		init();
		root.addNode(this);
	}


	public void init() {
	/*	setCenterX(300);
		setCenterY(300);
		setRadius(50);*/
		setStroke(Color.RED);
		setFill(Color.WHITE);


		setOnScroll(a -> {
			if (a.getDeltaY() > 0) {
				count++;
			} else {
				count = Math.max(--count, 1);
			}


			setRadius(getRadius() / oldCount * count);

			oldCount = count;
		});


		setOnMousePressed(e -> {
			xOffset = e.getX() - getCenterX();
			yOffset = e.getY() - getCenterY();
			System.out.println(e.getSceneY());
			System.out.println(e.getScreenY());
			System.out.println(e.getY());
		});
		setOnMouseDragged(e -> {
			setCenterX(e.getX() - xOffset);
			setCenterY(e.getY() - yOffset);

			System.out.println("setOnMouseDragged");
/*

			Dir dir = new Dir((float) (rectangle.getWidth() / 2), (float) rectangle.getHeight() / 2);
			Position center = new Position((float) rectangle.getX(), (float) rectangle.getY());
			center = center.add(dir);
			ColliderBox colliderBox = new ColliderBox(0, center, dir, (float) rectangle.getRotate());
			rectangle.setWidth(dir.getX()/1000f*2);
			rectangle.setHeight(dir.getZ()/1000f*2);
			rectangle.setRotate(colliderBox.getDegree());


			Position circlePos = new Position((float) getCenterX(), (float) getCenterY());
			int circleRadius = (int) (getRadius() * 1000);
			if(colliderBox.testCircle(circlePos, circleRadius)) {
				setFill(Color.BLUE);
			}else {
				setFill(Color.WHITE);
			}
*/

		});
	}
}
