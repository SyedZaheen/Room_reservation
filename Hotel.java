import Rooms.*;


public class Hotel {
    Hotel() {
        SingleRoom singleRooms[] = new SingleRoom[12];
        DoubleRoom doubleRooms[] = new DoubleRoom[12];
        DeluxeRoom deluxeRooms[] = new DeluxeRoom[12];
        vipSuiteRoom vipSuiteRooms[] = new vipSuiteRoom[12];
        for(int i = 0; i < 12; i++) {
            singleRooms[i] = new SingleRoom(i);
            doubleRooms[i] = new DoubleRoom(i+12);
            deluxeRooms[i] = new DeluxeRoom(i+24);
            vipSuiteRooms[i] = new vipSuiteRoom(i+36);
        }
    }

    

}
