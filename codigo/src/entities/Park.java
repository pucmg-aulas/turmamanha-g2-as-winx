package entities;

public class Park {
       private int numberParkingSpaces;
       private CarSpace[] carSpaces;
       private double pricePerHour;
       private double pricePerMinute;
       private int idPark;

       public Park(int numberParkingSpaces, double pricePerHour, double pricePerMinute, int idPark) {
              this.numberParkingSpaces = numberParkingSpaces;
              this.pricePerHour = pricePerHour;
              this.pricePerMinute = pricePerMinute;
              this.idPark = idPark;
              this.carSpaces = new CarSpace[numberParkingSpaces];
       }
       public int getNumberParkingSpaces() {
              return numberParkingSpaces;
       }
       public void setNumberParkingSpaces(int numberParkingSpaces) {
              this.numberParkingSpaces = numberParkingSpaces;
       }
       public void addCarSpace(CarSpace carSpace) {
              for (int i = 0; i < carSpaces.length; i++) {
                     if (carSpaces[i] == null) {
                            carSpaces[i] = carSpace;
                            break;
                     }
              }
       }

       public void listCarSpacesAvailables(){
              int parkMat[][] = new int[5][5];
              char letter;
              System.out.println("Parking Entrance");
              for(int i=0; i<5;i++){
                  for(int j=0; j<5;j++){
                      parkMat[i][j] = j;
                      letter = (char) ('A' + i);
                      System.out.printf("%c%d ", letter, parkMat[i][j]);
                  }
                  System.out.println();
              }
              System.out.println("Parking Exit");
          }

}