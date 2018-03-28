package com.qiaoge.barbecue.utils;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;

/**
 * @author cy
 * @Description 经纬度距离计算
 * @date 2018年3月24日上午9:25:07 
 */
public class SpatialUtils {

	public static Rectangle getRectangle(double longitude, double dimension, Integer radius) {
		if (radius == null) {
			radius = 800;
		}
		SpatialContext geo = SpatialContext.GEO;
		Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(geo.makePoint(longitude, dimension),
				radius * DistanceUtils.KM_TO_DEG, geo, null);
		return rectangle;
	}

	/**
	* @Title getDistance
	* @author cy
	* @Description 
	* @date 2018年3月24日上午10:39:20
	* @return double
	* @throws:
	*/
	public static double getDistance(Double lon1, Double lon2, Double lat1, Double lat2) {
		SpatialContext geo = SpatialContext.GEO;
		double distance = geo.calcDistance(geo.makePoint(lon1, lat1), geo.makePoint(lon2, lat2))
				* DistanceUtils.DEG_TO_KM;
		return distance;
	}

	public static void main(String[] args) {
		double longitude = 116.312528, dimension = 39.983733;
		Rectangle rectangle = getRectangle(longitude, dimension, null);
		System.out.println(rectangle.getMinX() + "=================" + rectangle.getMaxX());// 经度范围
		System.out.println(rectangle.getMinY() + "=================" + rectangle.getMaxY());
	}

}
