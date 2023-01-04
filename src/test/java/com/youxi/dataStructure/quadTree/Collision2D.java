package com.youxi.dataStructure.quadTree;

import com.example.demo1.util.vo.Vector2f;

public class Collision2D {
	public LRect getLRect(ColliderProxy proxy) {
		switch (proxy.type) {
			case 1: // 圆
				return LRect.CreateRect(proxy.pos, new Vector2f(proxy.radius, proxy.radius));
			case 2: // aabb
				return LRect.CreateRect(proxy.pos, proxy.size);
			case 3: // obb
				return LRect.CreateRect(proxy.pos, new Vector2f(proxy.radius, proxy.radius));
		}
		return null;
	}
}
