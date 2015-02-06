package com.elfec.ssc.businesslogic;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.location.Location;

import com.elfec.ssc.model.LocationPoint;;

public class LocationPointsManager {
	/**
	 * Registra el conjunto de puntos de ubicaci�n
	 * @param points
	 */
	public static void registerLocations(final List<LocationPoint> points)
	{
		for(LocationPoint point : points)
		{
			point.setInsertDate(DateTime.now());
			point.save();
		}
	}
	/**
	 * Obtiene los puntos cercanos a la ubicaci�n dada, y con la distancia definida
	 * @param points
	 * @param current
	 * @param maxDistance
	 * @return
	 */
	public static List<LocationPoint> getNearestPoints(List<LocationPoint> points,Location current,double maxDistance)
	{
		List<LocationPoint> result=new ArrayList<LocationPoint>();
		for(LocationPoint point:points)
		{
			if(point.distanceFrom(current)<=maxDistance)
			{
				result.add(point);
			}
		}
		return result;
	}
}
