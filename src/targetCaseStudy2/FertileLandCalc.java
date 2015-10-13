package targetCaseStudy2;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FertileLandCalc {

	public static void main(String args[]) throws IOException {
		int width = 400;
		int height = 600;
		
		List<String> allArgs = new ArrayList<>();
		List<String> coordinates = new ArrayList<>();
		List<List<Integer>> allCoordPoints = new ArrayList<>();
		List<Integer> areas = new ArrayList<>();
		
		// If args is empty then we do not need to process
		if (args.length == 0) {
			String e = "The argument is empty nothing to process.";
			throw new IOException(e);
		}
		
		// Expecting input as numbers separated by spaces, eg. {"0 292 399 307"}
		// Want to clean out the special characters so its easier to manipulate later
		for (String arg : args) {
			String coordinate = cleanArg(arg);
			allArgs.add(coordinate);
		}
	
		// We want to split the args into actual pairs of two coordinates
		for (String arg : allArgs) {
			String[] coords = arg.split(",");
			for (String coord : coords) {
				coordinates.add(coord);
			}
		}
		
		for (String coordinate : coordinates) {
			System.out.print(coordinate + " ");
		}
		
		for (String coordinate : coordinates) {
			List<Integer> coordPoints = new ArrayList<>();
			String[] coords = coordinate.split(" ");
			
			for (String coord : coords) {
				coordPoints.add(Integer.parseInt(coord));
			}
			
			// Want to make sure that the coordinate given are actually in the realm of the actual land owned
//			for (int i = 0; i <= coordPoints.size(); i++) {
//				boolean isValid;
//				
//				if (i%2 == 0) {
//					isValid = isValidCooridnate(coordPoints.get(i), width);
//				} else {
//					isValid = isValidCooridnate(coordPoints.get(i), height);
//				}
//				
//				if (!isValid) {
//					String e = "The argument has a coordiante outside of the feasible range" + coordPoints.get(i).toString() + ".";
//					throw new IOException(e);
//				}
//			}
			
			allCoordPoints.add(coordPoints);
			System.out.println("\nBottom Left: (" + coordPoints.get(0) + "," + coordPoints.get(1) + ") and Top Right: (" + coordPoints.get(2) + "," + coordPoints.get(3) + ")");
		}
		
		for (List<Integer> points : allCoordPoints) {
				Point bottomLeft = new Point(points.get(0), points.get(1));
				Point topRight = new Point(points.get(2), points.get(3));
				
				int area = rectangleAreaCalc(bottomLeft, topRight);
				
				areas.add(area);
		}
		
		System.out.println(Collections.min(areas) + " " + Collections.max(areas));
	}

	private static int rectangleAreaCalc(Point bottomLeft, Point topRight) {
		// Below is the calc to get the sides of the rectangle based off of the coordinates given.
		// Since the triangle we are using to calculate the a and b lengths of is always a 30 60 90 right triangle
		// we can draw some conclusions as to what calculations we want to use.
		int area = 0;
		// First need to get the c value or the hypotenuse 
		int c = (int) Math.sqrt((Math.pow((topRight.getX() - bottomLeft.getX()), 2) + 
					Math.pow((topRight.getY() - bottomLeft.getY()), 2)));
		// Next we can really calculate either of the other to first, but decided to just do this order
		int b = (int) (c * Math.cos(Math.toRadians(30)));
		int a = (int) Math.sqrt(Math.pow(c, 2) - Math.pow(b, 2));
		
		area = a*b;
		System.out.println("C = " + c + " B = " + b + " A = " + a);
		System.out.println("The area of the fertile land is: " + area);
		return area;
	}

	private static boolean isValidCooridnate(Integer coord, int validCoord) {
		// Will return true if its within the valid width or height
		if (coord <= validCoord - 1) {
			return true;
		} else {
			return false;
		}
	}

	private static String cleanArg(String coordinate) {
		// Removes the " and { } from the arguments
		String regex = "[\"{}]";
		String replacement = "";
		
		String cleanCoordinate = coordinate.replaceAll(regex, replacement);

		return cleanCoordinate;
	}

}
