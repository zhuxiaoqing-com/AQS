package com.youxi.math.draw;

import com.youxi.entity.CMath;
import com.youxi.entity.Dir;
import com.youxi.entity.Position;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyRectangle extends Rectangle implements Crash, KeyListener{
	private double xOffset = 0;
	private double yOffset = 0;
	public int oldCount = 1;
	public int count = 1;

	public int rotate = 300;
	DraggableCircle root;

	public boolean shift = false;



	/**
	 * Creates an empty instance of Circle.
	 */
	public MyRectangle(int rotate, DraggableCircle root) {
		this.root = root;
		this.rotate = rotate;
		init();
		root.addNode(this);
	}

	public void init() {
		setX(300);
		setY(300);
		setWidth(300);
		setHeight(100);
		setRotate(rotate);
		setStroke(Color.BLACK);
		setFill(Color.WHITE);

		setOnScroll(a -> {
			if (a.getDeltaY() > 0) {
				count++;
			} else {
				count = Math.max(--count, 1);
			}


			setWidth(getWidth() / oldCount * count);
			setHeight(getHeight() / oldCount * count);

			oldCount = count;
		});

		/**
		 * 点到
		 */
		setOnMousePressed(e -> {
			if (shift) {
				xOffset = e.getSceneX();
				yOffset = e.getSceneY();
				return;
			}
			xOffset = e.getSceneX() - getX() ;
			yOffset = e.getSceneY() - getY() ;
			//line.setStroke(Color.RED);
		});
		setOnMouseDragged(e -> {
			if(shift) {
			/*	Line line = new Line(getX() + getWidth() / 2, getY() + getHeight() / 2, e.getSceneX(), e.getSceneY());
				root.addNode(line);
				Position center = new Position((float) (getX() + getWidth() / 2), (float) (getY() + getHeight() / 2));
				Position position = new Position((float) (e.getX()), (float) (e.getY()));
				Position position2 = new Position((float) (yOffset), (float) (yOffset));
				//Position position = new Position((float) (yOffset+ getWidth() / 2), (float) (yOffset + getHeight() / 2));
				Position sub = position.sub(center);
				Position sub2 = position2.sub(center);
				double degree = 0;//SkillAreaMethod.getDegree(sub, new Position(1000,0));
				setRotate(degree);
				System.out.println("==========" +degree);*/
				return;
			}
			setX(e.getSceneX()-xOffset);
			setY(e.getSceneY() - yOffset);

		});


	}


	@Override
	public void onKeyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() != KeyCode.SHIFT) {
			return;
		}
		shift =true;
	}

	@Override
	public void onKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() != KeyCode.SHIFT) {
			return;
		}
		shift =false;
	}

	public Position getCenter() {
		return new Position((float) (getX() + getWidth() / 2), (float) (getY() + getHeight() / 2));
	}

	public List<Position> getPoints() {
		Position center = getCenter();
		Dir dir = new Dir(getWidth() / 2, getHeight() / 2);
		Dir[]	localSizeDirArray = new Dir[4];
		localSizeDirArray[0] = new Dir(-dir.getX(), -dir.getZ());// -x -z
		localSizeDirArray[1] = new Dir(-dir.getX(), dir.getZ());// -x +z
		localSizeDirArray[2] = new Dir(dir.getX(), dir.getZ());// +x +z
		localSizeDirArray[3] = new Dir(dir.getX(), -dir.getZ());// +x -z

		Position[] points = new Position[4];

		Dir dir0 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[0], -rotate);
		Dir dir1 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[1], -rotate);
		Dir dir2 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[2], -rotate);
		Dir dir3 = CMath.getUnityDirByRotateYFromX(localSizeDirArray[3], -rotate);
		points[0] = center.add(dir0);
		points[1] = center.add(dir1);
		points[2] = center.add(dir2);
		points[3] = center.add(dir3);
		return Arrays.stream(points).collect(Collectors.toList());
	}
}
