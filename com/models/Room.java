<<<<<<< HEAD
package com.models;

import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Room implements Model<Room> {

    private RoomStatuses status;
    private RoomTypes roomType;

    Room(RoomStatuses status, RoomTypes roomType) {

        this.status = status;
        this.roomType = roomType;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setStatus(RoomStatuses status) {
        this.status = status;
    }

    public RoomStatuses getStatus() {
        return status;
    }
}
=======
package com.models;

import com.enums.RoomStatuses;
import com.enums.RoomTypes;

public class Room implements Model<Room> {

    private RoomStatuses status;
    private RoomTypes roomType;

    Room(RoomStatuses status, RoomTypes roomType) {

        this.status = status;
        this.roomType = roomType;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setStatus(RoomStatuses status) {
        this.status = status;
    }

    public RoomStatuses getStatus() {
        return status;
    }
}
>>>>>>> zaheen-edits
