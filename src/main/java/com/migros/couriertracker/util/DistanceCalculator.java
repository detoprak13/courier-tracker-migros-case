package com.migros.couriertracker.util;

public class DistanceCalculator {

	public static double distance(double lat1, double lng1, double lat2, double lng2) {
		if ((lat1 == lat2) && (lng1 == lng2)) {
			return 0;
		}
		else {
			double theta = lng1 - lng2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return (dist);
		}
	}
}
