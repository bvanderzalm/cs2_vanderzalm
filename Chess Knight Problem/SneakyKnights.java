// Bradley Vanderzalm
// COP 3503, Spring 2021
// br304087

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class Coordinate
{
	int column;
	int row;

	Coordinate(int column, int row)
	{
		this.column = column;
		this.row = row;
	}

	// This modifies the hashcode given out by this object added to the HashSet.
	// This was done in effort where if two different objects with the same data inside could
	// have the same hashcode.
	@Override
	public int hashCode() 
	{
		// Multiply hashcode value by a prime number
		return column * 31 + row * 31;
	}

	// With the newly modified hashcode I also had to modify the equals
	// method (that affects the .contains() method in HashSet) where two seperate
	// objects with the same data inside would be considered the same.
	@Override
	public boolean equals(Object o)
	{
		// Dpuble check if obj is empty before trying to access data.
		if (o == null)
			return false;

		// Ensure the runtime is the same from the two objects
		if (getClass() != o.getClass())
			return false;

		// If objects don't have the same data within, then they are not the same.
		Coordinate second = (Coordinate) o;
		if (column != second.column || row != second.row)
			return false;

		return true;
	}
}

public class SneakyKnights
{
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		if (coordinateStrings.isEmpty())
			return true;

		int numKnights = coordinateStrings.size();

		// Used to quickly store one knight coordinate after parsing it's coordinate String
		int [] tempKnightCoord = new int[2];

		// Used to store all knight locations.
		HashSet<Coordinate> myHash = new HashSet<Coordinate>();

		// Used to temporarily store the locations that one piece can attack. Note: list will store
		// only 8 coordinates (or less) at a time.
		LinkedList<Coordinate> list = new LinkedList<Coordinate>();

		for (int i = 0; i < numKnights; i++)
		{
			parseCoordinateStrings(coordinateStrings.get(i), tempKnightCoord);

			int colCoord = tempKnightCoord[0];
			int rowCoord = tempKnightCoord[1];

			// Calculates the different places a Knight can attack and stores it in a linked list.
			calculateAttackableCoords(colCoord, rowCoord, boardSize, list);

			// Removes all elements from head of list and checks whether or not that coordinate
			// is stored in the hashSet. Note: isEmpty(), contains(), and poll() all run in constant time.
			while (list.isEmpty() == false)
			{
				if (myHash.contains(list.poll()))
					return false;
			}

			myHash.add(new Coordinate(colCoord, rowCoord));
		}

		return true;
	}

	// This helper method takes one Knight coordinate and calculates the different locations that
	// it can attack. It then stores all of the locations into a linked list. This method also
	// prevents the storing of false positive attackable knights that are outside the board
	public static void calculateAttackableCoords(
		int col, int row, int boardSize, LinkedList<Coordinate> list)
	{ 
		// If the knight is too close to the edge then it'll avoid adding an attackable knight
		// to the linked list.

		if (col <= (boardSize - 2) && row <= (boardSize - 1))
			list.addFirst(new Coordinate(col + 2, row + 1));

		if (col <= (boardSize - 2) && (row - 1) > 0)
			list.addFirst(new Coordinate(col + 2, row - 1));

		if ((col - 2) > 0 && row <= (boardSize - 1))
			list.addFirst(new Coordinate(col - 2, row + 1));

		if ((col - 2) > 0 && (row - 1) > 0)
			list.addFirst(new Coordinate(col - 2, row - 1));

		if (col <= (boardSize - 1) && row <= (boardSize - 2))
			list.addFirst(new Coordinate(col + 1, row + 2));

		if ((col - 1) > 0 && row <= (boardSize - 2))
			list.addFirst(new Coordinate(col - 1, row + 2));

		if (col <= (boardSize - 1) && (row - 2) > 0)
			list.addFirst(new Coordinate(col + 1, row - 2));

		if ((col - 1) > 0 && (row - 2) > 0)
			list.addFirst(new Coordinate(col - 1, row - 2));

	}

	// Adapted from my SneakyQueens.java submission
	// Takes one string and passes a temp array to store the (col, row) coordinate.
	public static void parseCoordinateStrings(String knightString, int [] knightCoordinate)
	{
		int resultColumn = 0, resultRow = 0;

		// Loop through all characters in the string
		for (int i = 0; i < knightString.length(); i++)
		{
			// Store the ASCII value from a character
			int ascii = (int) knightString.charAt(i);

			// Check for horizontal columns which are represented as char letters a through z.
			if (knightString.charAt(i) >= 'a' && knightString.charAt(i) <= 'z')
			{
				// Horner's rule - Base 26 (a-z)
				resultColumn *= 26;
				resultColumn = resultColumn + (ascii - ('a' - 1));
			}

			// Check for vertical rows which are represented as digits 0 through 9.
			else if (knightString.charAt(i) >= '0' && knightString.charAt(i) <= '9')
			{
				// Horner's rule - Base 10 (0-9)
				resultRow *= 10;
				resultRow = resultRow + (ascii - '0');
			}
		}

		// Add the coordinates to the temp array to be able return both values.
		knightCoordinate[0] = resultColumn;
		knightCoordinate[1] = resultRow;
	}

	public static double difficultyRating()
	{
		return 2.2;
	}

	public static double hoursSpent()
	{
		return 5.0;
	}
}
