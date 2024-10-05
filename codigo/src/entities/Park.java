package entities;

public class Park {
       private int numberParkingSpaces;
       private int idPark;

       private boolean parkingSpaces[][];
       private final int rows;
       private final int columns;

       public Park(int rows, int columns) {
              this.rows = rows;
              this.columns = columns;
              parkingSpaces = new boolean[rows][columns];
       }
       
       public int getNumberParkingSpaces() {
              return numberParkingSpaces;
       }
       public void setNumberParkingSpaces(int numberParkingSpaces) {
              this.numberParkingSpaces = numberParkingSpaces;
       }

       public boolean occupySpot(int row, int column) {
              if (!parkingSpaces[row][column]) { 
                  parkingSpaces[row][column] = true; 
                  System.out.println("Spot successfully occupied.");
                  return true;
              } else {
                  System.out.println("Spot is already occupied!");
                  return false;
              }
       }

       public boolean freeSpot(int row, int column) {
              if (parkingSpaces[row][column]) { 
                  parkingSpaces[row][column] = false; 
                  System.out.println("Spot successfully freed.");
                  return true;
              } else {
                  System.out.println("The spot is already free!");
                  return false;
              }
          }

       public void listCarSpacesAvailables() {
              System.out.println("Parking Entrance");
              for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < columns; j++) {
                      System.out.print((parkingSpaces[i][j] ? "[X]" : "[ ]") + " "); 
                  }
                  System.out.println();
              }
              System.out.println("Parking Exit");
          }
       
}